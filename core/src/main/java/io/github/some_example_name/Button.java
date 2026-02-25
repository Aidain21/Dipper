package io.github.some_example_name;

public class Button extends TileFills {
    boolean pressed = false;

    public Button() {
        fill = "button";
    }

    public void isPressed() {
        pressed = true;
        TextBox.text[1] = "I PRESSED a button!";
    }
}
