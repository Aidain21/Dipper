package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Vector2;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    Player player;
    public float inputTimer = 0f;
    static level currentLevel;
    static map levels;
    static int currentRow;
    static int currentCol;
    Bow bow;

    @Override
    public void create() {
        player = new Player();
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        levels = new map(2,2,10,10);
        level templevel=new level(8,8);
        levels.getMap()[0][1]=templevel;
        currentLevel=levels.getMap()[0][0];
        currentLevel.changeTile(2,4,'l');
        bow = new Bow();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        input();
        logic();
        draw();
        batch.end();
    }

    private void input() {
        // Movement
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
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                player.playerInteract(currentLevel);
                inputTimer = 0.1f;
            }
        }
        else {
            inputTimer -= Gdx.graphics.getDeltaTime();
        }

        // Arrows
        bow.cooldown += Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            bow.bowInput(player.pos.x, player.pos.y, 'n');
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            bow.bowInput(player.pos.x, player.pos.y, 'e');
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            bow.bowInput(player.pos.x, player.pos.y, 's');
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            bow.bowInput(player.pos.x, player.pos.y, 'w');
        }


    }

    private void logic() {
        bow.arrowLogic();
    }
    private void draw() {
        currentLevel.drawLevel(batch);

        //the logo
        //batch.draw(image, 140, 210);


        player.drawPlayer(batch);
        bow.drawArrow(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    public static void moveLevel(char rowCol, char upDown){
        if(rowCol=='r') {
            if (upDown == '+')
                currentRow ++;
            else
                currentRow --;
        }
        else{
            if(upDown=='+')
                currentCol++;
            else
                currentCol--;

            }
        currentLevel=levels.getMap()[currentRow][currentCol];
        }
    }

