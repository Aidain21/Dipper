package io.github.some_example_name;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BouncyWall extends TileFills {
    public BouncyWall(float r) {
        fill = "bouncy";
        sprite = new Sprite(LevelDraw.bouncy);
        sprite.setRotation(r);
    }
    public BouncyWall(int x, int y, float r) {
        fill = "bouncy";
        sprite = new Sprite(LevelDraw.bouncy);
        sprite.setRotation(r);
        sprite.setPosition(x*32, y*32);
    }
}
