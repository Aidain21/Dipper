package io.github.some_example_name;

import com.google.gson.*;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;

import java.util.ArrayList;
public class LevelTemplates {
    public static level level3 = new level(3,5,true);
    public static level levelX = new level(1,1,true);
    public static level levelHello = new level(1,1,true);
    public static level iceLevel = new level(20,40,8,12,true);
    public static level iceEasy = new level(15,10,7,5,true);
    public static level alexlevel1 = new level(1,5,true);
    public static level alexlevel2 = new level(1,5,true);
    public static level gatesBig = new level(3,5,true);
    public static level levelSelect = new level(3, 5, true);
    public static level asdf = new level(5,5,true);
    public static void addTemplatesToMap(map map) {

        /*
        File dir = new File("levels");
        File[] levels = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        });

        for (int i = 0; i < 8; i++) {

        }
         */



        // setup(level, map x, map y)
        level level1 = loadJson("level1.json");
        setup(level1, 0, 0);

        level level2 = loadJson("level2.json");
        setup(level2, 1, 0);

        level large = loadJson("largeEmpty.json");
        setup(large, 2, 2);

        asdf = loadJson("asdf.json");
        setup(asdf, 4, 1);

        iceEasy = loadJson("iceEasy.json");
        setup(iceEasy, 3,2);

        level3 = loadJson("level3.json");
        setup(level3, 1, 1);

        alexlevel1 = loadJson("alexlevel.json");
        setup(alexlevel1, 1, 2);

        iceLevel = loadJson("SamLevel1.json");
        setup(iceLevel, 3, 3);

        gatesBig = loadJson("gatesBig.json");
        setup(gatesBig, 4, 2);

        levelSelect = loadJson("levelSelect.json");
        setup(levelSelect, 5, 1);

        levelHello = loadJson("HELLO2.json");
        setup(levelHello, 0, 3);

        alexlevel2 = loadJson("alexlevel2.json");
        setup(alexlevel2, 4, 0);
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

    public static void setup(level level, int x, int y) {
        createObjects(level);
        level.icon = level.icon.refill();
        map.addName(level);
        map.levelMap[x][y] = level;
    }

    public static level loadJson(String fileName) {
        level test = new level(3,5,true);
        Gson gson = new Gson();
        File theFile = new File("levels/" + fileName);
        try {
            Scanner coolGuy = new Scanner(theFile);
            String jsonStuff = coolGuy.nextLine();
            test = gson.fromJson(jsonStuff, level.class);
            if (test.icon == null) {
                test.icon = Tile.floor;
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred: File not found.");
        }
        test.filename = fileName;
        return test;
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
