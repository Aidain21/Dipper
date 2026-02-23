package io.github.some_example_name;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextBox {

    public static String[] text;
    public static String[] textRight;

    public TextBox() {
        text = new String[] {"Row 1", "Row 2", "Row 3"};
        textRight = new String[] {"Row 4", "Row 5", "Row 6"};
    }

    public void drawTextBox(SpriteBatch batch) {
        BitmapFont font = new BitmapFont();
        font.draw(batch, text[0], 5, Gdx.graphics.getHeight() - 5);
        font.draw(batch, text[1], 5, Gdx.graphics.getHeight() - 25);
        font.draw(batch, text[2], 5, Gdx.graphics.getHeight() - 45);

        font.draw(batch, textRight[0], (float) Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 5);
        font.draw(batch, textRight[1], (float) Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 25);
        font.draw(batch, textRight[2], (float) Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 45);
    }
}
