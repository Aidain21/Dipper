package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    char[][] levelBase;
    //level[][] levels = new level[10][10];
    int currentRow;
    int currentCol;
    level currentLevel;
    map levels;
    //level level1;
    Texture bucketTexture;
    Texture dropTexture;
    SpriteBatch spriteBatch;
    FitViewport viewport;

    public float testy = 0f, testy2 = 0f;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        bucketTexture = new Texture("bucket.png");
        dropTexture = new Texture("drop.png");
        //level1= new level(10,10);
        //levels[0][0]=level1;
        currentRow=0;
        currentCol=0;
        levels = new map(2,2);
        level templevel=new level(8,8);
        levels.getMap()[0][1]=templevel;
        //currentLevel=levels.getMap()[0][0];
        //levelBase=currentLevel.getLevel();
        /*currentLevel.changeTile(3,2,'l');
        currentLevel.changeTile(0,1,'l');
        currentLevel.changeCol(5,'q');
        currentLevel.changeRow(1,'a');
         */
        //char[][] level1 = new char[5][5];
        /*for (int i = 0; i < 5; i++) {
            level1[0][i] = 'w';
            level1[4][i] = 'w';
            level1[i][0] = 'w';
            level1[i][4] = 'w';
        }
        for (int j = 1; j < 4; j++) {
            for (int b = 1; b < 4; b++) {
                level1[j][b] = 'f';
            }
        }
         */
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 140 + testy, 210 + testy2);

        input();
        logic();
        draw();
        batch.end();
    }

    private void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            testy -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            testy += 1;
            changeLevel('r');
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            testy2 += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            testy2 -= 1;
        }
    }

    private void logic() {

    }

    //add error checking for map edges
    private void changeLevel(char direction){
        if(direction=='u')
            currentRow++;
        if (direction=='d')
            currentRow--;
        if(direction=='r')
            currentCol++;
        if(direction=='l')
            currentCol--;
    }

    private void draw() {
        currentLevel=levels.getMap()[currentRow][currentCol];
        levelBase=currentLevel.getLevel();
        for (int i = 0; i < currentLevel.getRowCount(); i++) {
            for (int j = 0; j < currentLevel.getColCount(); j++) {
                if (levelBase[i][j] == 'w') {
                    batch.draw(bucketTexture, i * 20, j * 20, 20, 20);
                }
                if (levelBase[i][j] == 'f') {
                    batch.draw(dropTexture, i * 20, j * 20, 20, 20);
                }
                //System.out.print("");
            }
            //count++;
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
