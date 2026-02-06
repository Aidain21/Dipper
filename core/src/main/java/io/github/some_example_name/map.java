package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;

public class map {

    level[][] levelMap;
    int mapRows;
    int mapCols;

    /*public map(){
        super();
        levelMap=new level[5][5];
        levelMap[0][0]= new level(10,10);
    }

     */

    public map(int r, int c, int levelR, int levelC) {
        mapRows=r;
        mapCols=c;
        levelMap = new level[mapRows][mapCols];
        levelMap[0][0]= new level(levelR,levelC);
    }

    public level[][] getMap(){
        return levelMap;
    }
}
