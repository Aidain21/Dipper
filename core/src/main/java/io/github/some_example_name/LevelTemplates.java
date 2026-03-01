package io.github.some_example_name;

public class LevelTemplates {
    public static level level3 = new level(3,5,true);
    public static void addTemplatesToMap(map map) {
        TileFills gen = new TileFills();
        TileFills w = gen.CreateTileFills("wall");

        TileFills w1 = gen.CreateTileFills("bouncy",0f);
        TileFills w2 = gen.CreateTileFills("bouncy",90f);
        TileFills w3 = gen.CreateTileFills("bouncy",180f);
        TileFills w4 = gen.CreateTileFills("bouncy",270f);

        TileFills R1 = gen.CreateTileFills("rB",0f);
        TileFills R2 = gen.CreateTileFills("rB",90f);
        TileFills R3 = gen.CreateTileFills("rB",180f);
        TileFills R4 = gen.CreateTileFills("rB",270f);

        TileFills B1 = gen.CreateTileFills("bB",0f);
        TileFills B2 = gen.CreateTileFills("bB",90f);
        TileFills B3 = gen.CreateTileFills("bB",180f);
        TileFills B4 = gen.CreateTileFills("bB",270f);

        TileFills Y1 = gen.CreateTileFills("yB",0f);
        TileFills Y2 = gen.CreateTileFills("yB",90f);
        TileFills Y3 = gen.CreateTileFills("yB",180f);
        TileFills Y4 = gen.CreateTileFills("yB",270f);

        TileFills G1 = gen.CreateTileFills("gB",0f);
        TileFills G2 = gen.CreateTileFills("gB",90f);
        TileFills G3 = gen.CreateTileFills("gB",180f);
        TileFills G4 = gen.CreateTileFills("gB",270f);

        TileFills bu = gen.CreateTileFills("button");
        TileFills f = gen.CreateTileFills("floor");
        TileFills b = gen.CreateTileFills("box");
        TileFills pb = gen.CreateTileFills("pressureButton",-1,-1);
        TileFills p = gen.CreateTileFills("portal",-1,-1);
        TileFills i = gen.CreateTileFills("inportal",-1,-1);


        level3.level1 = new TileFills[][] {
        //   0     2     4     6     8    10    12    14    16    18    20    22    24    26    28
            {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w },
            {w ,w ,w ,w ,w ,w ,p ,w ,w ,w ,w ,w ,w ,w ,w ,w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w4,w },// 18
            {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,Y3,f ,f ,f ,f ,f ,w },
            {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,f ,f ,f ,f ,f ,w1,f ,f ,f ,f ,R4,w ,G2,f ,f ,f ,f ,w },// 16
            {w ,w ,w ,w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w1,f ,w3,f ,f ,f ,f ,B4,w ,Y2,f ,f ,f ,f ,w },
            {w ,w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,G1,f ,f ,f ,f ,f ,w },// 14
            {w ,f ,f ,f ,f ,bu,bu,bu,f ,f ,f ,f ,f ,w1,f ,w3,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,i ,f ,f ,f ,f ,w },// 12
            {w ,w1,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w3,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,pb,f ,f ,f ,f ,p ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },// 10
            {w ,f ,f ,f ,f ,pb,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },// 8
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,bu,f ,G1,G2,G3,G4,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },// 6
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,Y1,Y2,Y3,Y4,f ,f ,f ,f ,f ,f ,f ,f ,f ,b ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,b ,f ,f ,f ,B1,B2,B3,B4,f ,f ,f ,f ,f ,f ,f ,f ,b ,f ,b ,f ,f ,f ,f ,w },// 4
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,R1,R2,R3,R4,f ,f ,f ,f ,f ,f ,f ,f ,f ,b ,f ,f ,f ,f ,f ,w },
            {w ,f ,b ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },// 2
            {w ,w2,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w3,w },
            {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w },// 0
        };
        addDataToTile(level3, p, 0,1);
        addDataToTile(level3, i, 10,1);
        addDataToTile(level3, pb, 3,2);
        addDataToTile(level3, pb, 4,2);
        invertLevelY(level3);
        createObjects(level3);
        map.levelMap[1][1] = level3;
        level3.changeTile(2, 1, "wall");

        //add more levels below here
    }

    //works for portal, inportal, and pressureButton
    //add commands in order of top to bottom then left to right with multiples
    public static void addDataToTile(level level, TileFills tile, int x, int y) {
        for (int i = 0; i < level.level1.length; i++) {
            for (int j = 0; j < level.level1[0].length; j++) {
                if (level.level1[i][j].equals(tile)) {
                    level.changeTile(j,i,level.level1[i][j].getTileString(),x,y);
                }
            }
        }
    }

    public static void createObjects(level level) {
        for (int i = 0; i < level.level1.length; i++) {
            for (int j = 0; j < level.level1[0].length; j++) {
                TileFills tile = level.level1[i][j];
                switch(tile.getTileString()) {
                    // Sprite Objects
                    case "bouncy":
                    case "rB":
                    case "gB":
                    case "bB":
                    case "yB":
                    case "pressureButton": tile = tile.CreateTileFills(j, i, tile.getTileString(), tile.getRotation()); break;

                    // Texture Objects
                    case "box":
                    case "button": tile = tile.CreateTileFills(tile.getTileString()); break;
                    default: break;
                }
                level.level1[i][j] = tile;
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

/*
Boilerplate level:

level levelX = new level(5,5,true);
    levelX.level1 = new TileFills[][] {
        {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
        {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w },
    };

    //These change data of tiles that can have many variations
    //tiles with only a few variations like bouncy walls only having 4 just have different shorthands.
    //addDataToTile(level3, p, 0,1);
    //addDataToTile(level3, i, 10,1);

    //Keep this line so it renders correctly
    invertLevelY(levelX);
    map.levelMap[1][1] = levelX;
}


 */
