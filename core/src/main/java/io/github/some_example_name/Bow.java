package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Bow {
    public Bow() {}
    private final Texture arrowTexture = new Texture("arrow.png");
    Array<Sprite> arrowArray = new Array<>();
    Array<Character> direction = new Array<>();
    public final float cooldownTime = 0.5f;
    public float cooldown = 0f;

    // Creates arrow, rotates and sets position based on the direction it is fired
    public void createArrow(float posX, float posY, char nesw) {
        Sprite arrow = new Sprite(arrowTexture);
        arrow.setSize(100, 24);
        arrow.setX(posX*32);
        arrow.setY(posY*32);
        arrowArray.add(arrow);
        direction.add(nesw);
        switch(nesw) {
            case 'n': arrow.setX(posX*32+12); arrow.rotate(90); break;
            case 'e': arrow.setY(posY*32+6); break;
            case 's': arrow.setX(posX*32-6); arrow.rotate(270); break;
            case 'w': arrow.setY(posY*32+22); arrow.rotate(180); break;
            default: break;
        }
    }

    // Bow input
    public void bowInput(float pX, float pY, char nesw) {
        if (cooldown >= cooldownTime) {
            createArrow(pX, pY, nesw);
            cooldown = 0f;
        }
    }

    // Makes arrow move in a straight line
    public void arrowLogic() {
        float delta = Gdx.graphics.getDeltaTime();
        for (int i = arrowArray.size - 1; i >= 0; i--) {

            Sprite arrow = arrowArray.get(i);
            switch(direction.get(i)) {
                case 'n': arrow.translateY(400f * delta); break;
                case 'e': arrow.translateX(400f * delta); break;
                case 's': arrow.translateY(-400f * delta); break;
                case 'w': arrow.translateX(-400f * delta); break;
                default: break;
            }
            if(arrow.getX() > 450 || arrow.getX() < 25 || arrow.getY() > 400 || arrow.getY() < 25) {
                arrowArray.removeIndex(i);
                direction.removeIndex(i);
            }
        }
    }

    // Draw
    public void drawArrow(SpriteBatch batch) {
        for (Sprite arrow : arrowArray) {
            arrow.draw(batch);
        }
    }
}
