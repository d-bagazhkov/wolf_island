package cody.wolf.island.service.impl;

import cody.wolf.island.config.IslandConfig;
import cody.wolf.island.model.Position;
import cody.wolf.island.model.things.Thing;
import cody.wolf.island.model.things.animal.Rabbit;
import cody.wolf.island.model.things.animal.Wolf;
import cody.wolf.island.service.GameService;
import cody.wolf.island.service.StatsService;
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
        Set<Position> result = new HashSet<>(8);
        Function<Integer, Integer> around = (i) -> i + random.nextInt(3) - 1;
        int count = calcCountAround(position);
        while (result.size() != count) {
            Position positionRandom = new Position(around.apply(position.getX()), around.apply(position.getY()));
            if (validPosition(positionRandom) &&
                    !(position.getX().equals(positionRandom.getX())
                            && position.getY().equals(positionRandom.getY())))
                result.add(positionRandom);
        }
        List<Position> shuffleResult = new ArrayList<>(result);
        Collections.shuffle(shuffleResult);
        return shuffleResult;
    }

    private int calcCountAround(Position position) {
        int size = 8;

        if (position.getX() == 0 || position.getX().equals(islandConfig.getCountHorizontalCeil()))
            size -= 3;

        if (position.getY() == 0 || position.getY().equals(islandConfig.getCountVerticalCeil()))
            size -= 3;

        if ((position.getX() == 0 || position.getX().equals(islandConfig.getCountHorizontalCeil()))
                && (position.getY() == 0 || position.getY().equals(islandConfig.getCountVerticalCeil())))
            size += 1;

        return size;
    }

    private boolean validPosition(Position position) {
        if (position.getX() < 0
                || position.getY() < 0
                || position.getX() >= table.getHorizontalSize()
                || position.getY() >= table.getVerticalSize())
            return false;
        return true;
    }

    private Position defineRandomPositionAround(Position position) {
        Random random = new Random();
        Position positionNew;
        while (true) {
            int x = random.nextInt(3) - 1;
            int y = random.nextInt(3) - 1;
            if (x == 0 && y == 0) continue;
            int xNew = position.getX() + x;
            int yNew = position.getY() + y;
            if (xNew < 0
                    || yNew < 0
                    || xNew >= islandConfig.getCountHorizontalCeil()
                    || yNew >= islandConfig.getCountVerticalCeil())
                continue;
            positionNew = new Position(xNew, yNew);
            break;
        }
        return positionNew;
    }


    public TableServiceImpl reset() {
        log.info("Create new island");
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
