package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelDraw {

    public static Texture backgroundTexture = new Texture("background.png");
    public static Texture brickWallTexture = new Texture("brickWall.png");
    public static Texture crateTexture = new Texture("blockCrate.png");
    public static Texture portalTexture = new Texture("portal.png");
    public static Texture rButtonTx = new Texture("buttonR.png");
    public static Texture gButtonTx = new Texture("buttonG.png");
    public static Texture bButtonTx = new Texture("buttonB.png");
    public static Texture yButtonTx = new Texture("buttonY.png");
    public static Texture inPortalTx = new Texture("inPortal.png");
    public static Texture bouncy = new Texture("bouncyWall.png");
    public static Texture buttonTx = new Texture("button.png");
    public static Texture pressureButtonTx = new Texture("pressureButton.png");
    public static Texture leverTexture = new Texture("lever.png");
    public static Texture lever2Texture = new Texture("lever2.png");

    public static void drawLevel(SpriteBatch batch, level level){
        for (int j = 0; j < level.level1.length; j++) {
            for (int i = 0; i < level.level1[0].length; i++) {
                String tile = level.level1[j][i].getTileString();
                if (!tile.equals("box") && !tile.equals("portal") && !tile.equals("wall"))
                    batch.draw(backgroundTexture, i * 32, j * 32, 32, 32);
                switch(level.level1[j][i].getTileString()) {
                    case "portal": batch.draw(portalTexture, i*32, j*32, 32, 32); break;
                    case "box": batch.draw(crateTexture, i*32, j*32, 32, 32); break;
                    case "wall": batch.draw(brickWallTexture, i*32, j*32, 32, 32); break;
                    case "button": batch.draw(buttonTx, i*32, j*32, 32, 32); break;
                    case "lever":
                        if (((Lever) level.level1[j][i]).flipStatus())
                            {batch.draw(lever2Texture, i * 32, j * 32, 32, 32);}
                        else batch.draw(leverTexture, i * 32, j * 32, 32, 32);
                        break;
                    case "inportal": batch.draw(inPortalTx, i * 32, j * 32, 32, 32); break;

                    // place below for Sprites

                    case "bouncy":
                    case "colorButton":
                    case "pressureButton":
                        level.level1[j][i].getSprite().setPosition(i * 32, j *32);
                        (level.level1[j][i]).getSprite().draw(batch); break;
                    default: break;
                }
            }
        }
    }
}
