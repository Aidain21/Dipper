package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class PressureButton extends TileFills{
    boolean pressed;
    Sprite sprite;
    public PressureButton(int x, int y) {
        fill = "pressureButton";
        sprite = new Sprite(LevelDraw.pressureButtonTx);
        movable = true;
        pressed = false;
        sprite.setPosition(x*32, y*32);
    }
    public void press() {
        this.pressed = true;
        this.sprite.setAlpha(0);
        TextBox.text[1] = "Pressure Button is Pressed!!!";
    }
    public void unpress() {
        this.sprite.setAlpha(1);
        this.pressed = false;
        TextBox.text[1] = "Pressure Button no longer Presseed.";
    }
    //public boolean isPressed() {return this.pressed;}
    public Sprite getSprite() {return this.sprite;}
}
