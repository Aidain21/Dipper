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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class WinMenu {
    private Stage stage;
    private Skin menuSkin;
    private Skin devSkin;
    private boolean visible;
    private boolean quitStatus;
    private boolean devStatus;

    public WinMenu(Skin menuSkin, Skin devSkin) {
        this.menuSkin = menuSkin;
        this.stage = new Stage(new FitViewport(960, 720));
        this.visible = false;
        this.quitStatus = false;

        Button dev = new Button(devSkin);
        Button main = new Button(menuSkin);

        dev.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                setDev(true);
            }
        });

        main.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                setQuitStatus(true);
                hide();
            }
        });

        Table table = new Table();
        table.setColor(0, 0, 0, 1f); // color and transparency of buttons
        table.setFillParent(true);
        stage.addActor(table);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0.2f, 0.2f, 0.2f, 0.75f); //color and transparency of background
        pixmap.fill();
        Texture bgTexture = new Texture(pixmap);
        pixmap.dispose();

        table.setBackground(new TextureRegionDrawable(new TextureRegion(bgTexture)));

        table.defaults().pad(10);

        Texture logoTexture = new Texture(Gdx.files.internal("youWinUI.png"));
        Image logo = new Image(logoTexture);

        table.add(logo).width(200).height(100).padBottom(20).row();
        table.add(dev).width(200).height(80).row();
        table.add(main).width(200).height(80).row();
    }
    public void show() {
        visible = true;
        Gdx.input.setInputProcessor(stage);
    }

    public void hide() {
        visible = false;
        Gdx.input.setInputProcessor(null);
    }
    public boolean getQuitStatus() {
        return quitStatus;
    }

    public void setQuitStatus(boolean quitStatus) {
        this.quitStatus = quitStatus;
    }

    public void setDev(boolean devStatus) {
        this.devStatus = devStatus;
    }

    public boolean getDev() {
        return devStatus;
    }
    public boolean isVisible() {
        return visible;
    }

    public Stage getStage() {
        return stage;
    }

    public void dispose() {
        stage.dispose();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
