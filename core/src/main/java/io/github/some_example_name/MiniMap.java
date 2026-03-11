package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;

import static io.github.some_example_name.LevelDraw.*;

public class MiniMap {

    public static void drawMap(SpriteBatch batch, map map, level level){
        level[][] m=map.getMap();
        level temp = new level(0,0);
        for (int j = 0; j < m.length; j++) {
            for (int i = 0; i < m[0].length; i++) {
                //shows the current level
                if(m[j][i].equals(level))
                    batch.draw(portalTexture,800+(i*32),650+(j*32),32, 32);
                //empty levels
                else if(Arrays.deepEquals(m[j][i].getLevel(), temp.getLevel()))
                    batch.draw(voidTx,800+(i*32),650+(j*32),32, 32);
                //shows the rest of the levels
                else
                    batch.draw(backgroundTexture,800+(i*32),650+(j*32),32, 32);
            }
        }
    }
}
