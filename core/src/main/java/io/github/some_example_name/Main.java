package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private TextButton button;
    private Stage stage;
    private Table table;
    public float testy = 0f, testy2 = 0f;

    @Override
    public void create() {
        Skin skin = new Skin(Gdx.files.internal(""));
        stage = new Stage();
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        button = new TextButton("Button", skin);
        table = new Table();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.addActor(button);

    }

    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
    button.addListener(new ChangeListener() {
        public void changed (ChangeEvent event, Actor actor) {
            System.out.println("Changed!");
        }
    });

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

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
