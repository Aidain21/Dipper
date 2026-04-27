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
        //defaultSetup();
        randomSetup(map);
    }

    public static void defaultSetup() {
        Main.levelNum = -1;
        setup(loadJson("devlevel1.json"), 0, 0);
        setup(loadJson("devlevel2.json"), 1, 0);
        level3 = setup(loadJson("devlevel3.json"), 1, 1);
        setup(loadJson("devLevelSelect.json"), 5, 1);

        setup(loadJson("easyIce.json"), 3,2);
        setup(loadJson("hardEnd.json"), 4, 0);
        setup(loadJson("easyBox.json"), 1, 2);
        setup(loadJson("mediumIce.json"), 3, 3);
        alexlevel3 = setup(loadJson("mediumSpikes.json"), 5, 0);
        setup(loadJson("mediumGates.json"), 4, 2);

        finalBoss = setup(loadJson("finalBoss.json"), 6, 6);
    }

    public static void randomSetup(map map) {
        Main.levelNum = 0;

        File dir = new File("levels");
        File[] levels = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".json");
            }
        });

        ArrayList<String> easies = new ArrayList<>(),
            mediums = new ArrayList<>(),
            hards = new ArrayList<>(),
            devs = new ArrayList<>(),
            devs2 = new ArrayList<>();

        for (File f : levels) {
            if (f.getName().charAt(0) == 'e') {easies.add(f.getName());}
            if (f.getName().charAt(0) == 'm') {mediums.add(f.getName());}
            if (f.getName().charAt(0) == 'h') {hards.add(f.getName());}
            if (f.getName().charAt(0) == 'd' && devs.size() < 8) {devs.add(f.getName());}
            else if (f.getName().charAt(0) == 'd' && devs.size() >= 8) {devs2.add(f.getName());}
        }

        addShuffledLevelsToRow(easies, 0);
        addShuffledLevelsToRow(mediums, 1);
        addShuffledLevelsToRow(hards, 2);
        addShuffledLevelsToRow(devs, 5);
        //addShuffledLevelsToRow(devs2, 6);


    }

    public static void addShuffledLevelsToRow(ArrayList<String> levels, int row) {
        Collections.shuffle(levels);
        while (levels.size() > Main.LEVELCAP) {
            levels.remove(levels.get(levels.size()-1));
        }
        for (int i = 0; i < levels.size(); i++) {
            setup(loadJson(levels.get(i)), row, i);
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

        if(Objects.equals(level.filename, "devlevel3.json")) {level3 = level;}
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
