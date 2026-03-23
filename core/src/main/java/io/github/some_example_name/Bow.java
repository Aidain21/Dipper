package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Bow {
    public Bow() {}
    private final Texture arrowTexture = new Texture("arrow.png");
    private final Array<Sprite> arrowArray = new Array<>();
    public final float cooldownTime = 0.7f;
    public float cooldown = 0f;
    private final Array<Float> timer = new Array<>();
    private final Array<Integer> count = new Array<>();
    private final Array<Boolean> hasPressed = new Array<>();

    // Creates arrow, rotates and sets position based on the direction
    public void createArrow(float posX, float posY, char nesw) {
        Sprite arrow = new Sprite(arrowTexture);
        arrow.setSize(100, 24);
        arrow.setPosition(posX*32, posY*32);
        arrowArray.add(arrow);
        count.add(0);
        timer.add(0f);
        hasPressed.add(false);
        arrow.setOrigin(20, 12.5f);
        arrow.setScale(0.5f);
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
    // + Button & other Interactions
    public void arrowLogic(level curLvl) {
        float delta = Gdx.graphics.getDeltaTime();
        for (int i = arrowArray.size - 1; i >= 0; i--) {
            Sprite arrow = arrowArray.get(i);
            float wall = curLvl.rotationAt(arrow.getX(), arrow.getY())%360;
            String pos = curLvl.tileAtWorldPos(arrow.getX(), arrow.getY());
            TileFills tile = curLvl.tileObjectAt(arrow.getX(), arrow.getY());
            boolean arrowRicochet = shouldRicochet(pos, (int) arrow.getRotation(), wall);
            boolean arrowStop = (!arrowRicochet && !pos.equals("floor") && !pos.equals("portal") && !pos.equals("inportal")
                && !pos.equals("button") && !pos.equals("pressureButton") && !pos.equals("void")
                && !((pos.equals("rGate")||pos.equals("bGate")||pos.equals("gGate")||pos.equals("yGate")) && ((SimpleTextures.ColorGate) tile).open));
            TileFills f = curLvl.level1[Math.round(arrow.getY()/32)][Math.round(arrow.getX()/32)];
            if (f instanceof ColorButton && !hasPressed.get(i)) {
                ((ColorButton) f).press();
                hasPressed.set(i, true);
            }
            int arrowRotation = (int) arrow.getRotation()%360;
            int ric = 20;
            switch (arrowRotation) {
                case 90: // North
                    if (arrowStop) break;
                    arrow.translateY(400f * delta);
                    if (!arrowRicochet) break;
                    count.set(i, count.get(i)+1);
                    if (count.get(i) >= ric) break;
                    if (wall == 0) {
                        arrow.setPosition(arrow.getX()-arrow.getX()%32 + 48, arrow.getY()-arrow.getY()%32 + 36);
                        arrow.setRotation(0);
                    }
                    if (wall == 270) {
                        arrow.setPosition(arrow.getX()-arrow.getX()%32 + 15, arrow.getY()-arrow.getY()%32 + 36);
                        arrow.setRotation(180);
                    }
                    break;
                case 0: // East
                    if (arrowStop) break;
                    arrow.translateX(400f * delta);
                    if (!arrowRicochet) break;
                    count.set(i, count.get(i)+1);
                    if (count.get(i) >= ric) break;
                    if (wall== 270) {
                        arrow.setPosition(arrow.getX()-arrow.getX()%32 + 28, arrow.getY()-arrow.getY()%32 - 25);
                        arrow.setRotation(270);
                    }
                    if (wall == 180) {
                        arrow.setPosition(arrow.getX()-arrow.getX()%32 + 28, arrow.getY()-arrow.getY()%32 + 16);
                        arrow.setRotation(90);
                    }
                    break;
                case 270: // South
                    if (arrowStop) break;
                    arrow.translateY(-400f * delta);
                    if (!arrowRicochet) break;
                    count.set(i, count.get(i)+1);
                    if (count.get(i) >= ric) break;
                    if (wall == 180) {
                        arrow.setPosition(arrow.getX()-arrow.getX()%32 + 15, arrow.getY()-arrow.getY()%32 + 4);
                        arrow.setRotation(180);
                    }
                    if (wall == 90) {
                        arrow.setPosition(arrow.getX()-arrow.getX()%32 + 48, arrow.getY()-arrow.getY()%32 + 5);
                        arrow.setRotation(0);
                    }
                    break;
                case 180: // West
                    if (arrowStop) break;
                    arrow.translateX(-400f * delta);
                    if (!arrowRicochet) break;
                    count.set(i, count.get(i)+1);
                    if (count.get(i) >= ric) break;
                    if (wall == 90) {
                        arrow.setPosition(arrow.getX()-arrow.getX()%32 - 5, arrow.getY()-arrow.getY()%32 + 16);
                        arrow.setRotation(90);
                    }
                    if (wall== 0) {
                        arrow.setPosition(arrow.getX()-arrow.getX()%32 - 4, arrow.getY()-arrow.getY()%32 - 17);
                        arrow.setRotation(270);
                    }
                    break;
                default: break;
            }
            if (arrowStop) {
                removeArrow(i);
            }
        }
    }

    private boolean shouldRicochet(String pos, int rotation, float w) {
        if (pos.equals("bouncy")) {
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

    // Removes one arrow at a time
    private void removeArrow(int i) {
        timer.set(i, timer.get(i)+Gdx.graphics.getDeltaTime());
        if (timer.get(i) >= 0.9f) {
            arrowArray.removeIndex(i);
            count.removeIndex(i);
            timer.removeIndex(i);
            hasPressed.removeIndex(i);
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
