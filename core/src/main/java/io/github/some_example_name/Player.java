package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player {

    public final int[][] LEVEL_BOUNDS = new int[][] {{0,0}, {19,14}};
    public Vector2 pos;
    public Texture sprite;
    public boolean moving;

    public Player() {
        pos = new Vector2().setZero();
        sprite = new Texture("char.png");
        moving = false;
    }

    public void gridMove(Vector2 dir)
    {
        moving = true;
        Vector2 start = pos;
        Vector2 end = new Vector2(start.x + dir.x, start.y + dir.y);
        if (end.x < LEVEL_BOUNDS[0][0] || end.x > LEVEL_BOUNDS[1][0] ||
            end.y < LEVEL_BOUNDS[0][1] || end.y > LEVEL_BOUNDS[1][1]) {
            return;
        }
        pos = new Vector2(end.x, end.y);
        moving = false;
    }
    public Vector2 getPos(){
        return pos;
    }
}
