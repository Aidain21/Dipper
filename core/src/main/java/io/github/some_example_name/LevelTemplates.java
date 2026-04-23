package io.github.some_example_name;

import com.google.gson.*;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;
import java.io.FileNotFoundException;

public class LevelTemplates {
    public static level level3 = new level(3,5,true);
    public static level alexlevel3 = new level(1,5,true);
    public static level finalBoss = new level(1,5,true);
    public static void addTemplatesToMap(map map) {
        defaultSetup();
        //randomSetup(map);
    }

    public static void defaultSetup() {
        setup(loadJson("level1.json"), 0, 0);
        setup(loadJson("level2.json"), 1, 0);
        setup(loadJson("largeEmpty.json"), 2, 2);
        setup(loadJson("asdf.json"), 4, 1);
        setup(loadJson("iceEasy.json"), 3,2);
        level3 = setup(loadJson("level3.json"), 1, 1);
        setup(loadJson("alexlevel.json"), 1, 2);
        setup(loadJson("SamLevel1.json"), 3, 3);
        setup(loadJson("gatesBig.json"), 4, 2);
        setup(loadJson("levelSelect.json"), 5, 1);
        setup(loadJson("HELLO2.json"), 0, 3);
        setup(loadJson("alexlevel2.json"), 4, 0);
        finalBoss = setup(loadJson("finalBoss.json"), 6, 6);
        alexlevel3 = setup(loadJson("alexlevel3.json"), 5, 0);
    }

    public static void randomSetup(map map) {

        File dir = new File("levels");
        File[] levels = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".json");
            }
        });

        List<File> shuffle = Arrays.asList(levels);
        Collections.shuffle(shuffle);

        boolean stop = false;

        for (int i = 0; i < map.mapRows; i++) {
            if (stop) {
                break;
            }
            for (int j = 0; j < map.mapCols; j++) {
                if (i*map.mapRows+j == shuffle.size()) {
                    stop = true;
                    break;
                }
                setup(loadJson(shuffle.get(i*map.mapRows+j).getName()), i, j);
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

    public static level setup(level level, int x, int y) {
        createObjects(level);
        level.icon = level.icon.refill();
        map.addName(level);
        map.levelMap[x][y] = level;

        if(Objects.equals(level.filename, "level3.json")) {level3 = level;}
        if(Objects.equals(level.filename, "finalBoss.json")) {finalBoss = level;}
        if(Objects.equals(level.filename, "alexlevel3.json")) {alexlevel3 = level;}

        return level;
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
