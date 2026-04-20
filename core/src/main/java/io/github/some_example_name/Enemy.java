package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy {
    private static float maxHealth = 3f;
    private float currentHealth = maxHealth;
    private Sprite sprite = new Sprite(LevelDraw.characterTx);
    private Vector2Int pos;
    private Vector2Int facing;

    public Enemy(float health, int x, int y) {
        maxHealth = health;
        pos = new Vector2Int(x, y);
        facing = new Vector2Int(1, 0);
    }

    public void moveEnemy() {

    }

    public void damageEnemy(float damage) {
        this.currentHealth -= damage;
    }
}
