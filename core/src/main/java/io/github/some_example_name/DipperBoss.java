package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DipperBoss {
    static Sprite sprite;
    static Vector2Int dipPos;
    DipperBoss() {
        float bossMaxHealth = 10;
        float bossCurrentHealth = bossMaxHealth;
        float damage = 1.3f;
        dipPos = new Vector2Int(19,21);
        sprite = new Sprite(LevelDraw.dipperBossTx);
        pos(dipPos);
    }

    public static void dipperMove(int x, int y) {
        dipPos.x+=x;
        dipPos.y+=y;
        pos(dipPos);
    }

    private static void pos(Vector2Int pos) {
        sprite.setPosition(pos.x*32, pos.y*32);
    }

    public static void drawDipper(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
