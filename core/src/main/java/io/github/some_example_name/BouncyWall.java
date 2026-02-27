package io.github.some_example_name;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BouncyWall extends TileFills {
    public BouncyWall(int x, int y, float r) { // int x, int y, float r
        fill = "bouncy";
        sprite = new Sprite(LevelDraw.bouncy);
        sprite.setPosition(x*32, y*32);
        sprite.setRotation(r);
    }

    public void rotateWall(float r) {
        float rotation = (this.getRotation() + r)%360;
        this.sprite.setRotation(rotation);
    }
    public int getRotation() {return (int) this.sprite.getRotation();}
}
