package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;

import java.util.Arrays;

public class map {

    level[][] levelMap;
    int mapRows;
    int mapCols;

    public map(int r, int c, int levelR, int levelC) {
        mapRows = r;
        mapCols = c;
        levelMap = new level[mapCols][mapRows];

        for(int i=0;i<mapRows;i++) {
            System.out.print(i);
            for (int j = 0; j < mapCols; j++) {
                levelMap[j][i] = new level(0, 0);
            }
        }
        levelMap[0][0] = new level(levelR, levelC, 1, 1);
    }

    public level[][] getMap() {
        return levelMap;
    }

    public boolean isEmpty(int r, int c) {
        if (r<0 || r>mapRows || c<0 || c>mapCols || (Arrays.deepEquals(levelMap[c][r].getLevel(), new level(0, 0).getLevel()))) {
            return true;
        }
        return false;
    }
}
