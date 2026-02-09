package io.github.some_example_name;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class TestPuzzle extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private TextButton button;
    private Stage stage;
    private Table table;
    private boolean order;
    private int buttonCounter;
    public float testy = 0f, testy2 = 0f;
    private int[] orderButtons;

    @Override
    public void create() {
        orderButtons = new int[5];
        buttonCounter = 1;
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        TextButton button1 = new TextButton("Button", skin);
        TextButton button2 = new TextButton("Button", skin);
        TextButton button3 = new TextButton("Button", skin);
        TextButton button4 = new TextButton("Button", skin);

        button1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Button 1 Pressed!");
                if (orderButtons[buttonCounter - 1] < 1) {
                    orderButtons[buttonCounter] = 1;
                    buttonCounter++;
                } else
                    resetOrder();
                checkWin();
            }
        });

        button2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Button 2 Pressed!");
                if (orderButtons[buttonCounter - 1] < 3) {
                    orderButtons[buttonCounter] = 3;
                    buttonCounter++;
                } else
                    resetOrder();
                checkWin();
            }
        });
        button3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Button 3 Pressed!");
                if (orderButtons[buttonCounter - 1] < 2) {
                    orderButtons[buttonCounter] = 2;
                    buttonCounter++;
                } else
                    resetOrder();
                checkWin();
            }
        });

        button4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Button 4 Pressed!");
                if (orderButtons[buttonCounter - 1] < 4) {
                    orderButtons[buttonCounter] = 4;
                    buttonCounter++;
                } else
                    resetOrder();
                checkWin();
            }
        });

        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.add(button1).center().width(200).height(100);
        table.add(button2).top().width(200).height(100);
        table.add(button3).left().width(200).height(100);
        table.add(button4).right().width(200).height(100);

    }

    public void resetOrder() {
        for (int i = 0; i < 5; i++) {
            orderButtons[i] = 0;
        }
        System.out.println("Resetting values");
        buttonCounter = 1;
        order = false;
    }

    public void checkWin() {
        if (buttonCounter == 5) {
            for (int i = 0; i < orderButtons.length - 1; i++) {
                if (orderButtons[i] < orderButtons[i + 1])
                    order = true;
            }
        }
        if (order) {
            System.out.println("DING DING DING");
            resetOrder();
        }
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
