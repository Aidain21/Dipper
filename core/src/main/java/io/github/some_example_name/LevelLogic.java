package io.github.some_example_name;

import com.badlogic.gdx.Gdx;

public class LevelLogic extends LevelTemplates {
    public LevelLogic() { }
    float cooldown = 0f;
    boolean rPress = false;
    boolean bCombination = false;
    int step = 0;
    float wallTimer = 0f;
    TileFills tile = new TileFills();

    public void logic(level curLevel) {
        level3logic();
    }
    public void level3logic() {

        // rotates when buttons pressed
        if (((Button) level3.level1[6][8]).isPressed()) {
            TextBox.text[2] = "logic test";
            cooldown += Gdx.graphics.getDeltaTime();
            if (cooldown >= 0.2f) {
                level3.level1[6][10].getSprite().rotate(90);
                cooldown = 0f;
            }
        }
        if (((ColorButton) level3.level1[16][22]).isPressed() && !rPress) {
            TextBox.text[2] = "logic test 2";
            level3.level1[11][1].getSprite().rotate(90);
            rPress = ((ColorButton) level3.level1[16][22]).isPressed();
        }
        else if (!((ColorButton) level3.level1[16][22]).isPressed() && rPress) {
            level3.level1[11][1].getSprite().rotate(270);
            rPress = false;
        }
        // reveals path if button combination is correct
        if (!bCombination && ((Button) level3.level1[13][7]).isPressed() && !((Button) level3.level1[13][6]).isPressed()
            && ((Button) level3.level1[13][5]).isPressed()) {
            TextBox.text[2] = "logic test 3";
            bCombination = true;
            ((Button) level3.level1[13][7]).lock();
            ((Button) level3.level1[13][6]).lock();
            ((Button) level3.level1[13][5]).lock();
        }

        if (!bCombination) return;
        wallTimer += Gdx.graphics.getDeltaTime();
        if (wallTimer >= 0.6f) {
            int i = 16 + step;
            if (i <= 19) {
                for (int j = 5; j <= 7; j++) if (i != 18 || j != 6) level3.level1[i][j] = tile.CreateTileFills("floor");
                step++;
                wallTimer = 0f;
            } else bCombination = false;
        }
    }
}
