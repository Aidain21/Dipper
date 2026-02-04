package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    Player player;
    public float inputTimer = 0f;
    private SpriteBatch bucket;
    private Texture bucketT;


    @Override
    public void create() {
        player = new Player();
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        bucket = new SpriteBatch();
        bucketT = new Texture("bucket.png");

    }

    @Override
    public void render() {

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 140, 210);


        batch.draw(player.sprite, player.pos.x*32,player.pos.y*32);

        batch.end();
        bow();
        input();
        logic();
        draw();
    }

    private void input() {

        if (inputTimer <= 0) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                player.gridMove(new Vector2(-1, 0));
                inputTimer = 0.1f;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                player.gridMove(new Vector2(1, 0));
                inputTimer = 0.1f;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                player.gridMove(new Vector2(0, 1));
                inputTimer = 0.1f;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                player.gridMove(new Vector2(0, -1));
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
            bucket.begin();
            bucket.draw(bucketT, player.pos.x*1, player.pos.y*1);

            bucket.end();

            System.out.print(arrowX);
            //input();
            //logic();
            //draw();
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
