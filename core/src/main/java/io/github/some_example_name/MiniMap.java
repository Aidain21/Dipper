package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class MiniMap {

    public static void drawMap(SpriteBatch batch, map map, level level){
        level[][] m=map.getMap();
        for (int j = 0; j < m.length; j++) {
            for (int i = 0; i < m[0].length; i++) {
                //shows the current level
                if(m[j][i].equals(level))
                    batch.draw(LevelDraw.playerTx,800+(i*32),650+(j*32),32, 32);
                //shows the rest of the levels
                else
                    batch.draw(LevelDraw.backgroundTexture,800+(i*32),650+(j*32),32, 32);
            }
        }
    }
}
