package cody.wolf.island.utils;

import cody.wolf.island.model.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PositionUtils {

    public static List<Position> around(Position position, int maxX, int maxY) {
        Set<Position> result = new HashSet<>();

        result.add(positionShift(position, -1, -1, maxX, maxY)); //left-top
        result.add(positionShift(position, 0, -1, maxX, maxY)); //top
        result.add(positionShift(position, 1, -1, maxX, maxY)); //right-top
        result.add(positionShift(position, 1, 0, maxX, maxY)); //right
        result.add(positionShift(position, 1, 1, maxX, maxY)); //right-bottom
        result.add(positionShift(position, 0, 1, maxX, maxY)); //bottom
        result.add(positionShift(position, -1, 1, maxX, maxY)); //left-bottom
        result.add(positionShift(position, -1, 0, maxX, maxY)); //left

        result.remove(null);

        return new ArrayList<>(result);
    }

    private static Position positionShift(Position position, int shiftX, int shiftY, int maxX, int maxY) {
        Position tmp = new Position(position.getX() + shiftX, position.getY() + shiftY);

        return isPossitionValid(tmp, maxX, maxY)
                ? tmp
                : null;
    }

    private static boolean isPossitionValid(Position position, int maxX, int maxY) {
        int x = position.getX();
        int y = position.getY();

        return x >= 0 && y >= 0 && x < maxX && y < maxY;
    }

}
