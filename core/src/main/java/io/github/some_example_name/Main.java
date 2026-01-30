package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image, character;

    public int posX = 0, posY = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        character = new Texture("char.png");
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 140, 210);
        batch.draw(character, posX*32,posY*32);
        batch.end();
        input();
        logic();
        draw();
    }

    private void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            posX -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            posX += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            posY += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            posY -= 1;
        }
    }

    private void logic() {

    }

    private void draw() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
