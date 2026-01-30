package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class level extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    char[][] level1=new char[5][5];
    Texture bucketTexture;
    Texture dropTexture;
    SpriteBatch spriteBatch;
    FitViewport viewport;
    boolean run = true;
    int count = 0;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        bucketTexture = new Texture("bucket.png");
        dropTexture = new Texture("drop.png");
        //char[][] level1 = new char[5][5];
        for (int i = 0; i < 5; i++) {
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
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.begin();
        //batch.draw(image, 140, 210);
        draw();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    private void draw() {

        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        //spriteBatch.begin();
        //while (run) {
        //if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        //ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        //ScreenUtils.clear(Color.BLACK);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (level1[i][j] == 'w') {
                    batch.draw(bucketTexture, i * 20, j * 20, 20, 20);
                }
                if (level1[i][j] == 'f') {
                    batch.draw(dropTexture, i * 20, j * 20, 20, 20);
                }
                //System.out.print("");
            }
            //count++;
        }
        // if (count > 5)
        //   run = false;
        //   }
    }
    //batch.end();
    //}
}
