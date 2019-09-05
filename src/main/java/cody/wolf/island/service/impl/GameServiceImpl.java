package cody.wolf.island.service.impl;

import cody.wolf.island.config.IslandConfig;
import cody.wolf.island.model.Ceil;
import cody.wolf.island.model.Position;
import cody.wolf.island.model.things.Thing;
import cody.wolf.island.model.things.animal.AnimalThing;
import cody.wolf.island.model.things.animal.Rabbit;
import cody.wolf.island.model.things.animal.Wolf;
import cody.wolf.island.model.things.enums.ContentValue;
import cody.wolf.island.service.GameService;
import cody.wolf.island.service.StatsService;
import cody.wolf.island.service.TableService;
import cody.wolf.island.utils.PositionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    private final IslandConfig islandConfig;
    private final StatsService statsService;

    private TableService table;
    private Random random = new Random();

    public GameServiceImpl(IslandConfig islandConfig, StatsService statsService) {
        this.islandConfig = islandConfig;
        this.statsService = statsService;
        reset();
    }

    public TableService handle() {
        table.blockedForEach(ceil -> {
            if (ceil.getThing().isMovable()) {
                AnimalThing thing = (AnimalThing) ceil.getThing();
                thing.incAge();
                List<Position> shuffleAround = getShuffleAround(ceil.getPosition());
                if (ceil.hasContent(ContentValue.WOLF)) {
                    Optional<Ceil> rabbit = shuffleAround.stream()
                            .map(p -> table.get(p))
                            .filter(c -> c.hasContent(ContentValue.RABBIT))
                            .findFirst();
                    if (rabbit.isPresent()) {
                        table.replace(ceil, rabbit.get());
                        thing.incEnergy(islandConfig.getWolfConfig().getIncEnergy());
                        statsService.decRabbit();
                        return rabbit.get().getPosition();
                    }
                }
                for (Position position : shuffleAround) {
                    if (table.get(position).hasContent(ContentValue.WOLF)) continue;
                    if (table.move(ceil, position))
                        return position;
                }
                death(thing, ceil.getPosition());
            }
            return null;
        });
        birth();
        statsService.incSteps();
        return table;
    }

    private boolean death(AnimalThing thing, Position position) {
        if (thing.getValue().equals(ContentValue.WOLF)) {
            thing.decEnergy(islandConfig.getWolfConfig().getDecEnergy());
            if (thing.getEnergy() <= 0) {
                statsService.decWolf();
                table.remove(position);
                return true;
            }
        }
        if (thing.getValue().equals(ContentValue.RABBIT)) {
            thing.decEnergy(islandConfig.getRabbitConfig().getDecEnergy());
            if (thing.getEnergy() <= 0) {
                statsService.decRabbit();
                table.remove(position);
                return true;
            }
        }
        return false;
    }

    private void birth() {
        table.forEachCeil(ceil -> {
                    try {
                        if (ceil.getThing().isMovable()) {
                            AnimalThing thing = (AnimalThing) ceil.getThing();
                            if (ceil.hasContent(ContentValue.WOLF) && thing.getAge() > islandConfig.getWolfConfig().getBornAge()
                                    || ceil.hasContent(ContentValue.RABBIT) && thing.getAge() > islandConfig.getWolfConfig().getBornAge()) {
                                Position position = getShuffleAround(ceil.getPosition()).get(0);
                                Ceil childCeil = table.get(position);
                                childCeil.setThing(thing.getClass().newInstance());
                                thing.setAge(0);
                                statsService.incInstance(thing.getValue());
                            }
                        }
                    } catch (InstantiationException | IllegalAccessException e) {
                        log.error("Unexpected error: ", e);
                    }
                }
        );
    }

    private List<Position> getShuffleAround(Position position) {
        List<Position> shuffleResult = PositionUtils.around(position, islandConfig.getCountHorizontalCeil(), islandConfig.getCountVerticalCeil());
        Collections.shuffle(shuffleResult);
        return shuffleResult;
    }


    public TableService reset() {
        log.info("Create new island");
        statsService.clear();
        table = new TableServiceImpl(islandConfig.getCountHorizontalCeil(), islandConfig.getCountVerticalCeil());

        List<Position> isset = new ArrayList<>();

        for (int i = 0; i < islandConfig.getWolfConfig().getCount(); i++) {
            randomFill(isset, Wolf.class);
        }
        statsService.incWolf(islandConfig.getWolfConfig().getCount());
        for (int i = 0; i < islandConfig.getRabbitConfig().getCount(); i++) {
            randomFill(isset, Rabbit.class);
        }
        statsService.incRabbit(islandConfig.getRabbitConfig().getCount());

        log.debug("New island: {}", table);
        return table;
    }

    private void randomFill(List<Position> isset, Class<? extends Thing> thingClass) {
        Random random = new Random();
        while (true) {
            Position position = new Position(random.nextInt(islandConfig.getCountHorizontalCeil()), random.nextInt(islandConfig.getCountVerticalCeil()));
            if (isset.stream().anyMatch(p -> p.equals(position))) continue;
            isset.add(position);
            try {
                log.info("Set {} on position x={}, y={}", thingClass.getSimpleName(), position.getX(), position.getY());
                table.get(position).setThing(thingClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("Can't create new thing instance", e);
                return;
            }
            return;
        }
    }

}
