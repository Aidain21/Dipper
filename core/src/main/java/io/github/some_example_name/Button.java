package io.github.some_example_name;

public class Button extends TileFills {
    boolean pressed = false;
    boolean lock = false;
    public int posx = 0;
    public int posy = 0;
    public Button() {
        fill = "button";
        texture = LevelDraw.buttonTx;
    }

    public void press() {
        if (lock) return;
        pressed = !pressed;
        if (pressed) texture = LevelDraw.buttonPressTx;
        else texture = LevelDraw.buttonTx;
        TextBox.updateTextBox("I PRESSED a button!", 1);
    }
    public boolean isPressed() {return pressed;}
    public void lock() {lock = true;}
}
