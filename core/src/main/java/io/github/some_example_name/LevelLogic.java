package io.github.some_example_name;

import com.badlogic.gdx.Gdx;

public class LevelLogic extends LevelTemplates {
    public LevelLogic() { }
    boolean press1 = false;
    boolean press2 = false;
    boolean bCombination = false;
    int step = 0;
    float wallTimer = 0f;
    TileFills tile = new TileFills();

    public void logic(level curLevel) {
        level3logic();
        ColorGateLogic();
    }
    public void level3logic() {
        Button button1 = (Button) level3.level1[6][8];
        Button comboButton1 = (Button) level3.level1[13][5];
        Button comboButton2 = (Button) level3.level1[13][6];
        Button comboButton3 = (Button) level3.level1[13][7];
        ColorButton yButton = (ColorButton) level3.level1[2][17];
        Button button2 = (Button) level3.level1[7][8];
        SimpleTextures.BouncyWall wall1 = (SimpleTextures.BouncyWall) level3.level1[11][1];

        // rotates when buttons pressed
        if (button1.isPressed() && !press1) {
            TextBox.text[2] = "logic test";
            yButton.getSprite().rotate(90);
            press1 = button1.isPressed();
        }
        if (button2.isPressed() && !press2) {
            TextBox.text[2] = "logic test 2";
            wall1.getSprite().rotate(90);
            press2 = button2.isPressed();
        }
        else if (!button2.isPressed() && press2) {
            wall1.getSprite().rotate(270);
            press2 = false;
        }
        else if (!button1.isPressed() && press1) {
            yButton.getSprite().rotate(270);
            press1 = false;
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
    public void ColorGateLogic() {
        SimpleTextures.ColorGate gGate = (SimpleTextures.ColorGate) level3.level1[17][20];
        SimpleTextures.ColorGate rGate = (SimpleTextures.ColorGate) level3.level1[17][26];
        SimpleTextures.ColorGate bGate = (SimpleTextures.ColorGate) level3.level1[17][25];
        SimpleTextures.ColorGate yGate = (SimpleTextures.ColorGate) level3.level1[17][21];
        boolean redButtonsPressed = true;
        boolean greenButtonsPressed = true;
        boolean blueButtonsPressed = true;
        boolean yellowButtonsPressed = true;
        for (int i = 0; i < LevelTemplates.colorButtons3.size(); i++) {
            if ((LevelTemplates.colorButtons3.get(i) != null) &&!LevelTemplates.colorButtons3.get(i).isPressed() && LevelTemplates.colorButtons3.get(i).getTileString().equals("rB")) {
                redButtonsPressed = false;
            }
            if ((LevelTemplates.colorButtons3.get(i) != null) &&!LevelTemplates.colorButtons3.get(i).isPressed() && LevelTemplates.colorButtons3.get(i).getTileString().equals("gB")) {
                greenButtonsPressed = false;
            }
            if ((LevelTemplates.colorButtons3.get(i) != null) &&!LevelTemplates.colorButtons3.get(i).isPressed() && LevelTemplates.colorButtons3.get(i).getTileString().equals("bB")) {
                blueButtonsPressed = false;
            }
            if ((LevelTemplates.colorButtons3.get(i) != null) &&!LevelTemplates.colorButtons3.get(i).isPressed() && LevelTemplates.colorButtons3.get(i).getTileString().equals("yB")) {
                yellowButtonsPressed = false;
            }
        }
        if (redButtonsPressed) rGate.open();
        if (greenButtonsPressed) gGate.open();
        if (blueButtonsPressed) bGate.open();
        if (yellowButtonsPressed) yGate.open();
    }
}
