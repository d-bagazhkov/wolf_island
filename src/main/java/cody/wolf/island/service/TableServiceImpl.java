package cody.wolf.island.service;

import cody.wolf.island.config.IslandConfig;
import cody.wolf.island.model.Ceil;
import cody.wolf.island.model.IslandTable;
import cody.wolf.island.model.Position;
import cody.wolf.island.model.things.Thing;
import cody.wolf.island.model.things.animal.Rabbit;
import cody.wolf.island.model.things.animal.Wolf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class TableServiceImpl implements TableService {

    private final IslandConfig islandConfig;

    private IslandTable table;

    public TableServiceImpl(IslandConfig islandConfig) {
        this.islandConfig = islandConfig;
        reset();
    }

    public IslandTable handle() {
        table.forEach(ceil -> {
            Thing thing = ceil.getThing();
            if (thing.isMovable())
                while (true)
                    if (table.move(ceil, defineRandomPositionAround(ceil.getPosition()))) {
                        ceil.dock();
                        break;
                    }
        });
        table.forEach(Ceil::unDock);
        return table;
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


    public IslandTable reset() {
        log.info("Create new island");
        table = new IslandTable(islandConfig.getCountHorizontalCeil(), islandConfig.getCountVerticalCeil());

        List<Position> isset = new ArrayList<>();

        for (int i = 0; i < islandConfig.getStartCountWolf(); i++) {
            randomFill(isset, Wolf.class);
        }
        for (int i = 0; i < islandConfig.getStartCountRabbit(); i++) {
            randomFill(isset, Rabbit.class);
        }

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
