package cody.wolf.island.service.impl;

import cody.wolf.island.model.Ceil;
import cody.wolf.island.model.CeilImpl;
import cody.wolf.island.model.Position;
import cody.wolf.island.model.things.EmptyThing;
import cody.wolf.island.model.things.enums.ContentValue;
import cody.wolf.island.service.TableService;
import cody.wolf.island.utils.IslandTableSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


@Slf4j
@ToString(of = {"table"})
@JsonSerialize(using = IslandTableSerialize.class)
public class TableServiceImpl implements TableService {

    private final int horizontalSize;
    private final int verticalSize;

    private Ceil[][] table;
    private List<Position> blockedPositions = new ArrayList<>();

    public TableServiceImpl(int horizontalSize, int verticalSize) {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
        table = new CeilImpl[horizontalSize][verticalSize];
        for (int x = 0; x < horizontalSize; x++) {
            for (int y = 0; y < verticalSize; y++) {
                table[x][y] = new CeilImpl(new Position(x, y));
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
    public Ceil get(Position position) {
        return get(position.getX(), position.getY());
    }

    @Override
    public Ceil get(int x, int y) {
        return table[x][y];
    }

    @Override
    public boolean move(Ceil from, Position to) {
        return move(from.getPosition().getX(), from.getPosition().getY(), to.getX(), to.getY());
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        Ceil to = get(toX, toY);
        if (!to.hasContent(ContentValue.EMPTY)) {
            log.debug("Ceil on position x={}, y={} is already taken", toX, toY);
            return false;
        }
        Ceil from = get(fromX, fromY);
        if (from.hasContent(ContentValue.EMPTY)) {
            log.debug("Ceil on position x={}, y={} is empty. Nothing move", fromX, fromY);
            return false;
        }
        to.setThing(from.getThing());
        from.setThing(new EmptyThing());

        log.debug("Move from position x={} y={} to x={} y={}", fromX, fromY, toX, toY);
        return true;
    }

    @Override
    public void replace(Ceil from, Ceil to) {
        log.debug("Replace from position x={} y={} to x={} y={}", from.getPosition().getX(), from.getPosition().getY(), to.getPosition().getX(), to.getPosition().getY());
        to.setThing(from.getThing());
        from.setThing(new EmptyThing());
    }

    @Override
    public void forEachCeil(Consumer<Ceil> ceilConsumer) {
        for (int x = 0; x < horizontalSize; x++)
            for (int y = 0; y < verticalSize; y++)
                ceilConsumer.accept(get(x, y));
    }

    @Override
    public void blockedForEach(Function<Ceil, Position> function) {
        for (int x = 0; x < horizontalSize; x++) {
            for (int y = 0; y < verticalSize; y++) {
                Ceil ceil = get(x, y);
                Position position = null;
                if (!blockedPositions.contains(ceil.getPosition()))
                    position = function.apply(ceil);
                if (position != null)
                    blockedPositions.add(position);
            }
        }
        unblockALl();
    }

    @Override
    public void remove(Ceil ceil) {
        remove(ceil.getPosition());
    }

    @Override
    public void remove(Position position) {
        get(position).setThing(new EmptyThing());
    }

    @Override
    public void block(Ceil ceil) {
        block(ceil.getPosition());
    }

    @Override
    public void block(Position position) {
        blockedPositions.add(position);
    }

    @Override
    public void unblockALl() {
        blockedPositions.clear();
    }
}
