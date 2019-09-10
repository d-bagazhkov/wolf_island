package cody.wolf.island.service.impl;

import cody.wolf.island.config.IslandConfig;
import cody.wolf.island.model.Cell;
import cody.wolf.island.model.Position;
import cody.wolf.island.model.things.Thing;
import cody.wolf.island.model.things.animal.AnimalThing;
import cody.wolf.island.model.things.animal.Rabbit;
import cody.wolf.island.model.things.animal.Wolf;
import cody.wolf.island.model.things.enums.ContentValue;
import cody.wolf.island.service.GameService;
import cody.wolf.island.service.Island;
import cody.wolf.island.service.StatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    private final IslandConfig islandConfig;
    private final StatsService statsService;

    private Island island;

    public GameServiceImpl(IslandConfig islandConfig, StatsService statsService) {
        this.islandConfig = islandConfig;
        this.statsService = statsService;
        reset();
    }

    public Island handle() {
        island.blockedForEach(cell -> {
            if (cell.getThing().isMovable()) {
                AnimalThing thing = (AnimalThing) cell.getThing();
                thing.incAge();
                List<Position> shuffleAround = getShuffleAround(cell.getPosition());
                if (cell.hasContent(ContentValue.WOLF)) {
                    Optional<Cell> rabbit = shuffleAround.stream()
                            .map(p -> island.getCell(p))
                            .filter(c -> c.hasContent(ContentValue.RABBIT))
                            .findFirst();
                    if (rabbit.isPresent()) {
                        island.replace(cell, rabbit.get());
                        thing.incEnergy(islandConfig.getWolfConfig().getIncEnergy());
                        statsService.decRabbit();
                        return rabbit.get().getPosition();
                    }
                }
                for (Position position : shuffleAround) {
                    if (island.getCell(position).hasContent(ContentValue.WOLF)) continue;
                    if (island.move(cell, position))
                        return position;
                }
                death(thing, cell.getPosition());
            }
            return null;
        });
        birth();
        statsService.incSteps();
        return island;
    }

    private boolean death(AnimalThing thing, Position position) {
        if (thing.getValue().equals(ContentValue.WOLF)) {
            thing.decEnergy(islandConfig.getWolfConfig().getDecEnergy());
            if (thing.getEnergy() <= 0) {
                statsService.decWolf();
                island.remove(position);
                return true;
            }
        }
        if (thing.getValue().equals(ContentValue.RABBIT)) {
            thing.decEnergy(islandConfig.getRabbitConfig().getDecEnergy());
            if (thing.getEnergy() <= 0) {
                statsService.decRabbit();
                island.remove(position);
                return true;
            }
        }
        return false;
    }

    private void birth() {
        island.forEachCell(cell -> {
                    try {
                        if (cell.getThing().isMovable()) {
                            AnimalThing thing = (AnimalThing) cell.getThing();
                            if (cell.hasContent(ContentValue.WOLF) && thing.getAge() > islandConfig.getWolfConfig().getBornAge()
                                    || cell.hasContent(ContentValue.RABBIT) && thing.getAge() > islandConfig.getWolfConfig().getBornAge()) {
                                Position position = getShuffleAround(cell.getPosition()).get(0);
                                Cell childCell = island.getCell(position);
                                childCell.setThing(thing.getClass().getDeclaredConstructor().newInstance());
                                thing.setAge(0);
                                statsService.incInstance(thing.getValue());
                            }
                        }
                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        log.error("Unexpected error: ", e);
                    }
                }
        );
    }

    private List<Position> getShuffleAround(Position position) {
        List<Position> shuffleResult = island.around(position);
        Collections.shuffle(shuffleResult);
        return shuffleResult;
    }


    public Island reset() {
        log.info("New island is being created...");
        statsService.clearStats();
        island = new IslandImpl(islandConfig.getCountHorizontalCell(), islandConfig.getCountVerticalCell());

        List<Position> positionList = new ArrayList<>();

        for (int i = 0; i < islandConfig.getWolfConfig().getCount(); i++) {
            randomFill(positionList, Wolf.class);
        }
        statsService.incWolf(islandConfig.getWolfConfig().getCount());
        for (int i = 0; i < islandConfig.getRabbitConfig().getCount(); i++) {
            randomFill(positionList, Rabbit.class);
        }
        statsService.incRabbit(islandConfig.getRabbitConfig().getCount());

        log.debug("New island: {}", island);
        return island;
    }

    private void randomFill(List<Position> isset, Class<? extends Thing> thingClass) {
        Random random = new Random();
        while (true) {
            Position position = new Position(random.nextInt(islandConfig.getCountHorizontalCell()), random.nextInt(islandConfig.getCountVerticalCell()));
            if (isset.stream().anyMatch(p -> p.equals(position))) continue;
            isset.add(position);
            try {
                log.info("Set {} on position x={}, y={}", thingClass.getSimpleName(), position.getX(), position.getY());
                island.getCell(position).setThing(thingClass.getDeclaredConstructor().newInstance());
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                log.error("Can't create new thing instance", e);
                return;
            }
            return;
        }
    }

}
