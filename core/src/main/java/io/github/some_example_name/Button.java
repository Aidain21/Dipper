package io.github.some_example_name;

public class Button extends TileFills {
    boolean pressed = false;

    public Button() {
        fill = "button";
    }

    public Boolean pressedStatus() {
        return this.pressed;
    }

    public void isPressed() {
        pressed = !pressed;
    }
}
