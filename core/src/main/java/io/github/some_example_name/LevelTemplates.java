package io.github.some_example_name;

public class LevelTemplates {
    public static void addTemplatesToMap(map map) {
        TileFills gen = new TileFills();
        TileFills w = gen.CreateTileFills("wall");
        TileFills w1 = gen.CreateTileFills("1");
        TileFills w2 = gen.CreateTileFills("2");
        TileFills w3 = gen.CreateTileFills("3");
        TileFills w4 = gen.CreateTileFills("4");

        TileFills f = gen.CreateTileFills("floor");
        TileFills b = gen.CreateTileFills("box");
        TileFills p = gen.CreateTileFills("portal",-1,-1);
        TileFills i = gen.CreateTileFills("inportal",-1,-1);

        level level3 = new level(3,5,true);
        level3.level1 = new TileFills[][] {
            {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w1,w3,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,p ,f ,f ,f ,f ,f ,f ,w1,w3,f ,f ,f ,f ,f ,f ,i ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w1,w3,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w1,w3,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w1,w3,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w3,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,b ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,b ,b ,b ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,b ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w },



        };
        addDataToTile(level3, p, 0,1);
        addDataToTile(level3, i, 10,1);
        invertLevelY(level3);
        map.levelMap[1][1] = level3;
    }

    public static void addDataToTile(level level, TileFills tile, int x, int y) {
        for (int i = 0; i < level.level1.length; i++) {
            for (int j = 0; j < level.level1[0].length; j++) {
                if (level.level1[i][j].equals(tile)) {
                    level.changeTile(j,i,level.level1[i][j].getTileString(),x,y);
                }
            }
        }
    }

    public static void invertLevelY(level level) {
        int start = 0;
        int end = level.level1.length - 1;

        while (start < end) {
            // Swap the elements at the start and end indices
            TileFills[] temp = level.level1[start];
            level.level1[start] = level.level1[end];
            level.level1[end] = temp;

            // Move the indices toward the center
            start++;
            end--;
        }
    }
}
