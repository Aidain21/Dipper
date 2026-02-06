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
    private Texture image;
    Player player;
    public float inputTimer = 0f;
    level currentLevel;
    map levels;


    @Override
    public void create() {
        player = new Player();
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        levels = new map(2,2,10,10);
        level templevel=new level(8,8);
        levels.getMap()[0][1]=templevel;
        currentLevel=levels.getMap()[0][0];
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        input();
        logic();

        batch.begin();
        bow();
        draw();
        batch.end();

    }

    private void input() {

        if (inputTimer <= 0) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                player.gridMove(new Vector2(-1, 0), currentLevel);
                inputTimer = 0.1f;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                player.gridMove(new Vector2(1, 0), currentLevel);
                inputTimer = 0.1f;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                player.gridMove(new Vector2(0, 1), currentLevel);
                inputTimer = 0.1f;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                player.gridMove(new Vector2(0, -1), currentLevel);
                inputTimer = 0.1f;
            }
        }
        else {
            inputTimer -= Gdx.graphics.getDeltaTime();
        }
    }

    private void bow() {
        int arrowX = (int) player.getPos().x, arrowY = (int) player.getPos().y;
        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            batch.draw(image, player.pos.x, player.pos.y);


            System.out.print(arrowX);
        }
    }

    private void logic() {

    }
    private void draw() {
        currentLevel.drawLevel(batch);

        batch.draw(image, 140, 210);
        player.drawPlayer(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
