package cody.wolf.island.service.impl;

import cody.wolf.island.model.Cell;
import cody.wolf.island.model.CellImpl;
import cody.wolf.island.model.Position;
import cody.wolf.island.model.things.EmptyThing;
import cody.wolf.island.model.things.enums.ContentValue;
import cody.wolf.island.service.Island;
import cody.wolf.island.utils.IslandTableSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;


@Slf4j
@ToString(of = {"table"})
@JsonSerialize(using = IslandTableSerialize.class)
public class IslandImpl implements Island {

    private final int horizontalSize;
    private final int verticalSize;

    private Cell[][] table;
    private List<Position> blockedPositions = new ArrayList<>();

    public IslandImpl(int horizontalSize, int verticalSize) {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
        table = new CellImpl[horizontalSize][verticalSize];
        for (int x = 0; x < horizontalSize; x++) {
            for (int y = 0; y < verticalSize; y++) {
                table[x][y] = new CellImpl(new Position(x, y));
            }
        }
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }

    public int getVerticalSize() {
        return verticalSize;
    }

    @Override
    public Cell getCell(Position position) {
        return getCell(position.getX(), position.getY());
    }

    @Override
    public Cell getCell(int x, int y) {
        return table[x][y];
    }

    @Override
    public boolean move(Cell from, Position to) {
        return move(from.getPosition().getX(), from.getPosition().getY(), to.getX(), to.getY());
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        Cell to = getCell(toX, toY);
        if (!to.hasContent(ContentValue.EMPTY)) {
            log.debug("Cell on position x={}, y={} is already taken", toX, toY);
            return false;
        }
        Cell from = getCell(fromX, fromY);
        if (from.hasContent(ContentValue.EMPTY)) {
            log.debug("Cell on position x={}, y={} is empty. Nothing move", fromX, fromY);
            return false;
        }
        to.setThing(from.getThing());
        from.setThing(new EmptyThing());

        log.debug("Move from position x={} y={} to x={} y={}", fromX, fromY, toX, toY);
        return true;
    }

    @Override
    public void replace(Cell from, Cell to) {
        log.debug("Replace from position x={} y={} to x={} y={}", from.getPosition().getX(), from.getPosition().getY(), to.getPosition().getX(), to.getPosition().getY());
        to.setThing(from.getThing());
        from.setThing(new EmptyThing());
    }

    @Override
    public void forEachCell(Consumer<Cell> cellConsumer) {
        for (int x = 0; x < horizontalSize; x++)
            for (int y = 0; y < verticalSize; y++)
                cellConsumer.accept(getCell(x, y));
    }

    @Override
    public void blockedForEach(Function<Cell, Position> function) {
        for (int x = 0; x < horizontalSize; x++) {
            for (int y = 0; y < verticalSize; y++) {
                Cell cell = getCell(x, y);
                Position position = null;
                if (!blockedPositions.contains(cell.getPosition()))
                    position = function.apply(cell);
                if (position != null)
                    blockedPositions.add(position);
            }
        }
        unblockAll();
    }

    @Override
    public void remove(Cell cell) {
        remove(cell.getPosition());
    }

    @Override
    public void remove(Position position) {
        getCell(position).setThing(new EmptyThing());
    }

    @Override
    public void blockCell(Cell cell) {
        blockCell(cell.getPosition());
    }

    @Override
    public void blockCell(Position position) {
        blockedPositions.add(position);
    }

    @Override
    public void unblockAll() {
        blockedPositions.clear();
    }

    @Override
    public List<Position> around(Position position) {
        Set<Position> result = new HashSet<>();

        result.add(positionShift(position, -1, -1)); //left-top
        result.add(positionShift(position, 0, -1)); //top
        result.add(positionShift(position, 1, -1)); //right-top
        result.add(positionShift(position, 1, 0)); //right
        result.add(positionShift(position, 1, 1)); //right-bottom
        result.add(positionShift(position, 0, 1)); //bottom
        result.add(positionShift(position, -1, 1)); //left-bottom
        result.add(positionShift(position, -1, 0)); //left

        result.remove(null);

        return new ArrayList<>(result);
    }

    private Position positionShift(Position position, int shiftX, int shiftY) {
        Position tmp = new Position(position.getX() + shiftX, position.getY() + shiftY);

        return isPositionValid(tmp)
                ? tmp
                : null;
    }

    private boolean isPositionValid(Position position) {
        int x = position.getX();
        int y = position.getY();

        return x >= 0 && y >= 0 && x < horizontalSize && y < verticalSize;
    }
}
