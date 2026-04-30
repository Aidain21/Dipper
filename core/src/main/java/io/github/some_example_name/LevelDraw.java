package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
    public static Texture magicTx = new Texture("magic.png");
    public static Texture lever2Tx = new Texture("lever2.png");
    public static Texture spikesTx = new Texture("spikes.png");
    public static Texture voidTx = new Texture("void.png");
    public static Texture iceTx = new Texture("iceTile.png");
    public static Texture playerTx = new Texture("char.png");
    public static Texture fallBoxTx = new Texture("fallBox.png");
    public static Texture characterTx = new Texture("character.png");
    public static Texture redGateTx = new Texture("rGate.png");
    public static Texture greenGateTx = new Texture("gGate.png");
    public static Texture blueGateTx = new Texture("bGate.png");
    public static Texture yellowGateTx = new Texture("yGate.png");
    public static Texture openGateTx = new Texture("gateOpen.png");
    public static Texture dipperBossTx = new Texture("dipperpng2.png");
    public static Texture heartTx = new Texture("heartTx.png");
    public static Texture healthTx = new Texture("char.png");//"health.png");
    public static TextureRegion characterFront = new TextureRegion(characterTx, 64, 0, 32, 44);
    public static TextureRegion characterBack = new TextureRegion(characterTx, 96, 0, 32, 44);
    public static TextureRegion characterLeft = new TextureRegion(characterTx, 0, 0, 32, 44);
    public static TextureRegion characterRight = new TextureRegion(characterTx, 32, 0, 32, 44);

    public static void drawLevel(SpriteBatch batch, level level){
        for (int j = 0; j < level.level1.length; j++) {
            for (int i = 0; i < level.level1[0].length; i++) {
                TileFills tile = level.level1[j][i];
                if (tile.drawBackground()) batch.draw(backgroundTexture, i * 32, j * 32, 32, 32);
                if (tile.getType().equals("texture")) batch.draw(tile.getTexture(), i * 32, j * 32, 32, 32);
                else if (tile.getType().equals("sprite")) {
                    tile.getSprite().setPosition(i*32, j*32);
                    tile.getSprite().draw(batch);
                }
            }
        }
    }
}
