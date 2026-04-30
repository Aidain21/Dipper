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

    //this is the amount of levels of each difficulty on a run
    public static final int LEVELCAP = 3;

    private SpriteBatch batch;
    static Player player;
    public float inputTimer = 0f;
    static level currentLevel;
    public static map levels;
    public static int levelNum = -1;
    static Bow bow;
    static DipperBoss dip;
    public static boolean fullMap = false;
    public static boolean testing = false;
    public static TextBox textBox;
    Viewport viewport;
    LevelLogic log;
    private StartMenu startMenu;
    private Skin startSkin;
    private Skin editorSkin;
    private PauseMenuUI pauseMenu;
    private EditorMenu editorMenu;
    private DeathScreen deathScreen;
    private Skin resumeButtonSkin;
    private Skin resetButtonSkin;
    private Skin restartButtonSkin;
    private Skin exitSkin;
    public static boolean gameStarted = false;
    public static Main instance;
    private Skin saveSkin;
    private Skin saveAsSkin;
    private Skin exitWithoutSavingSkin;

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
        //deletes skins for when the game gets reset
        if (resumeButtonSkin != null) resumeButtonSkin.dispose();
        if (restartButtonSkin != null) restartButtonSkin.dispose();
        if (resetButtonSkin != null) resetButtonSkin.dispose();
        if (exitSkin != null) exitSkin.dispose();
        if (saveSkin != null) saveSkin.dispose();
        if (saveAsSkin != null) saveAsSkin.dispose();
        if (exitWithoutSavingSkin != null) exitWithoutSavingSkin.dispose();


        gameStarted = true;
        Tile.startTile();
        textBox = new TextBox();
        dip = new DipperBoss();



        //buttons for pause menu
        resumeButtonSkin = new Skin(Gdx.files.internal("ResumeButton.json"));
        resetButtonSkin  = new Skin(Gdx.files.internal("ResetButton.json"));
        restartButtonSkin = new Skin(Gdx.files.internal("RestartButton.json"));
        exitSkin = new Skin(Gdx.files.internal("exitButton.json"));

        //buttons for level editor pause menu
        saveSkin = new Skin(Gdx.files.internal("save.json"));
        saveAsSkin = new Skin(Gdx.files.internal("saveAs.json"));
        exitWithoutSavingSkin = new Skin(Gdx.files.internal("exitEditor.json"));

        //Creates all the UIs
        deathScreen = new DeathScreen(restartButtonSkin); //uses same restart as pause
        pauseMenu = new PauseMenuUI(resumeButtonSkin, resetButtonSkin, restartButtonSkin, exitSkin);
        editorMenu = new EditorMenu(saveSkin, saveAsSkin, exitWithoutSavingSkin);
        Gdx.graphics.setWindowedMode(1600, 1040);

        startMenu.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        pauseMenu.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        editorMenu.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        deathScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //moved level,player,bow,box, etc. to resetGame method to be able to get the restart game to work
        resetGame();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        //System.out.println(Gdx.graphics.getFramesPerSecond());

        if (!gameStarted) {
            startMenu.getStage().act(Gdx.graphics.getDeltaTime());
            startMenu.getStage().draw();
            Gdx.input.setInputProcessor(startMenu.getStage());
            return; // skips all game logic until start is pressed
        }

        if (player == null) return;

        if (gameStarted && pauseMenu.getQuitStatus()) {
            pauseMenu.setQuitStatus(false);
            pauseMenu.hide();
            gameStarted = false;
            Gdx.input.setInputProcessor(startMenu.getStage());
            return;
        }

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        if (gameStarted) {
            if (!player.isAlive() && !deathScreen.isVisible()) {
                deathScreen.show();
                pauseMenu.hide(); // ensure pause menu doesn't overlap
                Gdx.input.setInputProcessor(deathScreen.getStage());
            }
        }

        //Button for pause menus
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && !deathScreen.isVisible()) {
            if (testing) {
                Drawing.start(true);
            }
            else {
                if (editorMenu.isVisible()) {
                    editorMenu.hide();
                    Gdx.input.setInputProcessor(null);
                }
                else if (Drawing.drawing) {
                    editorMenu.show();
                    Gdx.input.setInputProcessor(editorMenu.getStage());
                }
                else if (pauseMenu.isVisible()) {
                    pauseMenu.hide();
                    Gdx.input.setInputProcessor(null);
                }
                else if (!editorMenu.isVisible()) {
                    pauseMenu.show();
                    Gdx.input.setInputProcessor(pauseMenu.getStage());
                }
            }


        }

        //Only updates logic if no menus are open
        //Find a way to generalize if a menu is open
        if (!pauseMenu.isVisible() && !Drawing.drawing && !deathScreen.isVisible()) {
            input();
            logic();
        }

        //Restarts game if button is pressed
        if (pauseMenu.getRestartStatus()) {
            pauseMenu.setRestartStatus(false);
            pauseMenu.hide();
            resetGame();
            Gdx.input.setInputProcessor(null);
        }

        //Restarts game from death screen
        if (deathScreen.getRestartStatus()) {
            deathScreen.setRestartStatus(false);
            deathScreen.hide();
            resetGame();
            Gdx.input.setInputProcessor(null);
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
        //renders logic for editor
        if (Drawing.drawing) {
            artDraw();
            artInput();
            artLogic();
        }
        else {
            draw();
        }

        batch.end();
        //displays pause menu
        if (pauseMenu.isVisible()) {
            pauseMenu.getStage().act(Gdx.graphics.getDeltaTime());
            pauseMenu.getStage().draw();
        }

        //displays editor menu
        if (editorMenu.isVisible()) {
            editorMenu.getStage().act(Gdx.graphics.getDeltaTime());
            editorMenu.getStage().draw();
        }

        //Displays death screen
        if (deathScreen.isVisible()) {
            deathScreen.getStage().act(Gdx.graphics.getDeltaTime());
            deathScreen.getStage().draw();
        }
    }

    private void resetGame() {//this handles level and player declaration

        if (testing) {
            levels = new map(1, 1, 1, 1);
        }
        else {
            levels = new map(8, 8, 12, 12);
        }

        dip = new DipperBoss();
        currentLevel = levels.getMap()[0][0];
        player = new Player(currentLevel.spawnRow, currentLevel.spawnCol);

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
        if (startMenu != null) {
            startMenu.resize(width, height);
        }
        if (editorMenu != null) {
            editorMenu.resize(width, height);
        }
        if (deathScreen != null) {
            deathScreen.resize(width, height);
        }
    }

    private void input() {
        /*if (!player.isAlive()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
                player.playerRestart(currentLevel);
                resetGame();
            }
            return;
        }
        */

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
            else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                player.gridMove(new Vector2(1, 0), currentLevel);
                inputTimer = 0.1f;
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                player.gridMove(new Vector2(0, 1), currentLevel);
                inputTimer = 0.1f;
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
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
    }

    private void logic() {
        bow.arrowLogic(currentLevel);
        log.logic(currentLevel);
        player.playerLogic();
        if (currentLevel == LevelTemplates.finalBoss) {
            DipperBoss.moveLogic();
            DipperBoss.cooldown += Gdx.graphics.getDeltaTime();
            if (DipperBoss.cooldown >= DipperBoss.cooldownTime) {
                DipperBoss.createMagic();
                DipperBoss.cooldown = 0f;
            }
            DipperBoss.shootMagic();
        }
    }

    private void draw() {
        if(!fullMap) {
            LevelDraw.drawLevel(batch, currentLevel);
            //if(currentLevel != LevelTemplates.finalBoss)
            //    MiniMap.drawMap(batch, levels, currentLevel, false);

            textBox.drawTextBox(batch);
            player.drawPlayer(batch);
            bow.drawArrow(batch);
        }
        else
            MiniMap.drawMap(batch, levels, currentLevel, true);
        if (currentLevel == LevelTemplates.finalBoss) {
            DipperBoss.drawDipper(batch);
            DipperBoss.drawMagic(batch);
        }
    }


    public void artInput() {
        if (editorMenu.isVisible()) {
            return;
        }

        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();

        if (!Drawing.placingPortal && !Drawing.placingButton && !Drawing.placingPressure) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                Drawing.changeDrawTile(1);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                Drawing.changeDrawTile(-1);
            }

            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) &&
                !Objects.equals(Drawing.curTile.getTileString(), "inportal") &&
                !Objects.equals(Drawing.curTile.getTileString(), "button") &&
                !Objects.equals(Drawing.curTile.getTileString(), "pressureButton")) {
                Drawing.drawTile(mouseX,mouseY, false);
            }

            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                (Objects.equals(Drawing.curTile.getTileString(), "inportal") ||
                Objects.equals(Drawing.curTile.getTileString(), "button") ||
                Objects.equals(Drawing.curTile.getTileString(), "pressureButton"))) {
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

            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                Drawing.pickTile(mouseX,mouseY);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
                Drawing.setSpawnPoint(mouseX, mouseY);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
                testing = true;
                Drawing.end("Save");
                resetGame();
            }


            if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
                fullMap = !fullMap;
            }
        }
        else if (Drawing.placingPortal){


            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                Drawing.endPlacePortal(mouseX,mouseY);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                TextBox.updateTextBox("Two Way: " + Drawing.twoWay,5);

                Drawing.twoWay = !Drawing.twoWay;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                TextBox.updateTextBox("Two Way: " + Drawing.twoWay,5);

                Drawing.twoWay = !Drawing.twoWay;
            }
        }
        else if (Drawing.placingButton) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) Drawing.endButton(mouseX, mouseY);
        }
        else if (Drawing.placingPressure) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                Drawing.endPressure();
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                Drawing.addWalls(mouseX, mouseY);
                TextBox.updateTextBox("Tiles Selected: " +
                    Drawing.wallsSelected(Drawing.wallX, Drawing.wallY), 5);
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
        if (pauseMenu != null) pauseMenu.dispose();
        if (editorMenu != null) editorMenu.dispose();
        if (deathScreen != null) deathScreen.dispose();
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
