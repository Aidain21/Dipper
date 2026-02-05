package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {

    public final int[][] LEVEL_BOUNDS = new int[][] {{0,0}, {19,14}};
    public Vector2 pos;
    public Texture sprite;
    public boolean moving;

    public Player() {
        pos = new Vector2(3,3);
        sprite = new Texture("char.png");
        moving = false;
    }

    public void gridMove(Vector2 dir, level curLevel)
    {
        moving = true;
        Vector2 start = pos;
        Vector2 end = new Vector2(start.x + dir.x, start.y + dir.y);
        if (end.x < LEVEL_BOUNDS[0][0] || end.x > LEVEL_BOUNDS[1][0] ||
        end.y < LEVEL_BOUNDS[0][1] || end.y > LEVEL_BOUNDS[1][1]) {
            return;
        }
        if (end.x < curLevel.colCount && end.y < curLevel.rowCount) {
            if (curLevel.level1[Math.round(end.y)][Math.round(end.x)] == 'w') {
                return;
            }
        }
        pos = new Vector2(end.x, end.y);
        moving = false;
    }

    public void drawPlayer(SpriteBatch batch) {
        batch.draw(sprite, pos.x*32,pos.y*32);
    }
}
