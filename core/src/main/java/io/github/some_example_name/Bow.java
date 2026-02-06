package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Bow {
    public Bow() {}
    Array<Sprite> arr = new Array<>();
    Array<Character> direction = new Array<>();
    public void createArrow(Texture image, float posX, float posY, char nesw) {
        Sprite arrow = new Sprite(image);
        arrow.setX(posX*32);
        arrow.setY(posY*32);
        arr.add(arrow);
        direction.add(nesw);
        switch(nesw) {
            case 'e': arrow.rotate(90);
            case 's': arrow.rotate(90);
            case 'w': arrow.rotate(90);
        }
    }
    public void arrowLogic() {
        float delta = Gdx.graphics.getDeltaTime();
        for (int i = arr.size - 1; i >= 0; i--) {
            Sprite arrow = arr.get(i);
            switch(direction.get(i)) {
                case 'n': arrow.translateY(400f * delta); break;
                case 'e': arrow.translateX(400f * delta); break;
                case 's': arrow.translateY(-400f * delta); break;
                case 'w': arrow.translateX(-400f * delta); break;
                default: break;
            }
            //if (arrow.getY() > 500) arrows.removeIndex(i);
        }
    }
    public void drawArrow(SpriteBatch batch) {
        for (Sprite arrow : arr) {
            arrow.draw(batch);
        }
    }
}
