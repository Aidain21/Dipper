package io.github.some_example_name;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Arrays;

public class MiniMap {

    public static void drawMap(SpriteBatch batch, map map, level level, boolean full){
        level[][] m=map.getMap();
        level temp = new level(0,0);
        int xStart=800;
        int yStart=650;
        int size=32;
        if(full){
            xStart=0;
            yStart=0;
            size=64;
        }
        for (int j = 0; j < m.length; j++) {
            for (int i = 0; i < m[0].length; i++) {
                //shows the current level

                // Draws background or void if there is no level
                if(Arrays.deepEquals(m[j][i].getLevel(), temp.getLevel()))
                    batch.draw(LevelDraw.voidTx,xStart+(i*size),yStart+(j*size),size, size);
                else batch.draw(LevelDraw.backgroundTexture,xStart+(i*size),yStart+(j*size),size, size);

                if(m[j][i].equals(level))
                    batch.draw(LevelDraw.playerTx,xStart+(i*size),yStart+(j*size),size, size);
            }
        }
    }
}
