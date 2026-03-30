//Restart room still needs to be implemented.
//Bug where if you restart game while falling it crashes upon respawn.
package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PauseMenuUI {
    private Stage stage;
    private Skin buttonSkin;
    private Boolean visible;
    private Boolean restartStatus;

    public PauseMenuUI(Skin skin) {
        this.buttonSkin = skin;
        this.stage = new Stage(new ScreenViewport());
        this.visible = false;
        this.restartStatus = false;

        TextButton resume = new TextButton("Resume", buttonSkin);
        TextButton restartRoom = new TextButton("Restart Room", buttonSkin);
        TextButton restartGame = new TextButton("Restart Game", buttonSkin);

        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                hide();
            }
        });

        restartRoom.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {

            }
        });

        restartGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                setRestartStatus(true);
                hide();
            }
        });

        Table table = new Table();
        table.setDebug(true);
        table.setColor(0, 0, 0, 0.8f); // semi-transparent black
        table.setFillParent(true);
        stage.addActor(table);
        table.defaults().pad(10);

        table.add(resume).width(200).height(80).row();
        table.add(restartRoom).width(200).height(80).row();
        table.add(restartGame).width(200).height(80).row();
    }
    public void show() {
        visible = true;
        Gdx.input.setInputProcessor(stage);
    }

    public void hide() {
        visible = false;
        Gdx.input.setInputProcessor(null);
    }

    public boolean getRestartStatus() {
        return restartStatus;
    }

    public void setRestartStatus(boolean bool) {
        restartStatus = bool;
    }

    public boolean isVisible() {
        return visible;
    }

    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
