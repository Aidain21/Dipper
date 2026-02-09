package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {

    public final int[][] LEVEL_BOUNDS = new int[][] {{0,0}, {19,14}};
    public Vector2 pos;
    public Vector2 facing;
    public Texture img;
    public Sprite pSprite;

    public Player() {
        pos = new Vector2(2,7);
        facing = new Vector2(1,0);
        img = new Texture("char.png");
        pSprite = new Sprite(img);
        pSprite.setOrigin(pSprite.getWidth() / 2, pSprite.getHeight() / 2);
    }

    //moves player based on direction inputted and current level
    public void gridMove(Vector2 dir, level curLevel)
    {
        Vector2 end = new Vector2(pos.x + dir.x, pos.y + dir.y);
        if (end.x < LEVEL_BOUNDS[0][0] || end.x > LEVEL_BOUNDS[1][0] ||
        end.y < LEVEL_BOUNDS[0][1] || end.y > LEVEL_BOUNDS[1][1]) {
            return;
        }
        if (end.x < curLevel.colCount && end.y < curLevel.rowCount) {
            switch (curLevel.level1[Math.round(end.y)][Math.round(end.x)]) {
                case 'w':
                    return;
                case 'l':
                    if (dir.x == 0 && dir.y == 1){ curLevel.changeLevel('u');}
                    if (dir.x == 0 && dir.y == -1){ curLevel.changeLevel('d');}
                    if (dir.x == 1 && dir.y == 0){ curLevel.changeLevel('r');}
                    if (dir.x == -1 && dir.y == 0){ curLevel.changeLevel('l');}
                default:
                    break;
            }
        }
        pos = new Vector2(end.x, end.y);

        facing = dir;
        if (dir.x == 0 && dir.y == 1){ pSprite.setRotation(0);}
        if (dir.x == 0 && dir.y == -1){ pSprite.setRotation(180);}
        if (dir.x == 1 && dir.y == 0){ pSprite.setRotation(270);}
        if (dir.x == -1 && dir.y == 0){ pSprite.setRotation(90);}

    }

    public void playerInteract(level curLevel) {
        Vector2 look = new Vector2(pos.x + facing.x, pos.y + facing.y);
        System.out.println();
        System.out.print("I see a ");
        switch (curLevel.level1[Math.round(look.y)][Math.round(look.x)]) {
            case 'w':
                System.out.print("wall! It's solid like a rock.");
                break;
            case 'l':
                System.out.print("level transition! It's a swirly magic portal.");
                break;
            default:
                System.out.print(curLevel.level1[Math.round(look.y)][Math.round(look.x)]);
                System.out.print("! It's cool I guess.");
                break;
        }
    }

    //Draw the player
    public void drawPlayer(SpriteBatch batch) {
        pSprite.setPosition(pos.x * 32, pos.y *32);
        pSprite.draw(batch);
    }
}
