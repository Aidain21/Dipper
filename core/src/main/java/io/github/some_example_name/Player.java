package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Objects;

public class Player {

    public final int[][] LEVEL_BOUNDS = new int[][] {{0,0}, {29,19}};
    public Vector2Int pos;
    public Vector2Int facing;
    public static Sprite pSprite;
    public static boolean playerLock = false;
    public static float lockTimer = 0f;
    public static float distance = 0f;
    public static boolean alive = true;
    public static boolean inMap=false;
    public static float playerMaxHealth = 3f;
    public static float playerHealth = playerMaxHealth;
    public static boolean playerFalling = false;
    public static boolean playerSliding = false;
    public static int originalX = 0;
    public static int originalY = 0;
    Sprite pFront = new Sprite(LevelDraw.characterFront);
    Sprite pBack = new Sprite(LevelDraw.characterBack);
    Sprite pLeft = new Sprite(LevelDraw.characterLeft);
    Sprite pRight = new Sprite(LevelDraw.characterRight);


    public Player() {
        pos = new Vector2Int(2,7);
        facing = new Vector2Int(1,0);
        pSprite = new Sprite(LevelDraw.characterFront);
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
            pSprite = pBack;
        }
        if (dir.x == 0 && dir.y == -1) {
            pSprite = pFront;
        }
        if (dir.x == 1 && dir.y == 0) {
            pSprite = pRight;
        }
        if (dir.x == -1 && dir.y == 0) {
            pSprite = pLeft;
        }

        //stops player from walking off-screen
        if (end.x < LEVEL_BOUNDS[0][0] || end.x > LEVEL_BOUNDS[1][0] ||
            end.y < LEVEL_BOUNDS[0][1] || end.y > LEVEL_BOUNDS[1][1]) {
            return;
        }

        //walking interactions based on what is stored in the tile's data
        //such as a wall stopping the walking from being done
        //or a portal teleporting you when you step on it
        if (end.x < curLevel.level1[0].length && end.y < curLevel.level1.length) {
            TileFills nextTile = curLevel.level1[end.y][end.x];
            if (!nextTile.canWalk()) return;
            switch(nextTile.getTileString()) {
                case "portal": pos = curLevel.changeLevel((SimpleTextures.Portal) nextTile); return;
                case "inportal": pos = ((SimpleTextures.InLevelPortal) nextTile).newPos(); return;
                case "spikes": ((SimpleTextures.Spikes) nextTile).spiked(); break;
                case "void": ((SimpleTextures.Void) nextTile).fall(pos.x, pos.y); break;
                case "iceFloor":  ((SimpleTextures.IceFloor) nextTile).slide(); return;
                default: break;
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
                ((Button) curLevel.getLevel()[look.y][look.x]).press();
                TextBox.text[0] = "I see a button!";
                break;
            case " ":
            case "floor": break;
            case "wall": TextBox.text[0] = "I see a wall! It's solid like a rock."; break;
            case "portal": TextBox.text[0] = "I see a level transition! It's a swirly magic portal."; break;
            case "box": TextBox.text[0] = "I see a block! I probably just pushed it.";
                ((Box) curLevel.getLevel()[look.y][look.x]).tryPush(look.x, look.y, facing.x, facing.y, curLevel);
                break;
            case "lever": TextBox.text[0] = "I see a lever! It probably added something new!";
                ((Lever) curLevel.getLevel()[look.y][look.x]).onFlip(2,2,curLevel);
                break;
            case "bouncy": TextBox.text[0] = "A Bouncy Wall! Maybe this could deflect something!"; break;
            case "rB":
            case "gB":
            case "bB":
            case "yB": TextBox.text[0] = "A Colored Button! It must be linked to something!"; break;
            case "rGate":
            case "gGate":
            case "bGate":
            case "yGate": TextBox.text[0] = "A Colored Gate! It must be linked to something!"; break;
            case "pressureButton": TextBox.text[0] = "A Pressure Button! I need something heavy!"; break;
            case "spikes": TextBox.text[0] = "Spikes!! I shouldn't touch them."; break;
            case "void": TextBox.text[0] = "... I shouldn't fall in there."; break;
            default: break;
        }
    }

    public static void dealDamage(float damage) {
        playerHealth -= damage;
        if (playerHealth <= 0) alive = false;
    }
    public boolean isAlive() {return alive;}

    public void playerLogic() {
        if (!alive) {
            TextBox.clearText();
            TextBox.text[0] = "You Died!";
            TextBox.text[1] = "Press 0 to restart.";
        }
        TextBox.textRight[2] = ("Health: " + playerHealth);
        TextBox.text[2] = pos.x + " " + pos.y + " " + pSprite.getX() + " " + pSprite.getY();
    }

    public void locked(level curLevel) {
        lockTimer -= Gdx.graphics.getDeltaTime();
        distance += Gdx.graphics.getDeltaTime();
        if (playerFalling && (lockTimer-0.5f) >= 0) {
            pSprite.setPosition((pos.x + facing.x)*32, (pos.y + facing.y)*32);
            pSprite.setScale(lockTimer-0.5f);
        }
        if (playerSliding && (lockTimer) > 0) {
            pSprite.setPosition((pos.x + facing.x * distance * 4) * 32, (pos.y + facing.y * distance * 4) * 32);
        }
        if(inMap)
            return;

        if (lockTimer <= 0) {
            if (playerFalling) pos = new Vector2Int(originalX, originalY);
            playerLock = false;
            playerFalling = false;
            if (playerSliding) {
                pos = new Vector2Int(Math.round(pSprite.getX()/32),Math.round(pSprite.getY()/32));
                pSprite.setPosition(pos.x * 32, pos.y *32);
            }
            playerSliding = false;
            distance = 0f;
            pSprite.setScale(1f);

            if (Objects.equals(curLevel.level1[pos.y][pos.x].fill, "iceFloor") &&
            curLevel.level1[pos.y + facing.y][pos.x + facing.x].canWalk) {
                ((SimpleTextures.IceFloor) curLevel.level1[pos.y][pos.x]).slide();
            }
        }
    }

    public void playerRestart(level curLevel) {
        alive = true;
        Main.textBox = new TextBox();
        pos.x = curLevel.getSpawnRow();
        pos.y = curLevel.getSpawnCol();
        playerHealth = playerMaxHealth;
    }

    public void movePlayer(int x, int y) {
        pos.x = x;
        pos.y = y;
    }

    //Draw the player
    public void drawPlayer(SpriteBatch batch) {
        if (!playerSliding) {
            pSprite.setPosition(pos.x * 32, pos.y *32);
        }
        pSprite.draw(batch);
    }
}

