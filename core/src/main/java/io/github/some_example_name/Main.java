package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;
import java.util.Objects;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    static Player player;
    public float inputTimer = 0f;
    static level currentLevel;
    public static map levels;
    static Bow bow;
    public static boolean fullMap = false;
    public static TextBox textBox;
    Viewport viewport;
    LevelLogic log;
    private StartMenu startMenu;
    private Skin startSkin;
    private Skin editorSkin;
    private PauseMenuUI pauseMenu;
    private Skin resumeButtonSkin;
    private Skin resetButtonSkin;
    private Skin restartButtonSkin;
    public static boolean gameStarted = false;
    public static Main instance;

    @Override
    public void create() {
        instance = this;
        batch = new SpriteBatch();
        viewport = new FitViewport(1600, 1040);
        Gdx.graphics.setWindowedMode(1600, 1040);

        startSkin = new Skin(Gdx.files.internal("startButton.json"));
        editorSkin = new Skin(Gdx.files.internal("editorButton.json"));
        startMenu = new StartMenu(startSkin, editorSkin);
        Gdx.input.setInputProcessor(startMenu.getStage());
    }

    public void onStartGame() {
        gameStarted = true;
        Tile.startTile();
        textBox = new TextBox();
        image = new Texture("libgdx.png");

        resumeButtonSkin = new Skin(Gdx.files.internal("ResumeButton.json"));
        resetButtonSkin  = new Skin(Gdx.files.internal("ResetButton.json"));
        restartButtonSkin = new Skin(Gdx.files.internal("RestartButton.json"));
        pauseMenu = new PauseMenuUI(resumeButtonSkin, resetButtonSkin, restartButtonSkin);
        pauseMenu.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        resetGame();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void render() {


        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        if (!gameStarted) {
            startMenu.getStage().act(Gdx.graphics.getDeltaTime());
            startMenu.getStage().draw();
            return; // skip all game logic until start is pressed
        }

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        //Button for pause menu
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (pauseMenu.isVisible()) {
                pauseMenu.hide();
                Gdx.input.setInputProcessor(null);
            }
            else {
                pauseMenu.show();
                Gdx.input.setInputProcessor(pauseMenu.getStage());
            }
        }

        if (!pauseMenu.isVisible() && !Drawing.drawing) { //If the pause menu is open don't update logic or take input
            input();
            logic();
        }

        //Restarts game if button is pressed
        if (pauseMenu.getRestartStatus()) {
            pauseMenu.setRestartStatus(false);
            resetGame();
        }
        //restarts current level if button is pressed
        if (pauseMenu.getRestartRoomStatus()) {
            pauseMenu.setRestartRoomStatus(false);
            float hp = player.getHealth();
            player.playerRestart(currentLevel);
            player.setHealth(hp);
            player.dealDamage(0);

            if (!Objects.equals(currentLevel.filename, "")) {
                currentLevel = LevelTemplates.loadJson(currentLevel.filename);
                LevelTemplates.createObjects(currentLevel);
            }

        }

        batch.begin();
        if (Drawing.drawing) {
            artDraw();
            artInput();
            artLogic();
        }
        else {
            draw();
        }

        batch.end();

        if (pauseMenu.isVisible()) {
            pauseMenu.getStage().act(Gdx.graphics.getDeltaTime());
            pauseMenu.getStage().draw();
        }
    }

    private void resetGame() {//this handles level and player declaration
        player = new Player();
        levels = new map(8, 8, 12, 12);
        currentLevel = levels.getMap()[0][0];

        bow = new Bow();
        log = new LevelLogic();
        //TextBox.clearText();
        inputTimer = 0f;

        Gdx.input.setInputProcessor(null);
        Gdx.graphics.setWindowedMode(1600, 1040);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        if (pauseMenu != null) {
            pauseMenu.resize(width, height);
        }
    }

    private void input() {
        if (!player.isAlive()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
                player.playerRestart(currentLevel);
                resetGame();
            }
            return;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)&& !Player.playerSliding && !Player.playerFalling) {
            if (!fullMap) {
                fullMap = true;
                Player.playerLock = true;
                Player.inMap =true;
            } else {
                fullMap = false;
                Player.playerLock = false;
                Player.inMap =false;
            }
        }

        if (Player.playerLock) {
            player.locked(currentLevel);
            return;
        }

        // Movement
        if (inputTimer <= 0) {
            // Player Movement Lock
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)) {
            Drawing.start(true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)) {
            Drawing.start(false);
        }


    }

    private void logic() {
        bow.arrowLogic(currentLevel);
        log.logic(currentLevel);
        player.playerLogic();
    }

    private void draw() {
        if(!fullMap) {
            LevelDraw.drawLevel(batch, currentLevel);
            MiniMap.drawMap(batch, levels, currentLevel, false);

            //the logo
            //batch.draw(image, 140, 210);

            textBox.drawTextBox(batch);
            player.drawPlayer(batch);
            bow.drawArrow(batch);
        }
        else
            MiniMap.drawMap(batch, levels, currentLevel, true);
    }


    public void artInput() {
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();

        if (!Drawing.placingPortal && !Drawing.placingOutPortal && !fullMap && !Drawing.placingButton) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)) {
                Drawing.end("SaveAs");
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
                Drawing.end("Save");
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.MINUS)) {
                Drawing.end("NoSave");
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                Drawing.changeDrawTile(1);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                Drawing.changeDrawTile(-1);
            }

            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) &&
                !Objects.equals(Drawing.curTile.getTileString(), "inportal") &&
                !Objects.equals(Drawing.curTile.getTileString(), "portal") &&
                !Objects.equals(Drawing.curTile.getTileString(), "button")) {
                Drawing.drawTile(mouseX,mouseY, false);
            }

            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                (Objects.equals(Drawing.curTile.getTileString(), "inportal") ||
                Objects.equals(Drawing.curTile.getTileString(), "portal") ||
                Objects.equals(Drawing.curTile.getTileString(), "button"))) {
                Drawing.drawTile(mouseX,mouseY, false);
            }

            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                Drawing.drawTile(mouseX,mouseY,true);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                Drawing.rotateTile(mouseX,mouseY);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
                Drawing.getTileData(mouseX,mouseY);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
                Drawing.workingLevel.icon = Drawing.curTile;
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
                fullMap = !fullMap;
            }
        }
        else if (Drawing.placingPortal && !Drawing.placingOutPortal && !fullMap){
            TextBox.updateTextBox("Placing portal end",4);
            TextBox.updateTextBox("Two Way: " + Drawing.twoWay,5);

            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                Drawing.endPlacePortal(mouseX,mouseY);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                Drawing.twoWay = !Drawing.twoWay;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                Drawing.twoWay = !Drawing.twoWay;
            }
        }
        else if (Drawing.placingButton) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) Drawing.endButton(mouseX, mouseY);
        }
        else if (!Drawing.placingPortal && Drawing.placingOutPortal && fullMap) {
            TextBox.updateTextBox("Placing level portal",4);

            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                Drawing.endPlaceOutPortal(mouseX,mouseY);
            }
        }
        else if (!Drawing.placingPortal && !Drawing.placingOutPortal) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
                fullMap = !fullMap;
            }
        }
    }

    public void artLogic() {

    }

    public void artDraw() {
        if (!fullMap) {
            LevelDraw.drawLevel(batch,Drawing.workingLevel);
            if (Drawing.curTile.getType().equals("texture")) batch.draw(Drawing.curTile.getTexture(),
                Gdx.graphics.getWidth() -32, Gdx.graphics.getHeight() -32,32, 32);
            else if (Drawing.curTile.getType().equals("sprite")) {
                Drawing.curTile.getSprite().setPosition(Gdx.graphics.getWidth() -32, Gdx.graphics.getHeight() -32);
                Drawing.curTile.getSprite().draw(batch);
            }
        }
        else{
            MiniMap.drawMap(batch, levels, currentLevel, true);
        }
        textBox.drawTextBox(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        pauseMenu.dispose();
        startMenu.dispose();
    }

    public static Vector2Int moveLevel(int x, int y){

        currentLevel=levels.getMap()[y][x];
        /*
        if (!Objects.equals(currentLevel.filename, "")) {
            currentLevel = LevelTemplates.loadJson(currentLevel.filename);
            LevelTemplates.createObjects(currentLevel);
        }

         */
        TextBox.updateTextBox(currentLevel.name, 6);
        return new Vector2Int(currentLevel.getSpawnRow(), currentLevel.getSpawnCol());
    }

}

