package io.github.some_example_name;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;

public class Drawing {

    public static boolean drawing;
    public static String currentFile;
    //public Vector2Int size;
    public static level workingLevel;
    //public TileFills[][] grid;
    public static TileFills curTile;
    public static int tileNum = 0;



    public static void start() {
        drawing = true;
        TextBox.clearText();
        Scanner scan = new Scanner(System.in);
        //System.out.print("Enter Json File Name: ");
        //currentFile = scan.nextLine();
        currentFile = "HELLO2.json";
        workingLevel = loadArtJson(currentFile);
        TextBox.textRight[0] = "Editing level: " + currentFile;
        tempReAddTextures(workingLevel);
        curTile = new TileFills().CreateTileFills("wall");
        //size.x = scan.nextInt();
        //size.y = scan.nextInt();

    }

    public static void end() {
        saveAsArtJson(workingLevel, currentFile);
        drawing = false;
    }

    public static void drawTile(int mouseX, int mouseY, boolean erase) {
        int rX = Math.round((mouseX - 15) / 32.0f);
        int rY = Math.round((720-mouseY - 15) / 32.0f);
        if (rY > 0 && rX > 0 && rY < workingLevel.level1.length && rX < workingLevel.level1[0].length) {
            if (erase) {
                workingLevel.level1[rY][rX] = new TileFills().CreateTileFills("floor");
            }
            else {
                workingLevel.level1[rY][rX] = curTile;
            }

        }
    }

    public static void changeDrawTile(int change) {
        if (tileNum + change < 0) {
            curTile = LevelTemplates.tileArray[LevelTemplates.tileArray.length - 1];
        }
        else if (tileNum + change > LevelTemplates.tileArray.length - 1) {
            curTile = LevelTemplates.tileArray[0];
        }
        else {
            curTile = LevelTemplates.tileArray[tileNum+change];
        }

        TextBox.textRight[1] = "Current Tile: " + curTile.getTileString();
        TextBox.textRight[2] = "Tile Num: " + tileNum;

    }


    public static void saveAsArtJson (level level, String fileName) {
        Gson guyThatDoesTheJson = new Gson();
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(guyThatDoesTheJson.toJson(level));
            //file.flush();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static level loadArtJson(String fileName) {
        level test = new level(3,5,true);
        Gson gson = new Gson();
        File theFile = new File(fileName);
        try {
            Scanner coolGuy = new Scanner(theFile);
            String jsonStuff = coolGuy.nextLine();
            test = gson.fromJson(jsonStuff, level.class);
        }
        catch (FileNotFoundException e) {
            try {
                theFile.createNewFile();
                System.out.println(theFile.getName());
                saveAsArtJson(LevelTemplates.levelX, theFile.getName());
                test = LevelTemplates.levelX;
            }
            catch (IOException ignored) {

            }

        }
        return test;
    }



    public static void tempReAddTextures(level level) {
        TileFills gen = new TileFills();
        TileFills w = gen.CreateTileFills("wall");
        TileFills f = gen.CreateTileFills("floor");


        for (int i = 0; i < level.level1.length; i++) {
            for (int j = 0; j < level.level1[0].length; j++) {
                if (Objects.equals(level.level1[i][j].getTileString(), "wall")) {
                    level.level1[i][j] = w;
                }
                else if (Objects.equals(level.level1[i][j].getTileString(), "floor")) {
                    level.level1[i][j] = f;
                }
            }
        }
    }
}
