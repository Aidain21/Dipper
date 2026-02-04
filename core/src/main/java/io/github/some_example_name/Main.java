package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    Player player;
    public float inputTimer = 0f;
    char[][] levelBase;
    int currentRow;
    int currentCol;
    level currentLevel;
    map levels;
    Texture bucketTexture;
    Texture dropTexture;
    SpriteBatch spriteBatch;
    FitViewport viewport;


    @Override
    public void create() {
        player = new Player();
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        bucketTexture = new Texture("bucket.png");
        dropTexture = new Texture("drop.png");
        currentRow=0;
        currentCol=0;
        levels = new map(2,2);
        level templevel=new level(8,8);
        levels.getMap()[0][1]=templevel;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        input();
        logic();
        batch.begin();
        draw();
        batch.end();

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

        batch.draw(image, 140, 210);
        player.drawPlayer(batch);




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
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
