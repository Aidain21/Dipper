package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    Player player;
    public float inputTimer = 0f;
    static level currentLevel;
    static map levels;
    static Bow bow;
    public static TextBox textBox;
    Viewport viewport;
    LevelLogic log;

    @Override
    public void create() {
        player = new Player();
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        levels = new map(2,2,12,12);
        level templevel=new level(8,8,1,1);
        templevel.changeTile(3,2,"portal",1,1);
        templevel.changeTile(5,5,"portal",0,0);
        templevel.changeTile( 4,4,"bouncy", 180f);
        templevel.changeTile(1, 5, "gB", 90f);
        templevel.changeTile(5, 2, "box");
        levels.getMap()[1][0]=templevel;
        currentLevel=levels.getMap()[0][0];
        currentLevel.changeTile(2,4,"portal",0,1);
        currentLevel.changeTile(1,5,"inportal",3,3);
        currentLevel.changeTile(5,5,"box");
        currentLevel.changeTile(7,7,"lever","box");
        currentLevel.changeTile(10,8,"yB", 270f);
        currentLevel.changeTile(1,10,"bouncy", 0f);
        currentLevel.changeTile(9,10,"bouncy", 270f);
        currentLevel.changeTile(1,1,"bouncy", 90f);
        currentLevel.changeTile(9,1,"bouncy", 180f);
        currentLevel.changeTile(5,9,"bouncy", 180f);
        currentLevel.changeTile(5,8,"bouncy", 270f);
        currentLevel.changeTile(6,9,"bouncy", 90f);
        currentLevel.changeTile(6,8,"bouncy", 0f);
        currentLevel.changeTile(6,3,"bouncy", 180f);
        currentLevel.changeTile(10,10,"wall");
        currentLevel.changeTile(10,1,"wall");
        currentLevel.changeTile(9,5,"button");
        currentLevel.changeTile(3,9,"spike", 1);
        currentLevel.changeTile(4, 2, "pressureButton");
        bow = new Bow();
        log = new LevelLogic();
        textBox = new TextBox();
        viewport = new FitViewport(960,720);

        Gdx.graphics.setWindowedMode(960, 720);

    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        input();
        logic();
        draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    private void input() {

        if (!player.isAlive()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) player.playerRestart(currentLevel);
            return;
        }

        // Movement
        if (inputTimer <= 0) {
            // Player Movement Lock
            if (Player.playerLock) {
                player.locked();
                return;
            }

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
            if (Gdx.input.isKeyPressed(Input.Keys.L)) {
                levels.getMap()[1][1].printLevel();
                inputTimer = 0.1f;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                MainMenuUI.CreateMenu();
            }
        } else {
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
        bow.arrowLogic(currentLevel);
        log.logic(currentLevel);
        player.playerLogic();
    }

    private void draw() {
        LevelDraw.drawLevel(batch,currentLevel);

        //the logo
        //batch.draw(image, 140, 210);

        textBox.drawTextBox(batch);
        player.drawPlayer(batch);
        bow.drawArrow(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    public static Vector2Int moveLevel(int x, int y){
        currentLevel=levels.getMap()[y][x];
        return new Vector2Int(currentLevel.getSpawnRow(), currentLevel.getSpawnCol());
    }

}

