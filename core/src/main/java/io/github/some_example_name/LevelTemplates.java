package io.github.some_example_name;

import com.badlogic.gdx.utils.Json;
import com.google.gson.*;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LevelTemplates {
    public static level level3 = new level(3,5,true);
    public static level level4 = new level(1,1,true);
    public static level levelX = new level(1,1,true);
    public static int buttonCount3 = 0;
    public static TileFills[] tileArray;
    public static ColorButton[] colorButtonList3 = new ColorButton[24];
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

        TileFills rG = gen.CreateTileFills("rGate");
        TileFills gG = gen.CreateTileFills("gGate");
        TileFills bG = gen.CreateTileFills("bGate");
        TileFills yG = gen.CreateTileFills("yGate");

        TileFills bu = gen.CreateTileFills("button");
        TileFills f = gen.CreateTileFills("floor");
        TileFills s = gen.CreateTileFills("spikes", 1);
        TileFills b = gen.CreateTileFills("box");
        TileFills v = gen.CreateTileFills("void");
        TileFills pb = gen.CreateTileFills("pressureButton",-1,-1);
        TileFills p = gen.CreateTileFills("portal",-1,-1);
        TileFills i = gen.CreateTileFills("inportal",-1,-1);
        TileFills p2 = gen.CreateTileFills("inportal",-1,-1);
        TileFills iF = gen.CreateTileFills("iceFloor");

        tileArray = new TileFills[]{w,w1,w2,w3,w4,R1,R2,R3,R4,B1,B2,B3,B4,Y1,Y2,Y3,Y4,G1,G2,G3,G4,
            rG,gG,bG,yG,bu,f,s,b,v,pb,p,i,p2,iF};


        level3.level1 = new TileFills[][] {
        //   0     2     4     6     8    10    12    14    16    18    20    22    24    26    28
            {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w },
            {w ,w ,w ,w ,w ,w ,p ,w ,w ,w ,w ,w ,w ,w ,w ,w ,f ,f ,f ,f ,gG,f ,f ,f ,f ,f ,f ,f ,w4,w },// 18
            {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,f ,f ,f ,f ,f ,f ,gG,yG,yG,yG,bG,bG,rG,rG,f ,w },
            {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,f ,f ,f ,f ,f ,w1,f ,f ,gG,f ,f ,gG,gG,bG,bG,rG,f ,w },// 16
            {w ,w ,w ,w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w1,f ,w3,f ,f ,gG,gG,gG,gG,f ,f ,f ,f ,f ,w },
            {w ,w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },// 14
            {w ,f ,f ,f ,f ,bu,bu,bu,f ,f ,f ,f ,f ,w1,f ,w3,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,i ,f ,s ,s ,f ,w },// 12
            {w ,w1,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w3,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,s ,s ,f ,w },
            {w ,f ,f ,f ,f ,f ,pb,f ,f ,f ,f ,p ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,s ,f ,w },// 10
            {w ,f ,f ,f ,f ,pb,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,v ,v ,v ,v ,f ,s ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,b ,f ,b ,f ,b ,v ,v ,v ,v ,f ,s ,f ,w },// 8
            {w ,f ,f ,f ,f ,f ,f ,f ,bu,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,iF,f ,f ,f ,bu,f ,f ,f ,f ,f ,f ,f ,R4,w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },// 6
            {w ,f ,f ,f ,iF,iF,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,G4,w ,f ,f ,f ,f ,f ,b ,f ,f ,f ,f ,f ,w },
            {w ,f ,p2,f ,iF,f ,b ,f ,f ,f ,f ,f ,f ,f ,f ,f ,G4,w ,f ,f ,f ,f ,b ,f ,b ,f ,f ,f ,f ,w },// 4
            {w ,f ,f ,f ,iF,f ,f ,f ,f ,f ,B3,B3,R3,R3,Y3,B3,w ,w ,f ,f ,f ,f ,f ,b ,f ,f ,f ,f ,f ,w },
            {w ,f ,b ,f ,f ,f ,f ,f ,f ,w ,w ,w ,w ,w ,w ,w ,w ,Y1,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },// 2
            {w ,w2,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w3,w },
            {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w },// 0
        };

        addDataToTile(level3, p, 0,1, false);
        addDataToTile(level3, i, 10,1, false);
        addDataToTile(level3, pb, 3,2, false);
        addDataToTile(level3, pb, 4,2, false);
        addDataToTile(level3, p2, 10,9, true);
        invertLevelY(level3);
        createObjects(level3);

        //saveAsJson(level3);
        //level3 = loadJson("testy.json");

        map.addName(level3);
        map.levelMap[1][1] = level3;
        //level3.changeTile(2, 1, "wall");



        //add more levels below here

        tileArray = new TileFills[]{w,w1,w2,w3,w4,R1,R2,R3,R4,B1,B2,B3,B4,Y1,Y2,Y3,Y4,G1,G2,G3,G4,
            rG,gG,bG,yG,bu,f,s,b,v,pb,p,i,p2,iF};

        level4.level1 = new TileFills[][] {
            {w,w1,w2,w3,w4},
            {R1,R2,R3,R4,bu},
            {rG,gG,bG,yG,f},
            {s,b,pb,v,i},
            {p2,iF,w,w,w}
        };

        invertLevelY(level4);
        createObjects(level4);

        //saveAsJson(level4);
        //level4 = loadJson("testy.json");

        //map.addName(level4);
        //map.levelMap[1][1] = level4;

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
    }

    //works for portal, inportal, and pressureButton
    //add commands in order of top to bottom then left to right with multiples
    //two way is to check if 2 portals are being created
    public static void addDataToTile(level level, TileFills tile, int x, int y, boolean twoWay) {
        for (int i = 0; i < level.level1.length; i++) {
            for (int j = 0; j < level.level1[0].length; j++) {
                if (level.level1[i][j].equals(tile)) {
                    if (twoWay) {
                        level.changeTile(j, i, level.level1[i][j].getTileString(), y+1, x);//, true);
                        level.changeTile(x,y,level.level1[i][j].getTileString(),j,level.level1.length-i-1);
                    }
                    else
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
                    case "rB":
                    case "gB":
                    case "bB":
                    case "yB": tile = tile.CreateTileFills(j, i, tile.getTileString(), tile.getRotation());
                        colorButtonList3[buttonCount3] = (ColorButton) tile;
                        buttonCount3++; break;
                    case "bouncy":
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

    public static void saveAsJson (level level) {
        Gson guyThatDoesTheJson = new Gson();
        try (FileWriter file = new FileWriter("testy.json")) {
            file.write(guyThatDoesTheJson.toJson(level));
            //file.flush();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static level loadJson(String fileName) {
        level test = new level(3,5,true);
        Gson gson = new Gson();
        File theFile = new File(fileName);
        try {
            Scanner coolGuy = new Scanner(theFile);
            String jsonStuff = coolGuy.nextLine();
            test = gson.fromJson(jsonStuff, level.class);
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred: File not found.");
        }
        return test;
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
