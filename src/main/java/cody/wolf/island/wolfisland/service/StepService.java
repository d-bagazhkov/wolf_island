package cody.wolf.island.wolfisland.service;

import cody.wolf.island.wolfisland.config.IslandConfig;
import cody.wolf.island.wolfisland.model.Entity;
import cody.wolf.island.wolfisland.model.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class StepService {

    private final IslandConfig islandConfig;

    public List<Entity> handle(List<Entity> positions) {
//        positions.stream()
//                .filter(p -> p.getEntityName() != null)
//                .map(Objects::toString)
//                .forEach(log::info);
        get(1, 5, positions).setEntityName("\uD83D\uDE0E");
        get(2, 5, positions).setEntityName("R");
        get(3, 5, positions).setEntityName("e");
        get(4, 5, positions).setEntityName("s");
        get(5, 5, positions).setEntityName("p");
        get(6, 5, positions).setEntityName("o");
        get(7, 5, positions).setEntityName("n");
        get(8, 5, positions).setEntityName("s");
        get(9, 5, positions).setEntityName("e");//Response
        get(3, 6, positions).setEntityName("f");
        get(4, 6, positions).setEntityName("r");
        get(5, 6, positions).setEntityName("o");
        get(6, 6, positions).setEntityName("m");//from
        get(2, 7, positions).setEntityName("s");
        get(3, 7, positions).setEntityName("e");
        get(4, 7, positions).setEntityName("r");
        get(5, 7, positions).setEntityName("v");
        get(6, 7, positions).setEntityName("e");
        get(7, 7, positions).setEntityName("r");//server
        return positions;
    }

    private Entity get(Integer x, Integer y, List<Entity> from) {
        return from.stream()
                .filter(e -> e.getPosition().getX().equals(x) && e.getPosition().getY().equals(y))
                .findFirst()
                .orElse(null);
    }

    public List<Entity> reset() {
        return IntStream.range(0, islandConfig.getCountHorizontalCeil()).boxed()
                .flatMap(x -> IntStream.range(0, islandConfig.getCountVerticalCeil()).boxed().map(y -> {
                    Entity entity = new Entity();
                    Position position = new Position();
                    position.setX(x);
                    position.setY(y);
                    entity.setPosition(position);
                    return entity;
                }))
                .collect(Collectors.toList());
    }
}
