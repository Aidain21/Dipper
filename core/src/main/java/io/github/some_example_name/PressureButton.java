package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class PressureButton extends TileFills{
    boolean pressed;
    static Array<Vector2Int> walls = new Array<>();
    public PressureButton() {
        fill = "pressureButton";
        sprite = new Sprite(LevelDraw.pressureButtonTx);
        canWalk = true;
        movable = true;
        pressed = false;
        //sprite.setPosition(x*32, y*32);
    }
    public void press() {
        this.pressed = true;
        this.sprite.setAlpha(0);
        TextBox.updateTextBox("Pressure Button is Pressed!!!", 1);
        for (int i = 0; i <= walls.size; i++) {
            //level.changeTile(walls.get(i).x, walls.get(i).y, "pressureButton");
        }
    }
    public void unpress() {
        this.sprite.setAlpha(1);
        this.pressed = false;
        TextBox.updateTextBox("Pressure Button no longer Pressed.", 1);
    }

    public static void addTile(int x, int y) {
        Vector2Int tile = new Vector2Int(x, y);
        walls.add(tile);
    }
    //public boolean isPressed() {return this.pressed;}
}
