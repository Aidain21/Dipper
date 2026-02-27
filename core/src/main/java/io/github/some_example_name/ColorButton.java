package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class ColorButton extends TileFills {
    boolean pressed = false;
    Sprite sprite;
    public ColorButton(float x, float y, String color, float r) {
        fill = "colorButton";
        switch(color) {
            case "b": sprite = new Sprite(LevelDraw.bButtonTx); break;
            case "g": sprite = new Sprite(LevelDraw.gButtonTx); break;
            case "y": sprite = new Sprite(LevelDraw.yButtonTx); break;
            default: sprite = new Sprite(LevelDraw.rButtonTx); break;
        }
        sprite.setPosition(x*32, y*32);
        sprite.setRotation(r);
    }

    public void isPressed() {
        pressed = true;
        TextBox.text[1] = "Colored Button!!";
    }
    public Sprite getSprite() {return this.sprite;}
}
