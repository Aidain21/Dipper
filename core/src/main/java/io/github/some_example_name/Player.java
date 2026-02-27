package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {

    public final int[][] LEVEL_BOUNDS = new int[][] {{0,0}, {29,19}};
    public Vector2Int pos;
    public Vector2Int facing;
    public Texture img;
    public Sprite pSprite;
    public int health=5;

    public Player() {
        pos = new Vector2Int(2,7);
        facing = new Vector2Int(1,0);
        img = new Texture("char.png");
        pSprite = new Sprite(img);
        pSprite.setOrigin(pSprite.getWidth() / 2, pSprite.getHeight() / 2);
    }

    //moves player based on direction inputted and current level
    public void gridMove(Vector2 direct, level curLevel) {

        //sets where the player is trying to move to.
        Vector2Int dir = new Vector2Int(direct);
        Vector2Int end = new Vector2Int(pos.x + dir.x, pos.y + dir.y);

        //changes both player sprites direction and interact direction
        facing = dir;
        if (dir.x == 0 && dir.y == 1) {
            pSprite.setRotation(0);
        }
        if (dir.x == 0 && dir.y == -1) {
            pSprite.setRotation(180);
        }
        if (dir.x == 1 && dir.y == 0) {
            pSprite.setRotation(270);
        }
        if (dir.x == -1 && dir.y == 0) {
            pSprite.setRotation(90);
        }

        //stops player from walking off-screen
        if (end.x < LEVEL_BOUNDS[0][0] || end.x > LEVEL_BOUNDS[1][0] ||
            end.y < LEVEL_BOUNDS[0][1] || end.y > LEVEL_BOUNDS[1][1]) {
            return;
        }

        //walking interactions based on what is stored in the tile's data
        //such as a wall stopping the walking from being done
        //or a portal teleporting you when you step on it
        // break = no collision | return = collision
        if (end.x < curLevel.colCount && end.y < curLevel.rowCount) {
            switch (curLevel.level1[end.y][end.x].getTileString()) {
                case "portal":
                    pos = curLevel.changeLevel((Portal) curLevel.level1[end.y][end.x]);
                    return;
                case "inportal":
                    pos=((InLevelPortal) curLevel.level1[end.y][end.x]).newPos();
                    return;

                // Place below for no collision

                case "button":
                case "pressureButton":
                case "floor":
                    break;
                case "spikes": TextBox.text[0] = "That's a spike! I shouldn't step on that!";;
                    health-=((Spikes) curLevel.getLevel()[end.y][end.x]).getDamage();
                    if(health<=0)
                        System.out.println("you died :(");
                    break;
                default: return;
            }
        }

        //sets players position if nothing else was done to stop it.
        pos = new Vector2Int(end.x, end.y);
    }

    //all of these only happen when the player presses E.
    public void playerInteract(level curLevel) {

        //selects the tile one ahead of the direction the player is facing
        Vector2Int look = new Vector2Int(pos.x + facing.x, pos.y + facing.y);

        //based on what string is stored in the selected tiles' data
        //switches what happens when you interact
        //You can use TextBox.text[0] = "string" to change the dialogue line
        switch (curLevel.level1[look.y][look.x].getTileString()) {
            case "button":
                ((Button) curLevel.getLevel()[look.y][look.x]).isPressed();
                TextBox.text[0] = "I see a button!";
                break;
            case " ":
            case "floor": break;
            case "wall": TextBox.text[0] = "I see a wall! It's solid like a rock."; break;
            case "level": TextBox.text[0] = "I see a level transition! It's a swirly magic portal."; break;
            case "box": TextBox.text[0] = "I see a block! I probably just pushed it.";
                ((Box) curLevel.level1[look.y][look.x]).tryPush(look.x, look.y, facing.x, facing.y, curLevel);
                break;
            case "lever":
                //((BouncyWall) curLevel.getLevel()[3][6]).rotateWall(90);
                ((Lever) curLevel.getLevel()[look.y][look.x]).onFlip(2,2,curLevel);
                TextBox.text[0] = "I see a lever! It probably added something new!";
                break;
            case "bouncy": TextBox.text[0] = "A Bouncy Wall! Maybe this could deflect something!"; break;
            case "colorButton": TextBox.text[0] = "A Colored Button! It must be linked to something!"; break;
            case "pressureButton": TextBox.text[0] = "A Pressure Button! I need something heavy!"; break;
            default: break;
        }
    }

    //Draw the player
    public void drawPlayer(SpriteBatch batch) {
        pSprite.setPosition(pos.x * 32, pos.y *32);
        pSprite.draw(batch);
    }
}

