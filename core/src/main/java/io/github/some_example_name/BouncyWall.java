package io.github.some_example_name;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BouncyWall extends TileFills {
    public BouncyWall(float r) {
        fill = "bouncy";
        sprite = new Sprite(LevelDraw.bouncy);
        sprite.setRotation(r);
    }

    public void rotateWall(float r) {
        float rotation = (this.getRotation() + r)%360;
        this.sprite.setRotation(rotation);
    }
    public int getRotation() {return (int) this.sprite.getRotation();}
}
