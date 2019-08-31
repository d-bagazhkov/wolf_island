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
        table = new IslandTable(islandConfig.getCountHorizontalCeil(), islandConfig.getCountVerticalCeil());
    }

    public IslandTable handle() {
        for (int x = 0; x < islandConfig.getCountHorizontalCeil(); x++) {
            for (int y = 0; y < islandConfig.getCountVerticalCeil(); y++) {
                Ceil ceil = table.get(x, y);
                Thing thing = ceil.getThing();
                if (thing.isMovable()) {
                    //todo move
                }
            }
        }
        return table;
    }


    public IslandTable reset() {
        table = new IslandTable(islandConfig.getCountHorizontalCeil(), islandConfig.getCountVerticalCeil());

        List<Position> isset = new ArrayList<>();

        for (int i = 0; i < islandConfig.getStartCountWolf(); i++) {
            randomFill(isset, Wolf.class);
        }
        for (int i = 0; i < islandConfig.getStartCountRabbit(); i++) {
            randomFill(isset, Rabbit.class);
        }

         return table;
    }

    private void randomFill(List<Position> isset, Class<? extends Thing> thingClass) {
        Random random = new Random();
        while (true) {
            Position position = new Position(random.nextInt(islandConfig.getCountHorizontalCeil()), random.nextInt(islandConfig.getCountVerticalCeil()));
            if (isset.stream().anyMatch(p -> p.equals(position))) continue;
            isset.add(position);
            try {
                table.get(position).setThing(thingClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("Can't create new thing instance", e);
                return;
            }
            return;
        }
    }

}
