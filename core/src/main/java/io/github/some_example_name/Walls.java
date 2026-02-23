package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
        Sprite sprite;
        public Bouncy (char i) { // int x, int y, float r
            fill = i;
            sprite = new Sprite(bouncy);
            //sprite.setPosition(x, y);
            //sprite.setRotation(r);
        }
//        public static void rotateWall(float r) {
//            this.sprite.rotate((this.sprite.getRotation + r)%360);
//        }
        //public void drawWall(SpriteBatch batch) {this.sprite.draw(batch);}
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
