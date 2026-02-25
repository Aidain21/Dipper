package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class BouncyWall extends TileFills {
    Sprite sprite;
    public BouncyWall(int x, int y, float r) { // int x, int y, float r
        fill = "bouncy";
        sprite = new Sprite(LevelDraw.bouncy);
        sprite.setPosition(x*32, y*32);
        sprite.setRotation(r);
    }

    public void rotateWall(float r) {
        this.sprite.rotate(r);
        if (this.sprite.getRotation() >= 270) this.sprite.setRotation(this.sprite.getRotation()%360);
    }
    public int getRotation() {return (int) this.sprite.getRotation();}
    public Sprite getSprite() {return this.sprite;}
}
