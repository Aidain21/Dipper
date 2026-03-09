package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainMenuUI {
    private static Stage stage;
    private static Skin buttonSkin;

    public static void MainMenuUI(Skin skin) {
        this.buttonSkin = buttonSkin;
        this.stage = stage;

        TextButton resume = new TextButton("Resume", buttonSkin);
        TextButton restartRoom = new TextButton("Restart Room", buttonSkin);
        TextButton restartGame = new TextButton("Restart Game", buttonSkin);
        TextButton controls = new TextButton("Controls", buttonSkin);

        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                stage.dispose();
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

            }
        });

        controls.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {

            }
        });

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.add(resume).center().width(200).height(100);
        table.add(restartRoom).top().width(200).height(100);
        table.add(restartGame).left().width(200).height(100);
        table.add(controls).right().width(200).height(100);

    }
}
