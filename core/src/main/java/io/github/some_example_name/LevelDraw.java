package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelDraw {

    public static Texture backgroundTexture = new Texture("background.png");
    public static Texture crateTexture = new Texture("blockCrate.png");
    public static Texture portalTexture = new Texture("portal.png");

    public static void drawLevel(SpriteBatch batch, level level){
        for (int j = 0; j < level.level1.length; j++) {
            for (int i = 0; i < level.level1[0].length; i++) {
                switch(level.level1[j][i].getTileString()) {
                    case "portal": batch.draw(portalTexture, i*32, j*32, 32, 32); break;
                    case "box": batch.draw(crateTexture, i*32, j*32, 32, 32); break;
                    case "wall": batch.draw(Walls.brickWallTexture, i*32, j*32, 32, 32); break;
                    case "floor": batch.draw(backgroundTexture, i * 32, j * 32, 32, 32); break;
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                        batch.draw(backgroundTexture, i * 32, j * 32, 32, 32);
                        Walls.bouncy.setRotation(Walls.getWallRotation(level.level1[j][i].getTileString()));
                        Walls.bouncy.setPosition(i * 32, j * 32);
                        Walls.bouncy.draw(batch);
                        break;
                    default: break;
                }

            }
        }
    }
}
