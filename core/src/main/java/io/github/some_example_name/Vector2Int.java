package io.github.some_example_name;

import com.badlogic.gdx.math.Vector2;

public class Vector2Int {
    public int x;
    public int y;

    public Vector2Int() {
        this(0, 0);
    }

    public Vector2Int(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public Vector2Int(Vector2 vector) {
        this.x = (int) vector.x;
        this.y = (int) vector.y;
    }
}
