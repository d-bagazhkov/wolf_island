package cody.wolf.island.service;

import cody.wolf.island.model.Cell;
import cody.wolf.island.model.Position;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Island {

    Cell getCell(int x, int y);

    Cell getCell(Position position);

    void blockCell(Cell cell);

    void blockCell(Position position);

    void unblockAll();

    boolean move(Cell from, Position to);

    boolean move(int fromX, int fromY, int toX, int toY);

    void replace(Cell from, Cell to);

    void forEachCell(Consumer<Cell> cellConsumer);

    void blockedForEach(Function<Cell, Position> function);

    void remove(Cell cell);

    void remove(Position position);

    List<Position> around(Position position);
}
