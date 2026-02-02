package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class level extends ApplicationAdapter {
    char[][] level1;
    int rowCount;
    int colCount;

    public level(int r, int c){
        level1=new char[r][c];
        rowCount=r;
        colCount=c;

        for (int i = 0; i < r; i++) {
            level1[i][0] = 'w';
            level1[i][c-1] = 'w';
        }
        for (int i = 0; i < c; i++) {
            level1[0][i] = 'w';
            level1[r-1][i] = 'w';
        }
        for (int j = 1; j < r-1; j++) {
            for (int b = 1; b < c-1; b++) {
                level1[j][b] = 'f';
            }
        }
    }

    public char[][] getLevel(){
        return level1;
    }
    public int getRowCount(){
        return rowCount;
    }

    public int getColCount(){
        return colCount;
    }

    public void changeTile(int r, int c, char fill){
        if((r>=0 && r<=rowCount) && (c>=0 && c<=colCount)){
            level1[r][c]=fill;
        }
    }

    public void changeRow(int r, char fill){
        if(r>0 && r<rowCount){
            for(int i=1;i<colCount-1;i++)
                level1[r][i]=fill;
        }
    }

    public void changeCol(int c, char fill){
        if(c>0 && c<colCount){
            for(int i=1;i<colCount-1;i++)
                level1[i][c]=fill;
        }
    }
}
