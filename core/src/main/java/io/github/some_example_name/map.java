package io.github.some_example_name;

import java.util.Arrays;

public class map {

    static level[][] levelMap;
    int mapRows;
    int mapCols;
    static String[] adj={"happy","sad","tired","exciting","intense","hazy","sweltering","frigid"};
    static String[] noun={"cave","store","building","tower","dungeon","apartment"};
    static String[] verb={"dancing","running","sleeping","exploding","jumping","analyzing"};

    public map(int r, int c, int levelR, int levelC) {
        mapRows = r;
        mapCols = c;
        levelMap = new level[mapCols][mapRows];

        for(int i=0;i<mapRows;i++) {
            for (int j = 0; j < mapCols; j++) {
                levelMap[j][i] = new level(0, 0);
            }
        }
        if (Main.testing) {
            levelMap[0][0] = Drawing.workingLevel;
        }
        else {
            LevelTemplates.addTemplatesToMap(this);
        }


    }

    public level[][] getMap() {
        return levelMap;
    }

    public static void addName(level l){
        int a=(int) (Math.random()*((adj.length-1)+1));
        int n=(int) (Math.random()*((noun.length-1)+1));
        int v=(int) (Math.random()*((verb.length-1)+1));
        l.name=adj[a]+" "+verb[v]+" "+noun[n];
    }

}
