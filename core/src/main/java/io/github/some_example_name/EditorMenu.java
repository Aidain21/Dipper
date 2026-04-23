package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class EditorMenu {
    private Stage stage;
    private Skin saveSkin;
    private Skin saveAsSkin;
    private Skin exitWithoutSavingSkin;
    private boolean visible;

    public EditorMenu(Skin saveSkin, Skin saveAsSkin, Skin exitWithoutSavingSkin) {
        this.stage = new Stage(new FitViewport(960, 720));
        this.saveSkin = saveSkin;
        this.saveAsSkin = saveAsSkin;
        this.exitWithoutSavingSkin = exitWithoutSavingSkin;
        this.visible = false;

        Button save = new Button(saveSkin);
        Button saveAs = new Button(saveAsSkin);
        Button exitWithoutSaving = new Button(exitWithoutSavingSkin);

        save.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                Drawing.end("Save");
                hide();
            }
        });

        saveAs.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                Drawing.end("SaveAs");
                hide();
            }
        });

        exitWithoutSaving.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                Drawing.end("NoSave");
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

        table.add(save).width(200).height(80).row();
        table.add(saveAs).width(200).height(80).row();
        table.add(exitWithoutSaving).width(200).height(80).row();
    }

    public void show() {
        visible = true;
        Gdx.input.setInputProcessor(stage);
    }

    public void hide() {
        visible = false;
        Gdx.input.setInputProcessor(null);
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
