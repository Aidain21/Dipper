package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Walls extends TileFills{
    public static Texture brickWallTexture = new Texture("brickWall.png");
    public static Texture bouncyWall = new Texture("bouncyWall.png");
    public static Sprite bouncy = new Sprite(bouncyWall);
    public Walls(){ }

    // Brick Wall
    public static class Wall extends TileFills {
        public Wall() {fill = 'w';}
    }

    // Bouncy Walls
    public static class Bouncy extends TileFills {
        // int rotation;
        public Bouncy (char i) {
            fill = i;
            //this.rotation = r;
        }
//        public static void rotateWall() {
//            this.
//        }
    }
    public static int getWallRotation(char i) {
        switch(i) {
            case '2': return 90;
            case '3': return 180;
            case '4': return 270;
            default: return 0;
        }
    }
}
