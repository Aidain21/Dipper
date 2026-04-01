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
            tileNum = LevelTemplates.tileArray.length - 1;
        }
        else if (tileNum + change > LevelTemplates.tileArray.length - 1) {
            curTile = LevelTemplates.tileArray[0];
            tileNum = 0;
        }
        else {
            curTile = LevelTemplates.tileArray[tileNum+change];
            tileNum += change;
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
        TileFills in = gen.CreateTileFills("inportal",-1,-1);
        TileFills p2 = gen.CreateTileFills("inportal",-1,-1);
        TileFills iF = gen.CreateTileFills("iceFloor");

        for (int i = 0; i < level.level1.length; i++) {
            for (int j = 0; j < level.level1[0].length; j++) {
                TileFills tile = level.level1[i][j];

                switch(tile.getTileString()) {
                    case "wall": tile = w; break;
                    case "iceFloor": tile = iF; break;
                    case "button": tile = bu; break;
                    case "floor": tile = f; break;
                    case "portal": tile = p; break;
                    case "bouncyWall":
                        System.out.print("aslfkja");
                        tile = w1;
                        //if (tile.getRotation() == 0) tile = w1;
                        //else if (tile.getRotation() == 90) tile = w2;
                        //else if (tile.getRotation() == 180) tile = w3;
                        //else if (tile.getRotation() == 270) tile = w4;
                        break;
                    default: break;
                }
                level.level1[i][j] = tile;
            }
        }
    }
}
