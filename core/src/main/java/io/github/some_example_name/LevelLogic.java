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
        Button button1 = (Button) level3.level1[6][8];
        Button comboButton1 = (Button) level3.level1[13][5];
        Button comboButton2 = (Button) level3.level1[13][6];
        Button comboButton3 = (Button) level3.level1[13][7];
        ColorButton gButton1 = (ColorButton) level3.level1[6][10];
        ColorButton gButton2 = (ColorButton) level3.level1[14][23];
        ColorButton gButton3 = (ColorButton) level3.level1[16][24];
        ColorButton rButton1 = (ColorButton) level3.level1[16][22];
        SimpleTextures.BouncyWall wall1 = (SimpleTextures.BouncyWall) level3.level1[11][1];
        SimpleTextures.ColorGate gate1 = (SimpleTextures.ColorGate) level3.level1[15][20];
        boolean greenButtonsPressed = true;
        for (int i = 0; i < 24; i++) {
            if ((LevelTemplates.colorButtonList[i] != null) &&!LevelTemplates.colorButtonList[i].isPressed() && LevelTemplates.colorButtonList[i].getTileString().equals("gB")) {
                greenButtonsPressed = false;
                break;
            }
        }
        if (greenButtonsPressed) gate1.open();

        if (gButton2.isPressed() && gButton3.isPressed()) {
            gate1.open();
        }

        // rotates when buttons pressed
        if (button1.isPressed()) {
            TextBox.text[2] = "logic test";
            cooldown += Gdx.graphics.getDeltaTime();
            if (cooldown >= 0.2f) {
                gButton1.getSprite().rotate(90);
                cooldown = 0f;
            }
        }
        if (rButton1.isPressed() && !rPress) {
            TextBox.text[2] = "logic test 2";
            wall1.getSprite().rotate(90);
            rPress = rButton1.isPressed();
        }
        else if (!rButton1.isPressed() && rPress) {
            wall1.getSprite().rotate(270);
            rPress = false;
        }
        // reveals path if button combination is correct
        if (!bCombination && comboButton1.isPressed() && !comboButton2.isPressed() && comboButton3.isPressed()) {
            TextBox.text[2] = "logic test 3";
            bCombination = true;
            comboButton1.lock();
            comboButton2.lock();
            comboButton3.lock();
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
