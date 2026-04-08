//Restart room still needs to be implemented.
//Bug where if you restart game while falling it crashes upon respawn.
package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PauseMenuUI {
    private Stage stage;
    private Skin resumeSkin;
    private Skin resetSkin;
    private Skin restartSkin;
    private Boolean visible;
    private Boolean restartStatus;
    private Boolean restartRoomStatus;

    public PauseMenuUI(Skin resumeSkin, Skin resetSkin, Skin restartSkin) {
        this.resumeSkin = resumeSkin;
        this.resetSkin = resetSkin;
        this.restartSkin = restartSkin;
        this.stage = new Stage(new FitViewport(960, 720));
        this.visible = false;
        this.restartStatus = false;
        this.restartRoomStatus = false;

        Button resume = new Button(resumeSkin);
        Button restartRoom = new Button(resetSkin);
        Button restartGame = new Button(restartSkin);

        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                hide();
            }
        });

        restartRoom.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                setRestartRoomStatus(true);
                hide();
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
        table.setColor(0, 0, 0, 1f);
        table.setFillParent(true);
        stage.addActor(table);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0.2f, 0.2f, 0.2f, 0.75f);
        pixmap.fill();
        Texture bgTexture = new Texture(pixmap);
        pixmap.dispose();

        table.setBackground(new TextureRegionDrawable(new TextureRegion(bgTexture)));

        table.defaults().pad(10);
        Texture logoTexture = new Texture(Gdx.files.internal("uiPaused.png"));
        Image logo = new Image(logoTexture);

        table.add(logo).width(200).height(100).padBottom(20).row();
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
    public boolean getRestartRoomStatus() {
        return restartRoomStatus;
    }
    public boolean getRestartStatus() {
        return restartStatus;
    }
    public void setRestartRoomStatus(boolean bool) {
        restartRoomStatus = bool;
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
