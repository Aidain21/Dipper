package io.github.some_example_name;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Arrays;

public class MiniMap {

    public static void drawMap(SpriteBatch batch, map map, level level){
        level[][] m=map.getMap();
        level temp = new level(0,0);
        for (int j = 0; j < m.length; j++) {
            for (int i = 0; i < m[0].length; i++) {
                //shows the current level

                // Draws background or void if there is no level
                if(Arrays.deepEquals(m[j][i].getLevel(), temp.getLevel()))
                    batch.draw(LevelDraw.voidTx,800+(i*32),650+(j*32),32, 32);
                else batch.draw(LevelDraw.backgroundTexture,800+(i*32),650+(j*32),32, 32);

                if(m[j][i].equals(level))
                    batch.draw(LevelDraw.playerTx,800+(i*32),650+(j*32),32, 32);
            }
        }
    }
}
