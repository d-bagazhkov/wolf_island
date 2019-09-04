package cody.wolf.island.service.impl;

import cody.wolf.island.config.IslandConfig;
import cody.wolf.island.model.Position;
import cody.wolf.island.model.things.Thing;
import cody.wolf.island.model.things.animal.Rabbit;
import cody.wolf.island.model.things.animal.Wolf;
import cody.wolf.island.service.GameService;
import cody.wolf.island.service.StatsService;
import cody.wolf.island.utils.PositionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    private final IslandConfig islandConfig;
    private final StatsService statsService;

    private TableServiceImpl table;
    private Random random = new Random();

    public GameServiceImpl(IslandConfig islandConfig, StatsService statsService) {
        this.islandConfig = islandConfig;
        this.statsService = statsService;
        reset();
    }

    public TableServiceImpl handle() {
        table.blockedForEach(ceil -> {
            Thing thing = ceil.getThing();
            if (thing.isMovable())
                for (Position position : getShuffleAround(ceil.getPosition()))
                    if (table.move(ceil, position))
                        return position;
            return null;
        });
        statsService.incSteps();
        return table;
    }

    private List<Position> getShuffleAround(Position position) {
        List<Position> shuffleResult = PositionUtils.around(position, islandConfig.getCountHorizontalCeil(), islandConfig.getCountVerticalCeil());
        Collections.shuffle(shuffleResult);
        return shuffleResult;
    }


    public TableServiceImpl reset() {
        log.info("Create new island");
        statsService.clear();
        table = new TableServiceImpl(islandConfig.getCountHorizontalCeil(), islandConfig.getCountVerticalCeil());

        List<Position> isset = new ArrayList<>();

        for (int i = 0; i < islandConfig.getStartCountWolf(); i++) {
            randomFill(isset, Wolf.class);
        }
        statsService.incWolf(islandConfig.getStartCountWolf());
        for (int i = 0; i < islandConfig.getStartCountRabbit(); i++) {
            randomFill(isset, Rabbit.class);
        }
        statsService.incRabbit(islandConfig.getStartCountRabbit());

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
