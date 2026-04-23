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
    float timer = 0.3f;

    public void logic(level curLevel) {
        level3logic();
        AlexLevel3Logic();
        ColorGateLogic(curLevel);
    }

    public void level3logic() {
        //Button button1 = (Button) level3.level1[6][8];

        ColorButton b = (ColorButton) finalBoss.level1[1][17];
        if (b.isPressed() && !press1) {
            DipperBoss.dipperMove(13, -7);
            press1 = true;
        }

        Button comboButton1 = (Button) level3.level1[13][5];
        Button comboButton2 = (Button) level3.level1[13][6];
        Button comboButton3 = (Button) level3.level1[13][7];
        //Button button2 = (Button) level3.level1[7][8];
        //SimpleTextures.BouncyWall wall1 = (SimpleTextures.BouncyWall) level3.level1[11][1];

        // rotates when buttons pressed
        // if (button1.isPressed() && !press1) {
        //     //TextBox.text[2] = "logic test";
        //     //yButton.getSprite().rotate(90);
        //     press1 = button1.isPressed();
        // }
        // if (button2.isPressed() && !press2) {
        //     //TextBox.text[2] = "logic test 2";
        //     //wall1.getSprite().rotate(90);
        //     press2 = button2.isPressed();
        // }
        // else if (!button2.isPressed() && press2) {
        //     //wall1.getSprite().rotate(270);
        //     press2 = false;
        // }
        // else if (!button1.isPressed() && press1) {
        //     //yButton.getSprite().rotate(270);
        //     press1 = false;
        // }
        // reveals path if button combination is correct
        if (!bCombination && comboButton1.isPressed() && !comboButton2.isPressed() && comboButton3.isPressed()) {
            //TextBox.text[2] = "logic test 3";
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

    public void AlexLevel3Logic(){
        PressureButton pb1= (PressureButton) alexlevel3.level1[18][2];
        PressureButton pb2= (PressureButton) alexlevel3.level1[2][17];

        if(pb1.pressed) {
            alexlevel3.level1[4][18]=Tile.floor.refill();//CreateTileFills("floor");
            alexlevel3.level1[4][17]=Tile.floor.refill();
            alexlevel3.level1[4][16]=Tile.floor.refill();
            alexlevel3.level1[4][15]=Tile.floor.refill();
        }

        if(pb2.pressed) {
            alexlevel3.level1[16][1] = Tile.floor.refill();
            alexlevel3.level1[15][1] = Tile.floor.refill();
            alexlevel3.level1[14][1] = Tile.floor.refill();
            alexlevel3.level1[16][2] = Tile.floor.refill();
            alexlevel3.level1[15][2] = Tile.floor.refill();
            alexlevel3.level1[14][2] = Tile.floor.refill();
            alexlevel3.level1[16][3] = Tile.floor.refill();
            alexlevel3.level1[15][3] = Tile.floor.refill();
            alexlevel3.level1[14][3] = Tile.floor.refill();
        }
    }
    public void ColorGateLogic(level curLevel) {
        boolean redButtonsPressed = true;
        boolean greenButtonsPressed = true;
        boolean blueButtonsPressed = true;
        boolean yellowButtonsPressed = true;
        for (int i = 0; i < curLevel.level1.length; i ++) {
            for (int j = 0; j < curLevel.level1[0].length; j++) {
                TileFills tile = curLevel.level1[i][j];
                switch (tile.getTileString()) {
                    case "rB": if (!((ColorButton) tile).isPressed()) redButtonsPressed = false; break;
                    case "gB": if (!((ColorButton) tile).isPressed()) greenButtonsPressed = false; break;
                    case "bB": if (!((ColorButton) tile).isPressed()) blueButtonsPressed = false; break;
                    case "yB": if (!((ColorButton) tile).isPressed()) yellowButtonsPressed = false; break;
                    case "button": Button b = (Button) tile;
                        if (b.isPressed()) {
                            b.timer += Gdx.graphics.getDeltaTime();
                            if (b.timer >= b.cooldown) {
                                b.unpress();
                                b.timer = 0;
                            }
                        } break;
                    default: break;
                }
            }
        }
        for (int x = 0; x < curLevel.level1.length; x++) {
            for (int y = 0; y < curLevel.level1[0].length; y++) {
                TileFills tile = curLevel.level1[x][y];
                switch (tile.getTileString()) {
                    case "rGate": if (redButtonsPressed) ((SimpleTextures.ColorGate) tile).open();
                        else ((SimpleTextures.ColorGate) tile).setColor();
                        break;
                    case "gGate": if (greenButtonsPressed) ((SimpleTextures.ColorGate) tile).open();
                        else ((SimpleTextures.ColorGate) tile).setColor();
                        break;
                    case "bGate": if (blueButtonsPressed) ((SimpleTextures.ColorGate) tile).open();
                        else ((SimpleTextures.ColorGate) tile).setColor();
                        break;
                    case "yGate": if (yellowButtonsPressed) ((SimpleTextures.ColorGate) tile).open();
                        else ((SimpleTextures.ColorGate) tile).setColor();
                        break;
                    default: break;
                }
            }
        }
    }
}
