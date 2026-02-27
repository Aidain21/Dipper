package io.github.some_example_name;

public class LevelTemplates {
    public static void addTemplatesToMap(map map) {
        TileFills gen = new TileFills();
        TileFills w = gen.CreateTileFills("wall");

        TileFills w1 = gen.CreateTileFills("bouncy",0f);
        TileFills w2 = gen.CreateTileFills("bouncy",90f);
        TileFills w3 = gen.CreateTileFills("bouncy",180f);
        TileFills w4 = gen.CreateTileFills("bouncy",270f);

        TileFills R1 = gen.CreateTileFills("r",0f);
        TileFills R2 = gen.CreateTileFills("r",90f);
        TileFills R3 = gen.CreateTileFills("r",180f);
        TileFills R4 = gen.CreateTileFills("r",270f);

        TileFills B1 = gen.CreateTileFills("b",0f);
        TileFills B2 = gen.CreateTileFills("b",90f);
        TileFills B3 = gen.CreateTileFills("b",180f);
        TileFills B4 = gen.CreateTileFills("b",270f);

        TileFills Y1 = gen.CreateTileFills("y",0f);
        TileFills Y2 = gen.CreateTileFills("y",90f);
        TileFills Y3 = gen.CreateTileFills("y",180f);
        TileFills Y4 = gen.CreateTileFills("y",270f);

        TileFills G1 = gen.CreateTileFills("g",0f);
        TileFills G2 = gen.CreateTileFills("g",90f);
        TileFills G3 = gen.CreateTileFills("g",180f);
        TileFills G4 = gen.CreateTileFills("g",270f);


        TileFills f = gen.CreateTileFills("floor");
        TileFills b = gen.CreateTileFills("box");
        TileFills pb = gen.CreateTileFills("pressureButton",-1,-1);
        TileFills p = gen.CreateTileFills("portal",-1,-1);
        TileFills i = gen.CreateTileFills("inportal",-1,-1);

        level level3 = new level(3,5,true);
        level3.level1 = new TileFills[][] {
            {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w },
            {w ,w1,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w4,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w1,w3,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,p ,f ,f ,f ,f ,f ,f ,w1,w3,f ,f ,f ,f ,f ,f ,i ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w1,w3,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w1,w3,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w1,w3,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w3,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,pb,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,pb,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,G1,G2,G3,G4,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,Y1,Y2,Y3,Y4,f ,f ,f ,f ,f ,f ,f ,f ,f ,b ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,b ,f ,f ,f ,B1,B2,B3,B4,f ,f ,f ,f ,f ,f ,f ,f ,b ,f ,b ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,R1,R2,R3,R4,f ,f ,f ,f ,f ,f ,f ,f ,f ,b ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,w2,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w3,w },
            {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w },
        };
        addDataToTile(level3, p, 0,1);
        addDataToTile(level3, i, 10,1);
        addDataToTile(level3, pb, 3,2);
        addDataToTile(level3, pb, 4,2);
        invertLevelY(level3);
        map.levelMap[1][1] = level3;

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
