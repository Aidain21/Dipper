package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class ColorButton extends TileFills {
    boolean pressed = false;
    public ColorButton(String color, float r) {
        fill = color;
        switch(color) {
            case "bB": sprite = new Sprite(LevelDraw.bButtonTx); break;
            case "gB": sprite = new Sprite(LevelDraw.gButtonTx); break;
            case "yB": sprite = new Sprite(LevelDraw.yButtonTx); break;
            case "rB": sprite = new Sprite(LevelDraw.rButtonTx); break;
            default: break;
        }
        rotation = r;
        sprite.setRotation(r);
    }

    public void press() {
        pressed = !pressed;
        if (pressed) {
            switch (this.fill) {
                case "rB": TextBox.updateTextBox("RED Button!!", 1); break;
                case "gB": TextBox.updateTextBox("GREEN Button!!", 1); break;
                case "bB":  TextBox.updateTextBox("BLUE Button!!", 1); break;
                case "yB": TextBox.updateTextBox("YELLOW Button!!", 1); break;
                default: break;
            }
            this.sprite.setColor(.5f, .5f, .5f,1f);
        }
        else this.sprite.setColor(1f,1f,1f,1f);
    }
    public boolean isPressed() {return pressed;}
}
