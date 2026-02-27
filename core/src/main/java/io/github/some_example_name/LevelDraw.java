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
    public static Texture buttonPressTx = new Texture("buttonPressTx.png");
    public static Texture pressureButtonTx = new Texture("pressureButton.png");
    public static Texture leverTx = new Texture("lever.png");
    public static Texture lever2Tx = new Texture("lever2.png");
    public static Texture spikesTx = new Texture("spikes.png");

    public static void drawLevel(SpriteBatch batch, level level){
        for (int j = 0; j < level.level1.length; j++) {
            for (int i = 0; i < level.level1[0].length; i++) {
                String tile = level.level1[j][i].getTileString();
                if (!tile.equals("box") && !tile.equals("portal") && !tile.equals("wall"))
                    batch.draw(backgroundTexture, i * 32, j * 32, 32, 32);

                switch (level.level1[j][i].getType()) {
                    case "texture": batch.draw((level.level1[j][i]).getTexture(), i * 32, j * 32, 32, 32); break;
                    case "sprite": (level.level1[j][i]).getSprite().draw(batch); break;
                    default: break;
                }
            }
        }
    }
}
