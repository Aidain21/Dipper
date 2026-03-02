package io.github.some_example_name;

public class Button extends TileFills {
    boolean pressed = false;
    boolean lock = false;
    public Button() {
        fill = "button";
        texture = LevelDraw.buttonTx;
    }

    public void press() {
        if (lock) return;
        pressed = !pressed;
        if (pressed) texture = LevelDraw.buttonPressTx;
        else texture = LevelDraw.buttonTx;
        TextBox.text[1] = "I PRESSED a button!";
    }
    public boolean isPressed() {return pressed;}
    public void lock() {lock = true;}
}
