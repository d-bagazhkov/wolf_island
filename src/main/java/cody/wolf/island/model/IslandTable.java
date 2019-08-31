package cody.wolf.island.model;

import cody.wolf.island.model.things.enums.ContentValue;
import cody.wolf.island.utils.IslandTableSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize(using = IslandTableSerialize.class)
public class IslandTable {

    private final int horizontalSize;
    private final int verticalSize;

    private Ceil[][] table;

    public IslandTable(int horizontalSize, int verticalSize) {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
        table = new Ceil[horizontalSize][verticalSize];
        for (int x = 0; x < horizontalSize; x++) {
            for (int y = 0; y < verticalSize; y++) {
                table[x][y] = new Ceil(new Position(x, y));
            }
        }
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }

    public int getVerticalSize() {
        return verticalSize;
    }

    public Ceil get(Position position) {
        return get(position.getX(), position.getY());
    }

    public Ceil get(int x, int y) {
        return table[x][y];
    }

    public boolean move(Position from, Position to) {
        return move(from.getX(), from.getY(), to.getX(), to.getY());
    }

    private boolean move(int fromX, int fromY, int toX, int toY) {
        if (!isEmpty(toX, toY)) return false;

        Ceil to = get(toX, toY);
        Ceil from = get(fromX, fromY);

        return true;
    }

    private boolean isEmpty(int x, int y) {
        return get(x, y).getThing().getValue().equals(ContentValue.EMPTY);
    }
}
