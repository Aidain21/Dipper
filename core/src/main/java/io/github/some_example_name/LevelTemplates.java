package io.github.some_example_name;

import com.google.gson.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;

import java.util.ArrayList;
public class LevelTemplates {
    public static level level3 = new level(3,5,true);
    public static level levelX = new level(1,1,true);
    public static level levelHello = new level(1,1,true);
    //public static int buttonCount3 = 0;
    //public static ColorButton[] colorButtonList3 = new ColorButton[24];
    public static level alexlevel1 = new level(1,5,true);
    public static ArrayList<ColorButton> colorButtons3 = new ArrayList<>();
    public static void addTemplatesToMap(map map) {

        TileFills w = Tile.wall;
        TileFills f = Tile.floor;
        levelX = new level(5,5,true);
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

        level level1 = loadJson("level1.json");
        createObjects(level1);
        map.addName(level1);
        map.levelMap[0][0] = level1;


        level level2 = loadJson("level2.json");
        createObjects(level2);
        map.addName(level2);
        map.levelMap[1][0] = level2;

        level3 = loadJson("level3.json");
        createObjects(level3);
        map.addName(level3);
        map.levelMap[1][1] = level3;

        alexlevel1 = loadJson("alexlevel.json");
        createObjects(alexlevel1);
        map.addName(alexlevel1);
        map.levelMap[1][2] = alexlevel1;

        levelHello = loadJson("HELLO2.json");
        createObjects(levelHello);
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
