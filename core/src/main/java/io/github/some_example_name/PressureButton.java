package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class PressureButton extends TileFills{
    boolean pressed;
    public PressureButton(int[] wallX, int[] wallY) {
        fill = "pressureButton";
        sprite = new Sprite(LevelDraw.pressureButtonTx);
        canWalk = true;
        movable = true;
        pressed = false;
        for (int i = 0; i <=4; i++) {
            wallsX[i] = wallX[i];
            wallsY[i] = wallY[i];
        }
    }
    public void press() {
        this.pressed = true;
        this.sprite.setAlpha(0);
        TextBox.updateTextBox("Pressure Button is Pressed!!!"+wallsX[2] + " " + wallsY[2], 1);
        for (int i = 0; i < wallsX.length; i++) {
            Main.currentLevel.changeTile(wallsX[i], wallsY[i], "floor");
        }
    }
    public void unpress() {
        this.sprite.setAlpha(1);
        this.pressed = false;
        TextBox.updateTextBox("Pressure Button no longer Pressed.", 1);
    }
}
