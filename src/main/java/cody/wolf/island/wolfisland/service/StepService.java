package cody.wolf.island.wolfisland.service;

import cody.wolf.island.wolfisland.config.IslandConfig;
import cody.wolf.island.wolfisland.model.Entity;
import cody.wolf.island.wolfisland.model.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Service
@RequiredArgsConstructor
public class StepService {

    private final IslandConfig islandConfig;
    @Value("${entity.view.wolf}")
    private String wolf;
    @Value("${entity.view.rabbit}")
    private String rabbit;

    public List<Entity> handle(List<Entity> positions) {
        if (positions.stream().noneMatch(e -> hasText(e.getEntityName()))) {
            return reset();
        }

        return positions;
    }

    private Entity get(Integer x, Integer y, List<Entity> from) {
        return from.stream()
                .filter(e -> e.getPosition().getX().equals(x) && e.getPosition().getY().equals(y))
                .findFirst()
                .orElse(null);
    }

    public List<Entity> reset() {
        List<Entity> entities = IntStream.range(0, islandConfig.getCountHorizontalCeil()).boxed()
                .flatMap(x -> IntStream.range(0, islandConfig.getCountVerticalCeil()).boxed().map(y -> {
                    Entity entity = new Entity();
                    Position position = new Position();
                    position.setX(x);
                    position.setY(y);
                    entity.setPosition(position);
                    return entity;
                }))
                .collect(Collectors.toList());
        Set<Position> existPosition = new HashSet<>();
        IntStream.range(0, islandConfig.getStartCountWolf()).boxed().forEach(i -> {
            Position position;
            do {
                position = getRandomPosition();
            } while (!existPosition.add(position));
            get(position.getX(), position.getY(), entities).setEntityName(wolf);
        });
        IntStream.range(0, islandConfig.getStartCountRabbit()).boxed().forEach(i -> {
            Position position;
            do {
                position = getRandomPosition();
            } while (!existPosition.add(position));
            get(position.getX(), position.getY(), entities).setEntityName(rabbit);
        });
        return entities;
    }

    private Position getRandomPosition() {
        Position position = new Position();
        Random random = new Random();
        position.setY(random.nextInt(islandConfig.getCountVerticalCeil()));
        position.setX(random.nextInt(islandConfig.getCountHorizontalCeil()));
        return position;
    }
}
