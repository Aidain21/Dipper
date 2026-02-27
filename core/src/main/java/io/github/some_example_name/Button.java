package io.github.some_example_name;

public class Button extends TileFills {
    boolean pressed = false;
    public Button() {
        fill = "button";
        texture = LevelDraw.buttonTx;
    }

    public void isPressed() {
        pressed = !pressed;
        if (pressed)
            texture = LevelDraw.buttonPressTx;
        else texture = LevelDraw.buttonTx;
        TextBox.text[1] = "I PRESSED a button!";
    }
}
