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
        sprite.setRotation(r);
    }
    public ColorButton(int x, int y, String color, float r) {
        fill = color;
        switch(color) {
            case "bB": sprite = new Sprite(LevelDraw.bButtonTx); break;
            case "gB": sprite = new Sprite(LevelDraw.gButtonTx); break;
            case "yB": sprite = new Sprite(LevelDraw.yButtonTx); break;
            case "rB": sprite = new Sprite(LevelDraw.rButtonTx); break;
            default: break;
        }
        sprite.setPosition(x*32, y*32);
        sprite.setRotation(r);
    }

    public void press() {
        pressed = !pressed;
        //if (pressed) {
            switch (this.fill) {
                case "rB": TextBox.text[1] = "RED Button!!"; break;
                case "gB": TextBox.text[1] = "GREEN Button!!"; break;
                case "bB": TextBox.text[1] = "BLUE Button!!"; break;
                case "yB": TextBox.text[1] = "YELLOW Button!!"; break;
                default: break;
            }
        //}
    }
    public boolean isPressed() {return pressed;}
}
