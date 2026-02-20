package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import static io.github.some_example_name.Main.currentLevel;

public class Bow {
    public Bow() {}
    private final Texture arrowTexture = new Texture("arrow.png");
    private final Array<Sprite> arrowArray = new Array<>();
    public final float cooldownTime = 0.75f;
    public float cooldown = 0f;
    private float time = 0f;

    // Creates arrow, rotates and sets position based on the direction
    public void createArrow(float posX, float posY, char nesw) {
        Sprite arrow = new Sprite(arrowTexture);
        arrow.setSize(100, 24);
        arrow.setPosition(posX*32, posY*32);
        arrowArray.add(arrow);
        arrow.setScale(0.75f);

        switch(nesw) {
            case 'n': arrow.setX(posX*32+9); arrow.rotate(90); break;
            case 'e': arrow.setY(posY*32+7); break;
            case 's': arrow.setX(posX*32); arrow.rotate(270); break;
            case 'w': arrow.setY(posY*32+18); arrow.rotate(180); break;
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
    // stops if it hits a wall
    public void arrowLogic() {
        float delta = Gdx.graphics.getDeltaTime();
        for (int i = arrowArray.size - 1; i >= 0; i--) {
            Sprite arrow = arrowArray.get(i);
            boolean arrowStop = (currentLevel.tileAtWorldPos(arrow.getX(), arrow.getY())=='w' ||
                currentLevel.tileAtWorldPos(arrow.getX(), arrow.getY()) == 'b');
            boolean arrowRicochet = (currentLevel.tileAtWorldPos(arrow.getX(), arrow.getY()) == 'r');
            int arrowRotation = (int) arrow.getRotation();
            if (arrow.getRotation() > 270) arrow.setRotation(arrow.getRotation()-360);
            switch(arrowRotation) {
                case 90: if (!arrowStop) arrow.translateY(400f * delta);
                    if (arrowRicochet) ricochet(arrow, 0);
                    break;
                case 0: if (!arrowStop) arrow.translateX(400f * delta);
                    //if (arrowRicochet) ricochet(arrow, 180);
                    break;
                case 270: if (!arrowStop) arrow.translateY(-400f * delta);
                    //if (arrowRicochet) ricochet(arrow, 0);
                    break;
                case 180: if (!arrowStop) arrow.translateX(-400f * delta);
                    if (arrowRicochet) ricochet(arrow, 0);
                    break;
                default: break;
            }

            // removes arrow after a delay
            if(arrowStop) {
                time += Gdx.graphics.getDeltaTime();
                if (time >= 0.7f) {
                    arrowArray.removeIndex(i);
                    time = 0f;
                }
            }
        }
    }



    public void ricochet(Sprite arrow, float wallRotation) {
        if (arrow.getRotation() == 0 && wallRotation == 270) arrow.rotate(270); // East
        if (arrow.getRotation() == 0 && wallRotation == 180) arrow.rotate(90);
        if (arrow.getRotation() == 90 && wallRotation == 0) arrow.rotate(270); // North
        if (arrow.getRotation() == 90 && wallRotation == 270) arrow.rotate(90);
        if (arrow.getRotation() == 180 && wallRotation == 0) arrow.rotate(90); // West
        if (arrow.getRotation() == 180 && wallRotation == 90) arrow.rotate(270);
        if (arrow.getRotation() == 270 && wallRotation == 90) arrow.rotate(90); // South
        if (arrow.getRotation() == 270 && wallRotation == 180) arrow.rotate(270);
    }

    // Draw
    public void drawArrow(SpriteBatch batch) {
        for (Sprite arrow : arrowArray) {
            arrow.draw(batch);
        }
    }
}
