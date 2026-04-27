package io.github.some_example_name;

import java.lang.Math;

import org.w3c.dom.Text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DipperBoss {
    public static Sprite sprite;
    static Vector2Int dipPos;
    public static float bossGridTimer = 0f;
    public static float cooldown = 0f;
    public static float cooldownTime = 0.08f;
    static Array<Sprite> magicArray = new Array<>();
    static Array<Integer> count = new Array<>();
    static float bossMaxHealth = 10;
    static float bossCurrentHealth;
    static boolean alive = true;

    DipperBoss() {
        alive = true;
        float damage = 1.3f;
        bossCurrentHealth = bossMaxHealth;
        dipPos = new Vector2Int(19,21);
        sprite = new Sprite(LevelDraw.dipperBossTx);
        pos(dipPos);
    }

    public static void gridMove() {
        if (!alive) return;
        int dir = ranNum();
        level level = LevelTemplates.finalBoss;
        switch(dir) {
            case 1: if (level.level1[dipPos.y+4][dipPos.x].canWalk) dipperMove(0, 1); break;
            case 2: if (level.level1[dipPos.y-1][dipPos.x].canWalk) dipperMove(0, -1); break;
            case 3: if (level.level1[dipPos.y][dipPos.x+5].canWalk) dipperMove(1, 0); break;
            case 4: if (level.level1[dipPos.y][dipPos.x-1].canWalk) dipperMove(-1, 0); break;
            default: break;
        }
    }

    public static void createMagic() {
        if (!alive) return;
        Sprite magic = new Sprite(LevelDraw.magicTx);
        magic.setSize(32, 32);
        magic.setPosition((dipPos.x+1)*32, (dipPos.y+5)*32);
        magicArray.add(magic);
        count.add(0);
        //magic.setOrigin(20, 12.5f);
        //magic.setScale(1f);
        double rand = Math.random() * 360;
        magic.setRotation((int)rand);
        magic.setScale(2f);
    }

    public static void shootMagic() {
        for (int i = magicArray.size - 1; i >= 0; i--) {
            Sprite magic = magicArray.get(i);
            magic.translateX((float) Math.cos(Math.toRadians(magic.getRotation()))*4);
            magic.translateY((float) Math.sin(Math.toRadians(magic.getRotation()))*4);
             if (Player.pSprite.getBoundingRectangle().overlaps(magic.getBoundingRectangle())) {
                Player.dealDamage(0.5f);
                magicArray.removeIndex(i);
                count.removeIndex(i);
            }
             if (magic.getX() < 0 || magic.getX() > (LevelTemplates.finalBoss.rowCount)*32 || magic.getY() < 0 || magic.getY() > LevelTemplates.finalBoss.colCount*32) {
                magicArray.removeIndex(i);
                count.removeIndex(i);
             }
        }
    }

    public static void dipperMove(int x, int y) {
        dipPos.x+=x;
        dipPos.y+=y;
        pos(dipPos);
    }

    public static void teleportPlayer() {

    }

    public static void deleteMagic() {
        magicArray.clear();
        count.clear();
    }

    private static void pos(Vector2Int pos) {
        sprite.setPosition(pos.x*32, pos.y*32);
    }

    public static void drawDipper(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public static void dealDamage(float i) {
        bossCurrentHealth -= i;
        TextBox.updateTextBox("Dipper Health: " + bossCurrentHealth + "/"+bossMaxHealth, 4);
        if (bossCurrentHealth <= 0) alive = false;
    }

    public static int ranNum() {
        int max = 4;
        int min = 1;
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    public static void drawMagic(SpriteBatch batch) {
        for (Sprite magic : magicArray) {
            magic.draw(batch);
        }
    }

    public static void moveLogic() {
        bossGridTimer += Gdx.graphics.getDeltaTime();
        if (bossGridTimer >= 0.2f) {
            gridMove();
            bossGridTimer = 0f;
        }
    }
}
