package io.github.some_example_name;
import com.badlogic.gdx.graphics.Texture;

public class Button extends TileFills {
    boolean pressed = false;
    Texture texture;
    public Button() {
        texture = new Texture("button.png");
        fill = "button";
    }

    public void isPressed() {
        pressed = true;
        texture = new Texture("buttonR.png");
        TextBox.text[1] = "I PRESSED a button!";
    }

    public Texture getTexture() {
        return this.texture;
    }
}
