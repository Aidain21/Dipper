package io.github.some_example_name;

import com.google.gson.*;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
public class LevelTemplates {
    public static level level3 = new level(3,5,true);
    public static level level4 = new level(1,1,true);
    public static level levelX = new level(1,1,true);
    public static level levelHello = new level(1,1,true);
    //public static int buttonCount3 = 0;
    //public static ColorButton[] colorButtonList3 = new ColorButton[24];
    public static level alexlevel1 = new level(1,5,true);
    public static ArrayList<ColorButton> colorButtons3 = new ArrayList<>();
    public static void addTemplatesToMap(map map) {

        TileFills w = Tile.wall;

        TileFills w1 = Tile.bouncyWall1;
        TileFills w2 = Tile.bouncyWall2;
        TileFills w3 = Tile.bouncyWall3;
        TileFills w4 = Tile.bouncyWall4;

        TileFills R1 = Tile.redButtonU;
        TileFills R2 = Tile.redButtonL;
        TileFills R3 = Tile.redButtonD;
        TileFills R4 = Tile.redButtonR;

        TileFills B1 = Tile.blueButtonU;
        TileFills B2 = Tile.blueButtonL;
        TileFills B3 = Tile.blueButtonD;
        TileFills B4 = Tile.blueButtonR;

        TileFills Y1 = Tile.yellowButtonU;
        TileFills Y2 = Tile.yellowButtonL;
        TileFills Y3 = Tile.yellowButtonD;
        TileFills Y4 = Tile.yellowButtonR;

        TileFills G1 = Tile.greenButtonU;
        TileFills G2 = Tile.greenButtonL;
        TileFills G3 = Tile.greenButtonD;
        TileFills G4 = Tile.greenButtonR;

        TileFills rG = Tile.redGate;
        TileFills gG = Tile.greenGate;
        TileFills bG = Tile.blueGate;
        TileFills yG = Tile.yellowGate;

        TileFills bu = Tile.button;
        TileFills f = Tile.floor;
        TileFills s = Tile.spikes;
        TileFills b = Tile.box;
        TileFills v = Tile.voidTile;
        TileFills pb = Tile.pressureButton;
        TileFills p = Tile.outPortal;
        TileFills i = Tile.inPortal;
        TileFills p2 = Tile.inPortal;
        TileFills iF = Tile.iceFloor;



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
            {w ,f ,f ,f ,f ,pb,f ,f ,f ,f ,f ,f ,f ,G3,f ,f ,f ,f ,f ,f ,f ,f ,v ,v ,v ,v ,f ,s ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,R4,w ,B2,f ,f ,b ,f ,b ,f ,b ,v ,v ,v ,v ,f ,s ,f ,w },// 8
            {w ,f ,f ,f ,f ,f ,f ,f ,bu,f ,f ,f ,f ,Y1,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,iF,f ,f ,f ,bu,f ,f ,f ,f ,f ,f ,f ,R4,w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },// 6
            {w ,f ,f ,f ,iF,iF,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,G4,w ,f ,f ,f ,f ,f ,b ,f ,f ,f ,f ,f ,w },
            {w ,f ,p2,f ,iF,f ,b ,f ,f ,f ,f ,f ,f ,f ,f ,f ,G4,w ,f ,f ,f ,f ,b ,f ,b ,f ,f ,f ,f ,w },// 4
            {w ,f ,f ,f ,iF,f ,f ,f ,f ,f ,B3,B3,R3,R3,Y3,B3,w ,w ,f ,f ,f ,f ,f ,b ,f ,f ,f ,f ,f ,w },
            {w ,f ,b ,f ,f ,f ,f ,f ,f ,w ,w ,w ,w ,w ,w ,w ,w ,Y1,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },// 2
            {w ,w2,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w3,w },
            {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w },// 0
        };

        saveAsJson(level3);
        level3 = loadJson("testy.json");
        //Drawing.tempReAddTextures(level3);

        addDataToTile(level3, Tile.outPortal.getTileString(), 2,1, false);
        addDataToTile(level3, Tile.outPortal.getTileString(), 0,0, false);
        addDataToTile(level3, Tile.inPortal.getTileString(), 10,1, false);
        addDataToTile(level3, Tile.inPortal.getTileString(), 10,9, true);
        invertLevelY(level3);
        createObjects(level3);

        map.addName(level3);
        map.levelMap[1][1] = level3;
        //level3.changeTile(2, 1, "wall");



        //add more levels below here

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


        //add some secret to top right corner in future
        alexlevel1.level1 = new TileFills[][] {
            {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w },
            {w ,f ,f ,f ,f ,f ,w ,f ,f ,s ,s ,w ,w ,w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w ,p ,w },
            {w ,f ,b ,f ,b ,f ,v ,f ,f ,s ,s ,s ,w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w ,f ,w },
            {w ,f ,f ,f ,f ,f ,w ,f ,f ,s ,s ,s ,s ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w ,f ,w },
            {w ,w ,w ,w ,w ,w ,w ,f ,f ,f ,f ,f ,v ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w ,v ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,v ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,p ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,v ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,v ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,v ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w },
            {w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,w ,w },
            {w ,p2,w ,f ,f ,f ,f ,f ,f ,f ,f ,w ,w ,w ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,f ,b ,p ,w },
            {w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w ,w },
        };

        addDataToTile(alexlevel1, "inportal", 3,alexlevel1.level1.length-2, false);
        addDataToTile(alexlevel1, "portal", 1,0, false);
        addDataToTile(alexlevel1, "portal", 1,1, false);
        addDataToTile(alexlevel1, "portal", 0,0, false);

        invertLevelY(alexlevel1);
        createObjects(alexlevel1);
        map.addName(alexlevel1);



        map.levelMap[1][2] = alexlevel1;



        levelHello = loadJson("HELLO2.json");
        createObjects(levelHello);
        //Drawing.tempReAddTextures(levelHello);
        //invertLevelY(levelHello);

        map.addName(levelHello);

        map.levelMap[0][3] = levelHello;
    }

    //works for portal, inportal, and pressureButton
    //add commands in order of top to bottom then left to right with multiples
    //two way is to check if 2 portals are being created
    public static void addDataToTile(level level, String fill, int x, int y, boolean twoWay) {
        for (int i = 0; i < level.level1.length; i++) {
            for (int j = 0; j < level.level1[0].length; j++) {
                if (level.level1[i][j].getTileString().equals(fill) && level.level1[i][j].dataX == -1) {
                    if (twoWay) {
                        level.changeTile(j, i, level.level1[i][j].getTileString(), x, y);//, true);
                        level.changeTile(x,level.level1.length-y-1,level.level1[i][j].getTileString(),j,level.level1.length-i-1);
                        return;
                    }
                    else {
                        level.changeTile(j, i, level.level1[i][j].getTileString(), x, y);
                        return;
                    }
                }
            }
        }
    }

    public static void createObjects(level level) {
        //gotta figure these guys out
        List<String> banned = Arrays.asList("lever");
        for (int i = 0; i < level.level1.length; i++) {
            for (int j = 0; j < level.level1[0].length; j++) {
                if (!banned.contains(level.level1[i][j].getTileString())) {
                    level.level1[i][j] = level.level1[i][j].refill();
                }

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
