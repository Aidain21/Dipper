package io.github.some_example_name;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextBox {

    public static String[] text;
    public static String[] textRight;
    public static String[] levelName;

    public static boolean updated;
    public static BitmapFont font;

    public TextBox() {
        text = new String[] {"", "", ""};
        textRight = new String[] {"", "", ""};
        levelName = new String[] {"Lone Beginnings"};
        font = new BitmapFont();
    }
    public static void clearText() {
        for (int i = 0; i <= 2; i++) {
            text[i] = "";
            textRight[i] = "";
        }
        levelName[0]="";
    }
    public void drawTextBox(SpriteBatch batch) {
        if (!updated) {
            return;
        }
        font.draw(batch, text[0], 5, Gdx.graphics.getHeight() - 5);
        font.draw(batch, text[1], 5, Gdx.graphics.getHeight() - 25);
        font.draw(batch, text[2], 5, Gdx.graphics.getHeight() - 45);

        font.draw(batch, textRight[0], (float) Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 5);
        font.draw(batch, textRight[1], (float) Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 25);
        font.draw(batch, textRight[2], (float) Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 45);

        font.draw(batch, levelName[0], (float) Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 65);
    }
    public static void updateTextBox(String newText, int box){
        switch (box) {
            case 0: text[0] = newText; break;
            case 1: text[1] = newText; break;
            case 2: text[2] = newText; break;
            case 3: textRight[0] = newText; break;
            case 4: textRight[1] = newText; break;
            case 5: textRight[2] = newText; break;
            case 6: levelName[0] = newText; break;
        }
        updated = true;


    }
}
