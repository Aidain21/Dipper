package io.github.some_example_name;

import com.google.gson.Gson;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

//Load as: 7
//Load: 8
//Save as: 9
//Save: 0

public class Drawing {

    public static boolean drawing;
    public static boolean placingPortal;
    public static boolean placingOutPortal;
    public static boolean twoWay;
    public static String currentFile;
    //public Vector2Int size;
    public static level workingLevel;
    //public TileFills[][] grid;
    public static TileFills curTile;
    public static int tileNum = 0;
    public static Vector2Int portalEdit = new Vector2Int(0,0);



    public static void start(boolean loadPlayerLevel) {
        drawing = true;
        TextBox.clearText();
        if (!loadPlayerLevel) {
            currentFile = JOptionPane.showInputDialog("Load JSON file:") + ".json";
            if (currentFile.equals("null.json")) {
                end(false);
                return;
            }
            workingLevel = loadArtJson(currentFile);
        }
        else {
            currentFile = "temp.json";
            workingLevel = Main.currentLevel;
        }
        TextBox.updateTextBox("Editing level: " + currentFile,3);
        LevelTemplates.createObjects(workingLevel);
        curTile = Tile.wall;
        TextBox.updateTextBox("Current Tile: " + curTile.getTileString(),4);
        TextBox.updateTextBox("Tile Num: " + tileNum,5);

    }

    public static void end(boolean saveAs) {
        if (saveAs) {
            currentFile = JOptionPane.showInputDialog("Save as:") + ".json";
        }
        if (!currentFile.equals("null.json")) {
            saveAsArtJson(workingLevel, currentFile);
        }
        TextBox.clearText();
        drawing = false;
    }

    public static void startPlacePortal(int x, int y, boolean inPortal) {
        if (inPortal) {
            twoWay = false;
            placingPortal = true;
            portalEdit = new Vector2Int(x, y);
        }
        else {
            portalEdit = new Vector2Int(x, y);
            placingOutPortal = true;
            Main.fullMap = true;
        }
    }

    public static void endPlacePortal(int mouseX, int mouseY) {
        int rX = Math.round((mouseX - 15) / 32.0f);
        int rY = Math.round((720-mouseY - 15) / 32.0f);
        if (rY > 0 && rX > 0 && rY < workingLevel.level1.length && rX < workingLevel.level1[0].length) {
            workingLevel.level1[portalEdit.x][portalEdit.y] =
                new TileFills().CreateTileFills("inportal", rX, rY);
            if (twoWay) {
                workingLevel.level1[rY][rX] = new TileFills().CreateTileFills("inportal", portalEdit.y, portalEdit.x);
                workingLevel.level1[rY][rX].refill();
            }
        }
        placingPortal = false;
        TextBox.updateTextBox("Current Tile: " + curTile.getTileString(),4);
        TextBox.updateTextBox("Tile Num: " + tileNum,5);
    }

    public static void endPlaceOutPortal(int mouseX, int mouseY) {
        int rX = Math.round((mouseX - 31) / 64.0f);
        int rY = Math.round((720 - mouseY - 31) / 64.0f);
        if (rY > 0 && rX > 0 && rY < Main.levels.mapRows && rX < Main.levels.mapCols) {
            workingLevel.level1[portalEdit.x][portalEdit.y] =
                new TileFills().CreateTileFills("portal", rX, rY);
        }
        placingOutPortal = false;
        Main.fullMap = false;
        TextBox.updateTextBox("Current Tile: " + curTile.getTileString(),4);
        TextBox.updateTextBox("Tile Num: " + tileNum,5);
    }



    public static void getTileData(int mouseX, int mouseY) {
        int rX = Math.round((mouseX - 15) / 32.0f);
        int rY = Math.round((720-mouseY - 15) / 32.0f);
        if (rY > 0 && rX > 0 && rY < workingLevel.level1.length && rX < workingLevel.level1[0].length) {
            TextBox.text[0] = "fill: " + workingLevel.level1[rY][rX].getTileString();
            TextBox.text[1] = "rot: " + workingLevel.level1[rY][rX].getRotation();
            TextBox.text[2] = "x y: " + workingLevel.level1[rY][rX].getData().x
                + " " + workingLevel.level1[rY][rX].getData().y;
        }


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
                if (Objects.equals(workingLevel.level1[rY][rX].getTileString(), "inportal")) {
                    startPlacePortal(rY,rX,true);
                }
                if (Objects.equals(workingLevel.level1[rY][rX].getTileString(), "portal")) {
                    startPlacePortal(rY,rX,false);
                }
            }

        }
    }

    public static void rotateTile(int mouseX, int mouseY) {
        List<String> rotatable = Arrays.asList("bouncy","rB","bB","gB","yB");
        float[] angles = new float[] {0f,90f,180f,270f};
        int rX = Math.round((mouseX - 15) / 32.0f);
        int rY = Math.round((720-mouseY - 15) / 32.0f);
        if (rY > 0 && rX > 0 && rY < workingLevel.level1.length && rX < workingLevel.level1[0].length) {
            if (rotatable.contains(workingLevel.level1[rY][rX].getTileString())) {
                workingLevel.level1[rY][rX] = new TileFills().CreateTileFills(workingLevel.level1[rY][rX].getTileString(),
                    angles[iterateArray(Math.round(workingLevel.level1[rY][rX].rotation/90),1,angles.length)]);
            }
        }
    }

    public static void changeDrawTile(int change) {
        curTile = Tile.drawTileArray[iterateArray(tileNum,change,Tile.drawTileArray.length)];
        tileNum = iterateArray(tileNum,change,Tile.drawTileArray.length);


        TextBox.updateTextBox("Current Tile: " + curTile.getTileString(),4);
        TextBox.updateTextBox("Tile Num: " + tileNum,5);

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

    public static int iterateArray(int start, int change, int size) {
        if (start + change < 0) {
            return size - (Math.abs(change)-start);
        }
        else if (start + change > size - 1) {
             return start + change - size;
        }
        else {
            return start + change;
        }
    }


    /*
    public static void tempReAddTextures(level level) {

        TileFills s = Tile.spikes;
        TileFills p = Tile.outPortal;
        TileFills in = Tile.inPortal;


        for (int i = 0; i < level.level1.length; i++) {
            for (int j = 0; j < level.level1[0].length; j++) {
                TileFills tile = level.level1[i][j];

                switch(tile.getTileString()) {
                    case "portal": tile = p; break;
                    case "inportal": tile = in; break;
                    case "spikes": tile = s; break;
                    default: break;
                }
                level.level1[i][j] = tile;
            }
        }
    }
     */
}
