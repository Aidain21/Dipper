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
        arrow.setOrigin(20, 12.5f);
        arrow.setScale(0.75f);

        switch(nesw) {
            case 'n': arrow.translateX(-4); arrow.rotate(90); break;
            case 'e': arrow.translateY(4); break;
            case 's': arrow.translateX(-3.5f); arrow.rotate(270); break;
            case 'w': arrow.translateY(4); arrow.rotate(180); break;
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

    // Arrow Logic: Makes arrow move in a direction
    // + Collision
    // + Ricochet
    // + (eventually) buttons and other interactions
    public void arrowLogic() {
        float delta = Gdx.graphics.getDeltaTime();
        for (int i = arrowArray.size - 1; i >= 0; i--) {
            Sprite arrow = arrowArray.get(i);
            boolean arrowRicochet = (arrowPos(arrow).equals("1") || arrowPos(arrow).equals("2") || arrowPos(arrow).equals("3") || arrowPos(arrow).equals("4"));
            boolean should = shouldRicochet((int) arrow.getRotation(), arrow);
            boolean arrowStop = (arrowPos(arrow).equals("wall") || arrowPos(arrow).equals("box") || (arrowRicochet && !should));
            int arrowRotation = (int) arrow.getRotation();
            if (arrow.getRotation() > 270) arrow.setRotation(arrow.getRotation() - 360);
            switch (arrowRotation) {
                case 90:
                    if (!arrowStop) {
                        arrow.translateY(400f * delta);
                        if (arrowRicochet) {
                            if (arrowPos(arrow).equals("1")) {
                                arrow.translate(25,15);
                                arrow.rotate(270);
                            }
                            if (arrowPos(arrow).equals("4")) {
                                arrow.translate(-16,15);
                                arrow.rotate(90);
                            }
                        }
                    }
                    break;
                case 0:
                    if (!arrowStop) {
                        arrow.translateX(400f * delta);
                        if (arrowRicochet) {
                            if (arrowPos(arrow).equals("4")) {
                                arrow.translate(7, -25);
                                arrow.rotate(270);
                            }
                            if (arrowPos(arrow).equals("3")) {
                                arrow.translate(7, 16);
                                arrow.rotate(90);
                            }
                        }
                    }
                    break;
                case 270:
                    if (!arrowStop) {
                        arrow.translateY(-400f * delta);
                        if (arrowRicochet) {
                            if (arrowPos(arrow).equals("3")) {
                                arrow.translate(-16,-7);
                                arrow.rotate(270);
                            }
                            if (arrowPos(arrow).equals("2")) {
                                arrow.translate(25, -7);
                                arrow.rotate(90);
                            }
                        }
                    }
                    break;
                case 180:
                    if (!arrowStop) {
                        arrow.translateX(-400f * delta);
                        if (arrowRicochet) {
                            if (arrowPos(arrow).equals("2")) {
                                arrow.translate(-16, 16);
                                arrow.rotate(270);
                            }
                            if (arrowPos(arrow).equals("1")) {
                                arrow.translate(-16, -25);
                                arrow.rotate(90);
                            }
                        }
                    }
                    break;
                default:
                    break;
            }

            if (arrowStop) {
                removeArrow(i);
            }
        }
    }

    private boolean shouldRicochet(int rotation, Sprite arrow) {
        switch(rotation) {
            case 90: return (arrowPos(arrow).equals("1") || arrowPos(arrow).equals("4"));
            case 0: return (arrowPos(arrow).equals("3") || arrowPos(arrow).equals("4"));
            case 270: return (arrowPos(arrow).equals("2") || arrowPos(arrow).equals("3"));
            case 180: return (arrowPos(arrow).equals("1") || arrowPos(arrow).equals("2"));
            default: return false;
        }
    }

    private String arrowPos(Sprite arrow) {
        return currentLevel.tileAtWorldPos(arrow.getX(), arrow.getY());
    }

    // Removes one arrow at a time
    private void removeArrow(int i) {
        time += Gdx.graphics.getDeltaTime();
        if (time >= 0.7f) {
            arrowArray.removeIndex(i);
            time = 0f;
        }
    }

    // Deletes all arrows currently on screen
    public void deleteArrows() {
        for (int i = arrowArray.size - 1; i >= 0; i--) {
            arrowArray.removeIndex(i);
        }
    }

    // Draws arrows
    public void drawArrow(SpriteBatch batch) {
        for (Sprite arrow : arrowArray) {
            arrow.draw(batch);
        }
    }
}
