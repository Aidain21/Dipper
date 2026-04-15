package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class PressureButton extends TileFills{
    boolean pressed;
    public PressureButton() {
        fill = "pressureButton";
        sprite = new Sprite(LevelDraw.pressureButtonTx);
        canWalk = true;
        movable = true;
        pressed = false;
        //sprite.setPosition(x*32, y*32);
    }
    public void press() {
        this.pressed = true;
        this.sprite.setAlpha(0);
        TextBox.updateTextBox("Pressure Button is Pressed!!!", 1);
    }
    public void unpress() {
        this.sprite.setAlpha(1);
        this.pressed = false;
        TextBox.updateTextBox("Pressure Button no longer Pressed.", 1);
    }
    //public boolean isPressed() {return this.pressed;}
}
