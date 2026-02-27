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

    public static void drawLevel(SpriteBatch batch, level level){
        for (int i = 0; i < level.level1.length; i++) {
            for (int j = 0; j < level.level1[0].length; j++) {
                String tile = level.level1[j][i].getTileString();
                if (!tile.equals("box") && !tile.equals("portal") && !tile.equals("wall") && !tile.equals("spikes"))
                    batch.draw(backgroundTexture, i * 32, j * 32, 32, 32);
                switch(level.level1[j][i].getTileString()) {
                    case "portal": batch.draw(portalTexture, i*32, j*32, 32, 32); break;
                    case "box": batch.draw(crateTexture, i*32, j*32, 32, 32); break;
                    case "wall": batch.draw(brickWallTexture, i*32, j*32, 32, 32); break;
                    case "button": batch.draw(buttonTx, i*32, j*32, 32, 32); break;
                    case "bouncy": ((BouncyWall) level.level1[j][i]).getSprite().draw(batch); break;
                    case "lever": batch.draw(Lever.leverTexture, i * 32, j * 32, 32, 32); break;
                    case "inportal": batch.draw(inPortalTx, i * 32, j * 32, 32, 32); break;
                    case "colorButton": ((ColorButton) level.level1[j][i]).getSprite().draw(batch); break;
                    case "pressureButton": ((PressureButton) level.level1[j][i]).getSprite().draw(batch); break;
                    case "spikes":break;
                    default: break;
                }
            }
        }
    }
}
