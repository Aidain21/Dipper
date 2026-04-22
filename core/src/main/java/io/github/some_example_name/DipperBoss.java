package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class DipperBoss {
    DipperBoss() {
        float bossMaxHealth = 10;
        float bossCurrentHealth = bossMaxHealth;
        float damage = 1.3f;
        Sprite sprite = new Sprite(LevelDraw.dipperBossTx);
    }

}
