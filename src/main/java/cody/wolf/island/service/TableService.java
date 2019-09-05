package cody.wolf.island.service;

import cody.wolf.island.model.Ceil;
import cody.wolf.island.model.Position;

import javax.validation.constraints.Positive;
import java.util.function.Consumer;
import java.util.function.Function;

public interface TableService {

    Ceil get(int x, int y);

    Ceil get(Position position);

    void block(Ceil ceil);

    void block(Position position);

    void unblockALl();

    boolean move(Ceil from, Position to);

    boolean move(int fromX, int fromY, int toX, int toY);

    void replace(Ceil from, Ceil to);

    void forEachCeil(Consumer<Ceil> ceilConsumer);

    void blockedForEach(Function<Ceil, Position> function);

    void remove(Ceil ceil);

    void remove(Position position);
}
