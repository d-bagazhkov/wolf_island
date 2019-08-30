package cody.wolf.island.wolfisland.service.impl;

import cody.wolf.island.wolfisland.model.Entity;
import cody.wolf.island.wolfisland.service.StepService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StepServiceImpl2 implements StepService {

    @Override
    public List<Entity> handle() {

        String[][] array = new String[10][10];
        array[0][0] = "_";
        array[0][1] = "_";
        array[0][2] = "w";
        array[0][3] = "_";
        array[0][4] = "r";
        array[0][5] = "_";
        array[0][6] = "_";
        array[0][7] = "_";
        array[0][8] = "w";
        array[0][9] = "_";

        array[1][0] = "_";
        array[1][1] = "r";
        array[1][2] = "_";
        array[1][3] = "_";
        array[1][4] = "w";
        array[1][5] = "_";
        array[1][6] = "r";
        array[1][7] = "_";
        array[1][8] = "_";
        array[1][9] = "_";


        array[2][0] = "_";
        array[2][1] = "_";
        array[2][2] = "_";
        array[2][3] = "_";
        array[2][4] = "_";
        array[2][5] = "_";
        array[2][6] = "_";
        array[2][7] = "_";
        array[2][8] = "_";
        array[2][9] = "_";


        array[3][0] = "_";
        array[3][1] = "_";
        array[3][2] = "_";
        array[3][3] = "_";
        array[3][4] = "_";
        array[3][5] = "_";
        array[3][6] = "_";
        array[3][7] = "_";
        array[3][8] = "_";
        array[3][9] = "_";


        array[4][0] = "_";
        array[4][1] = "_";
        array[4][2] = "_";
        array[4][3] = "_";
        array[4][4] = "_";
        array[4][5] = "_";
        array[4][6] = "_";
        array[4][7] = "_";
        array[4][8] = "_";
        array[4][9] = "_";


        array[5][0] = "_";
        array[5][1] = "_";
        array[5][2] = "_";
        array[5][3] = "_";
        array[5][4] = "_";
        array[5][5] = "_";
        array[5][6] = "_";
        array[5][7] = "_";
        array[5][8] = "_";
        array[5][9] = "_";


        array[6][0] = "_";
        array[6][1] = "_";
        array[6][2] = "_";
        array[6][3] = "_";
        array[6][4] = "_";
        array[6][5] = "_";
        array[6][6] = "_";
        array[6][7] = "_";
        array[6][8] = "_";
        array[6][9] = "_";


        array[7][0] = "_";
        array[7][1] = "_";
        array[7][2] = "_";
        array[7][3] = "_";
        array[7][4] = "_";
        array[7][5] = "_";
        array[7][6] = "_";
        array[7][7] = "_";
        array[7][8] = "_";
        array[7][9] = "_";


        array[8][0] = "_";
        array[8][1] = "_";
        array[8][2] = "_";
        array[8][3] = "_";
        array[8][4] = "_";
        array[8][5] = "_";
        array[8][6] = "_";
        array[8][7] = "_";
        array[8][8] = "_";
        array[8][9] = "_";


        array[9][0] = "_";
        array[9][1] = "_";
        array[9][2] = "_";
        array[9][3] = "_";
        array[9][4] = "_";
        array[9][5] = "_";
        array[9][6] = "_";
        array[9][7] = "_";
        array[9][8] = "_";
        array[9][9] = "_";

        System.out.println(Arrays.deepToString(array).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array.length; y++) {
                final String ceil = array[x][y];
                if (null == ceil || ceil.equals("_")) {
                    continue;
                }
                //check if cell already moved
                if (ceil.equals("some boolean flag")) {
                    continue;
                }
                moveRandom(array, x, y);
            }
        }

        //todo
        return Arrays.stream(array)
                .flatMap(Arrays::stream)
                .map(e -> new Entity())
                .collect(Collectors.toList());
    }


    private void moveRandom(final String[][] array,
                            int x,
                            int y) {
        final List<Integer> moves = new ArrayList<>();
        for (int i = 0; i < 9; i++) moves.add(i);
        Collections.shuffle(moves);
        final String source = array[x][y];

        //if random number is zero we don't move
        if (moves.get(0).equals(0)) {
            return;
        }
        for (Integer move : moves) {
            switch (move) {
                // [1][2][3]
                // [8][w][4]
                // [7][6][r]
                case 1:
                    //check range
                    if (x - 1 < 0 || y - 1 < 0) {
                        break;
                    }
                    //if ceil is empty we just step in and end our move
                    if (array[x - 1][y - 1].equals("_")) {
                        array[x - 1][y - 1] = source;
                        return;
                    }

                    //if we are same rabbit - rabbit or wolf - wolf
                    //we continue searching
                    if (source.equals(array[x - 1][y - 1])) {
                        break;
                    }

                    //if we are wolf we eat rabbit and end our move
                    if (source.equals("w")) {
                        array[x][y] = "_";
                        array[x - 1][y - 1] = source;
                        return;
                    } else {
                        //if we are rabbit for not we just want another place to go
                        break; //todo run away form wolf logic
                    }
                case 2://todo
                case 3://todo
                case 4://todo
                case 5://todo
                case 6://todo
                case 7://todo
                case 8://todo
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public List<Entity> reset() {
        //todo
        return null;
    }
}
