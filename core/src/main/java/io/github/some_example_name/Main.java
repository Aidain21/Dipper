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
    level level1;
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
        level1= new level(10,10);
        levelBase=level1.getLevel();
        level1.changeTile(3,2,'l');
        level1.changeTile(0,1,'l');
        level1.changeCol(5,'q');
        level1.changeRow(1,'a');
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

    private void draw() {
        for (int i = 0; i < level1.getRowCount(); i++) {
            for (int j = 0; j < level1.getColCount(); j++) {
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
