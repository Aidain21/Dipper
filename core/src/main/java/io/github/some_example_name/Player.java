package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Objects;

public class Player {

    public final int[][] LEVEL_BOUNDS = new int[][] {{0,0}, {49,29}};
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
        TextBox.updateTextBox("Health: " + playerHealth,5);
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
                case "void": ((SimpleTextures.Void) nextTile).fall(); break;
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
                TextBox.updateTextBox("I see a button!",0);
                break;
            case " ":
            case "floor": break;
            case "wall": TextBox.updateTextBox("I see a wall! It's solid like a rock.",0); break;
            case "portal": TextBox.updateTextBox("I see a level transition! It's a swirly magic portal.",0); break;
            case "box": TextBox.updateTextBox("I see a block! I probably just pushed it.",0);
                ((Box) curLevel.getLevel()[look.y][look.x]).tryPush(look.x, look.y, facing.x, facing.y, curLevel);
                break;
            case "lever": TextBox.updateTextBox("I see a lever! It probably added something new!",0);
                ((Lever) curLevel.getLevel()[look.y][look.x]).onFlip(2,2,curLevel);
                break;
            case "bouncy": TextBox.updateTextBox("A Bouncy Wall! Maybe this could deflect something!",0); break;
            case "rB":
            case "gB":
            case "bB":
            case "yB": TextBox.updateTextBox("A Colored Button! It must be linked to something!",0); break;
            case "rGate":
            case "gGate":
            case "bGate":
            case "yGate": TextBox.updateTextBox("A Colored Gate! It must be linked to something!",0); break;
            case "pressureButton": TextBox.updateTextBox("A Pressure Button! I need something heavy!",0); break;
            case "spikes": TextBox.updateTextBox("Spikes!! I shouldn't touch them.",0); break;
            case "void": TextBox.updateTextBox("... I shouldn't fall in there.",0); break;
            default: break;
        }
    }

    public static void dealDamage(float damage) {
        playerHealth -= damage;
        TextBox.updateTextBox("Health: " + playerHealth,5);
        if (playerHealth <= 0) alive = false;
    }
    public boolean isAlive() {return alive;}

    public void playerLogic() {
        if (!alive) {
            TextBox.clearText();
            TextBox.updateTextBox("You Died!",0);
            TextBox.updateTextBox("Press 0 to restart.", 1);
        }
        //TextBox.text[2] = pos.x + " " + pos.y + " " + pSprite.getX() + " " + pSprite.getY();
    }

    public void locked(level curLevel) {
        lockTimer -= Gdx.graphics.getDeltaTime();
        distance += Gdx.graphics.getDeltaTime();
        if (playerFalling && (lockTimer-0.5f) >= 0) {
            pSprite.setPosition((pos.x + facing.x)*32, (pos.y + facing.y)*32);
            pSprite.setScale(lockTimer-0.5f);
        }
        if (playerSliding && (lockTimer) > 0) {
            pSprite.setPosition((pos.x + facing.x * distance * 8) * 32, (pos.y + facing.y * distance * 8) * 32);
        }
        if(inMap)
            return;

        if (lockTimer <= 0) {
            if (playerFalling) pos = new Vector2Int(curLevel.getSpawnRow(), curLevel.getSpawnCol());
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
            if (Objects.equals(curLevel.level1[pos.y][pos.x].fill, "void")) ((SimpleTextures.Void) curLevel.level1[pos.y][pos.x]).fall();
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

