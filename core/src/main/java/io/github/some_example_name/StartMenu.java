package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class StartMenu {
    private Stage stage;
    private Skin startSkin;
    private Skin editorSkin;
    private boolean visible;

    public StartMenu(Skin startSkin, Skin editorSkin) {
        this.startSkin = startSkin;
        this.editorSkin = editorSkin;
        this.stage = new Stage(new FitViewport(960, 720));
        this.visible = true;

        Button start = new Button(startSkin);
        Button editor = new Button(editorSkin);

        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                hide();
            }
        });

        editor.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                hide();
            }
        });

        Table table = new Table();
        table.setColor(0, 0, 0, 1f); // color and transparency of buttons
        table.setFillParent(true);
        stage.addActor(table);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0f, 0f, 0f, 1); //color and transparency of background
        pixmap.fill();
        Texture bgTexture = new Texture(pixmap);
        pixmap.dispose();

        table.setBackground(new TextureRegionDrawable(new TextureRegion(bgTexture)));

        table.defaults().pad(10);

        table.add(start).width(200).height(80).row();
        table.add(editor).width(200).height(80).row();
    }

    public int show() {
        visible = true;
        Gdx.input.setInputProcessor(stage);
        return 1;
    }

    public int hide() {
        visible = false;
        Gdx.input.setInputProcessor(null);
        return -1;
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
