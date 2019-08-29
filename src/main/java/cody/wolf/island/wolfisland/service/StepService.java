package cody.wolf.island.wolfisland.service;

import cody.wolf.island.wolfisland.config.IslandConfig;
import cody.wolf.island.wolfisland.model.Entity;
import cody.wolf.island.wolfisland.model.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;
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
    private List<Entity> positions;

    public List<Entity> handle() {
        if (positions == null) return reset();
        if (positions.stream().noneMatch(e -> hasText(e.getEntityName()))) {
            return reset();
        }
        forI(islandConfig.getCountHorizontalCeil(), x -> {
            forI(islandConfig.getCountVerticalCeil(), y -> {
                Entity entity = get(x, y, positions);
                if (!hasText(entity.getEntityName())) return;
                randomMove(entity, positions);
            });
        });

        return positions;
    }

    private void randomMove(Entity entity, List<Entity> positions) {
        List<Entity> around = new ArrayList<>(around(entity, positions));
        Entity newPosition;
        List<Integer> random = IntStream.range(0, around.size()).boxed().collect(Collectors.toList());
        for (int i = 0; i < around.size(); i++) {
            newPosition = around.get(random.get(i));
            if (hasText(newPosition.getEntityName())) continue;
            get(newPosition.getPosition().getX(), newPosition.getPosition().getY(), positions).setEntityName(entity.getEntityName());
            entity.setEntityName(null);
            return;
        }
    }

    private Set<Entity> around(Entity entity, List<Entity> positions) {
        Set<Entity> result = new HashSet<>();
        result.add(right(entity, positions));
        result.add(left(entity, positions));
        result.add(top(entity, positions));
        result.add(bottom(entity, positions));
        result.add(leftBottom(entity, positions));
        result.add(leftTop(entity, positions));
        result.add(rightBottom(entity, positions));
        result.add(rightTop(entity, positions));
        result.remove(null);
        return result;
    }

    private Entity right(Entity entity, List<Entity> positions) {
        if (entity.getPosition().getX() + 1 >= islandConfig.getCountHorizontalCeil())
            return null;
        return get(entity.getPosition().getX() + 1, entity.getPosition().getY(), positions);
    }

    private Entity left(Entity entity, List<Entity> positions) {
        if (entity.getPosition().getX() - 1 <= 0)
            return null;
        return get(entity.getPosition().getX() - 1, entity.getPosition().getY(), positions);
    }

    private Entity top(Entity entity, List<Entity> positions) {
        if (entity.getPosition().getY() - 1 <= 0)
            return null;
        return get(entity.getPosition().getX(), entity.getPosition().getY() - 1, positions);
    }

    private Entity bottom(Entity entity, List<Entity> positions) {
        if (entity.getPosition().getY() + 1 >= islandConfig.getCountVerticalCeil())
            return null;
        return get(entity.getPosition().getX(), entity.getPosition().getY() + 1, positions);
    }

    private Entity leftBottom(Entity entity, List<Entity> positions) {
        if (entity.getPosition().getY() + 1 >= islandConfig.getCountVerticalCeil()
                || entity.getPosition().getX() - 1 <= 0)
            return null;
        return get(entity.getPosition().getX() - 1, entity.getPosition().getY() + 1, positions);
    }

    private Entity rightBottom(Entity entity, List<Entity> positions) {
        if (entity.getPosition().getY() + 1 >= islandConfig.getCountVerticalCeil()
                || entity.getPosition().getX() + 1 >= islandConfig.getCountHorizontalCeil())
            return null;
        return get(entity.getPosition().getX() + 1, entity.getPosition().getY() + 1, positions);
    }

    private Entity rightTop(Entity entity, List<Entity> positions) {
        if (entity.getPosition().getY() - 1 <= 0
                || entity.getPosition().getX() + 1 >= islandConfig.getCountHorizontalCeil())
            return null;
        return get(entity.getPosition().getX() + 1, entity.getPosition().getY() - 1, positions);
    }

    private Entity leftTop(Entity entity, List<Entity> positions) {
        if (entity.getPosition().getY() - 1 <= 0
                || entity.getPosition().getX() - 1 <= 0)
            return null;
        return get(entity.getPosition().getX() - 1, entity.getPosition().getY() - 1, positions);
    }

    private void forI(int i, Consumer<Integer> consumer) {
        IntStream.range(0, i).boxed().forEach(consumer);
    }

    private Entity get(Integer x, Integer y, List<Entity> from) {
        return from.stream()
                .filter(e -> e.getPosition().getX().equals(x) && e.getPosition().getY().equals(y))
                .findFirst()
                .orElse(null);
    }

    public List<Entity> reset() {
        positions = IntStream.range(0, islandConfig.getCountHorizontalCeil()).boxed()
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
            get(position.getX(), position.getY(), positions).setEntityName(wolf);
        });
        IntStream.range(0, islandConfig.getStartCountRabbit()).boxed().forEach(i -> {
            Position position;
            do {
                position = getRandomPosition();
            } while (!existPosition.add(position));
            get(position.getX(), position.getY(), positions).setEntityName(rabbit);
        });
        return positions;
    }

    private Position getRandomPosition() {
        Position position = new Position();
        Random random = new Random();
        position.setY(random.nextInt(islandConfig.getCountVerticalCeil()));
        position.setX(random.nextInt(islandConfig.getCountHorizontalCeil()));
        return position;
    }
}
