package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class TileFills {
    String fill;
    //if you can walk on a tile
    boolean canWalk=false;
    //if you can move a tile
    boolean movable=false;
    Sprite sprite;

    //default constructor for empty tiles
    public TileFills() {
        fill = " ";
    }

    //called when only handed the name of a tile, returns a new tile
    public TileFills CreateTileFills(String fill){
        switch (fill) {
            case "floor": return new Floor();
            case "wall": return new Wall();
            case "box": return new Box();
            case "button": return new Button();
            default: return new TileFills();
        }
    }

    //called when handed a name and 2 numbers, currently used for portals
    public TileFills CreateTileFills(String fill, int x, int y){
        switch (fill) {
            case "portal": return new Portal(x, y);
            case "inportal": return new InLevelPortal(x, y);
            case "pressureButton": return new PressureButton(x, y);
            default: return new TileFills();
        }
    }

    //called when given 2 names, used currently for the lever
    public TileFills CreateTileFills(String fill, String newFill){
        return new Lever(newFill);
    }

    //called when given a name and number, currently just for spikes
    public TileFills CreateTileFills(String fill, int damage){ return new Spikes(damage);}

    // Rotation
    public TileFills CreateTileFills(int x, int y, String fill, float r) {
        switch(fill) {
            case "bouncy": return new BouncyWall(x, y, r);
            case "r":
            case "g":
            case "b":
            case "y": return new ColorButton(x, y, fill, r);
            default: return new TileFills();
        }
    }

    public String getTileString(){
        return fill;
    }
    public Sprite getSprite() { return sprite;}

}

