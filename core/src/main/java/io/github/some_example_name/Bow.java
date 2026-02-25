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
            case 'n': arrow.translateX(-4); arrow.setRotation(90); break;
            case 'e': arrow.translateY(4); break;
            case 's': arrow.translateX(-3.5f); arrow.setRotation(270); break;
            case 'w': arrow.translateY(4); arrow.setRotation(180); break;
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
            float wall = wallR(arrow);
            char pos = arrowPos(arrow);
            boolean arrowRicochet = shouldRicochet((int) arrow.getRotation(), arrow, wall);
            boolean arrowStop = (!arrowRicochet && pos != 'f' && pos != 'p' && pos != 'l');
            int arrowRotation = (int) arrow.getRotation()%360;
            switch (arrowRotation) {
                case 90: // North
                    if (!arrowStop) {
                        arrow.translateY(400f * delta);
                        if (arrowRicochet) {
                            if (wall == 0) {
                                arrow.setPosition(arrow.getX()-arrow.getX()%32 + 48, arrow.getY()-arrow.getY()%32 + 36);
                                arrow.setRotation(0);

                            }
                            if (wall == 270) {
                                arrow.setPosition(arrow.getX()-arrow.getX()%32 + 15, arrow.getY()-arrow.getY()%32 + 36);
                                arrow.setRotation(180);

                            }
                        }
                    }
                    break;
                case 0: // East
                    if (!arrowStop) {
                        arrow.translateX(400f * delta);
                        if (arrowRicochet) {
                            if (wall== 270) {
                                arrow.setPosition(arrow.getX()-arrow.getX()%32 + 28, arrow.getY()-arrow.getY()%32 - 25);
                                arrow.setRotation(270);
                            }
                            if (wall == 180) {
                                arrow.setPosition(arrow.getX()-arrow.getX()%32 + 28, arrow.getY()-arrow.getY()%32 + 16);
                                arrow.setRotation(90);
                            }
                        }
                    }
                    break;
                case 270: // South
                    if (!arrowStop) {
                        arrow.translateY(-400f * delta);
                        if (arrowRicochet) {
                            if (wall == 180) {
                                arrow.setPosition(arrow.getX()-arrow.getX()%32 + 15, arrow.getY()-arrow.getY()%32 + 4);
                                arrow.setRotation(180);
                            }
                            if (wall == 90) {
                                arrow.setPosition(arrow.getX()-arrow.getX()%32 + 48, arrow.getY()-arrow.getY()%32 + 5);
                                arrow.setRotation(0);
                            }
                        }
                    }
                    break;
                case 180: // West
                    if (!arrowStop) {
                        arrow.translateX(-400f * delta);
                        if (arrowRicochet) {
                            if (wall == 90) {
                                arrow.setPosition(arrow.getX()-arrow.getX()%32 - 5, arrow.getY()-arrow.getY()%32 + 16);
                                arrow.setRotation(90);
                            }
                            if (wall== 0) {
                                arrow.setPosition(arrow.getX()-arrow.getX()%32 - 4, arrow.getY()-arrow.getY()%32 - 17);
                                arrow.setRotation(270);
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

    private boolean shouldRicochet(int rotation, Sprite arrow, float w) {
        if (arrowPos(arrow) == 'r') {
            switch (rotation) {
                case 90: return (w == 0 || w == 270);
                case 0: return (w == 180 || w == 270);
                case 270: return (w== 90 || w == 180);
                case 180: return (w == 0 || w == 90);
                default: break;
            }
        }
        return false;
    }

    private float wallR(Sprite arrow) { return currentLevel.rotationAt(arrow.getX(), arrow.getY())%360;}

    private char arrowPos(Sprite arrow) { return currentLevel.tileAtWorldPos(arrow.getX(), arrow.getY());}

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
