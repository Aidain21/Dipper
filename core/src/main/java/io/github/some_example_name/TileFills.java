package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
public class TileFills {
    String fill;
    //if you can walk on a tile
    boolean canWalk=false;
    //if you can move a tile
    boolean movable=false;
    // Texture / Sprite
    transient Sprite sprite = null;
    transient Texture texture = null;
    float rotation = 0;
    //Vector2Int pos = new Vector2Int(0,0);
    int dataX = 0;
    int dataY = 0;
    boolean drawBackground = true;

    //default constructor for empty tiles
    public TileFills() {
        fill = " ";
    }

    //called when only handed the name of a tile, returns a new tile
    public TileFills CreateTileFills(String fill){
        switch (fill) {
            case "floor": return new SimpleTextures.Floor();
            case "iceFloor" : return new SimpleTextures.IceFloor();
            case "wall": return new SimpleTextures.Wall();
            case "box": return new Box();
            case "button": return new Button();
            case "void": return new SimpleTextures.Void();
            case "rGate":
            case "gGate":
            case "bGate":
            case "yGate": return new SimpleTextures.ColorGate(fill);
            case "pressureButton": return new PressureButton();
            case "health": return new SimpleTextures.Health();
            default: return new TileFills();
        }
    }

    //called when handed a name and 2 numbers, currently used for portals
    public TileFills CreateTileFills(String fill, int x, int y){
        switch (fill) {
            case "button": return new Button(x, y);
            case "portal": return new SimpleTextures.Portal(x, y);
            case "inportal": return new SimpleTextures.InLevelPortal(x, y);

            default: return new TileFills();
        }
    }

    //called when given 2 names, used currently for the lever
    public TileFills CreateTileFills(String fill, String newFill){
        return new Lever(newFill);
    }

    //called when given a name and number, currently just for spikes
    public TileFills CreateTileFills(String fill, int damage){ return new SimpleTextures.Spikes(damage);}

    // Default Sprite
    public TileFills CreateTileFills(String fill, float r) {
        switch(fill) {
            case "bouncy": return new SimpleTextures.BouncyWall(r);
            case "rB":
            case "gB":
            case "bB":
            case "yB": return new ColorButton(fill, r);
            default: return new TileFills();
        }
    }

    // Objects

    public TileFills refill() {
        switch(fill) {
            case "floor":
            case "iceFloor":
            case "wall":
            case "box":
            case "void":
            case "rGate":
            case "gGate":
            case "bGate":
            case "yGate":
            case "pressureButton": return CreateTileFills(fill);
            case "bouncy":
            case "rB":
            case "gB":
            case "bB":
            case "yB": return CreateTileFills(fill, rotation);
            case "portal":
            case "button":
            case "inportal": return CreateTileFills(fill, dataX, dataY);
            case "spikes": return CreateTileFills(fill, dataX);
            default: return new TileFills();

        }
    }

    public String getTileString() {return fill;}
    public Sprite getSprite() {return sprite;}
    public Texture getTexture() {return texture;}
    public float getRotation() {return rotation;}
    public Vector2Int getData() {return new Vector2Int(dataX, dataY);}
    public boolean drawBackground() {return drawBackground;}
    public boolean canWalk() {return canWalk;}
    public String getType() {
        if (texture != null) return "texture";
        else if (sprite != null) return "sprite";
        return "N/A";
    }
}

