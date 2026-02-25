package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Button extends TileFills {
    boolean pressed = false;
    Sprite sprite;
    public Button(float x, float y, float r) {
        fill = "button";
        sprite = new Sprite(LevelDraw.buttonBTexture);
        sprite.setPosition(x*32, y*32);
        sprite.setRotation(r);
    }

    public void isPressed() {
        pressed = true;
        TextBox.text[1] = "I PRESSED a button!";
    }
    public Sprite getSprite() {return this.sprite;}
}
