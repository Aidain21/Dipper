package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static io.github.some_example_name.LevelDraw.backgroundTexture;
import static io.github.some_example_name.LevelDraw.portalTexture;

public class MiniMap {

    public static void drawMap(SpriteBatch batch, map map, level level){
        level[][] m=map.getMap();
        for (int j = 0; j < m.length; j++) {
            for (int i = 0; i < m[0].length; i++) {
                if(m[j][i].equals(level))
                    batch.draw(portalTexture,800+(i*32),650+(j*32),32, 32);
                else
                    batch.draw(backgroundTexture,800+(i*32),650+(j*32),32, 32);
                /*TileFills tile = map.level1[j][i];
                if (tile.drawBackground()) batch.draw(backgroundTexture, i * 32, j * 32, 32, 32);
                if (tile.getType().equals("texture")) batch.draw(tile.getTexture(), i * 32, j * 32, 32, 32);
                else if (tile.getType().equals("sprite")) tile.getSprite().draw(batch);
                 */

            }
        }
    }
}
