package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelDraw {
    public static Texture backgroundTexture = new Texture("background.png");
    public static Texture crateTexture = new Texture("blockCrate.png");
    public static Texture portalTexture = new Texture("portal.png");
    public static Texture buttonBTexture = new Texture("buttonB.png");
    public static Texture brickWallTexture = new Texture("brickWall.png");
    public static Texture inPortalTx = new Texture("inPortal.png");
    public static Texture bouncy = new Texture("bouncyWall.png");

    public static void drawLevel(SpriteBatch batch, level level){
        for (int i = 0; i < level.level1.length; i++) {
            for (int j = 0; j < level.level1[0].length; j++) {
                switch(level.level1[j][i].getTileString()) {
                    case "portal": batch.draw(portalTexture, i*32, j*32, 32, 32); break;
                    case "box": batch.draw(crateTexture, i*32, j*32, 32, 32); break;
                    case "wall": batch.draw(brickWallTexture, i*32, j*32, 32, 32); break;
                    case "floor": drawF(batch, i, j); break;
                    case "button": drawF(batch, i, j); (((Button) Main.currentLevel.getObject()[i][j])).getSprite().draw(batch); break;
                    case "bouncy": drawF(batch, i, j); (((BouncyWall) Main.currentLevel.getObject()[i][j])).getSprite().draw(batch); break;
                    case "lever": drawF(batch, i, j); batch.draw(Lever.leverTexture, i * 32, j * 32, 32, 32); break;
                    case "inportal": drawF(batch, i, j); batch.draw(inPortalTx, i * 32, j * 32, 32, 32); break;
                    default: break;
                }
            }
        }
    }
    // Added this back because it will be used everytime there's a texture that isn't the full 32 x 32
    public static void drawF(SpriteBatch batch, int i, int j) { batch.draw(backgroundTexture, i * 32, j * 32, 32, 32);}
}
