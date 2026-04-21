package io.github.some_example_name;

import com.badlogic.gdx.Gdx;

public class Button extends TileFills {
    boolean pressed = false;
    boolean lock = false;
    public float timer = 0f;
    public float cooldown = 0.37f;
    public Button() {
        fill = "button";
        texture = LevelDraw.buttonTx;
    }

    public Button(int x, int y) {
        fill = "button";
        texture = LevelDraw.buttonTx;
        dataX = x;
        dataY = y;
    }

    public void press(level curLevel) {
        if (lock) return;
        if (pressed) return;
        texture = LevelDraw.buttonPressTx;
        if (curLevel.level1[dataY][dataX] instanceof SimpleTextures.BouncyWall)
            ((SimpleTextures.BouncyWall) curLevel.level1[dataY][dataX]).wallRotate(90);
        TextBox.updateTextBox("I PRESSED a button!", 1);
        pressed = true;
    }

    public void unpress() {
        this.pressed = false;
        texture = LevelDraw.buttonTx;
    }

    public void setPos(int x, int y) {
        this.dataX = x;
        this.dataY = y;
    }
    public boolean isPressed() {return pressed;}
    public void lock() {lock = true;}
}
