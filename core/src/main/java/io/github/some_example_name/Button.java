package io.github.some_example_name;

import com.badlogic.gdx.Gdx;

public class Button extends TileFills {
    boolean pressed = false;
    boolean lock = false;
    private float timer = 0.3f;
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
        pressed = true;
        texture = LevelDraw.buttonPressTx;
        ((SimpleTextures.BouncyWall)curLevel.level1[dataY][dataX]).getSprite().rotate(270);
        TextBox.updateTextBox("I PRESSED a button!", 1);
    }

    public void unpress() {
        pressed = false;
        texture = LevelDraw.buttonTx;
    }

    public void setPos(int x, int y) {
        this.dataX = x;
        this.dataY = y;
    }
    public boolean isPressed() {return pressed;}
    public void lock() {lock = true;}
}
