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
    public Vector2Int size;
    public static level workingLevel;
    public TileFills[][] grid;



    public static void start() {
        drawing = true;
        Scanner scan = new Scanner(System.in);
        //System.out.print("Enter Json File Name: ");
        //currentFile = scan.nextLine();
        currentFile = "HELLO.json";
        workingLevel = loadArtJson(currentFile);
        tempReAddTextures(workingLevel);

        //size.x = scan.nextInt();
        //size.y = scan.nextInt();

    }

    public static void end() {
        saveAsArtJson(workingLevel, currentFile);
        drawing = false;
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
