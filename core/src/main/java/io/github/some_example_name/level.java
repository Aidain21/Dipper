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
    int spawnRow=0;
    int spawnCol=0;

    //char[][] levelBase;
    //int currentRow;
    //int currentCol;
    //level currentLevel;
    //map levels;
    Texture bucketTexture = new Texture("bucket.png");
    Texture dropTexture = new Texture("drop.png");

    public level(int r, int c) {
        rowCount=r;
        colCount=c;
        createLevel();
    }

    public level(int r, int c, int spawnR, int spawnC){
        rowCount=r;
        colCount=c;
        spawnRow=spawnR;
        spawnCol=spawnC;
        createLevel();


    }
    //public level(){
        //level1=new char[5][5];
    public void createLevel(){
        level1=new char[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            level1[0][i] = 'w';
            level1[colCount-1][i] = 'w';
        }
        for (int i = 0; i < colCount; i++) {
            level1[i][0] = 'w';
            level1[i][rowCount-1] = 'w';
        }
        for (int i = 1; i < rowCount-1; i++) {
            for (int j = 1; j < colCount-1; j++) {
                level1[j][i] = 'f';
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
        if((r>0 && r<rowCount) && (c>0 && c<colCount)){
            level1[c][r]=fill;
        }
    }

    public void changeRow(int r, char fill){
        if(r>0 && r<rowCount){
            for(int i=1;i<colCount-1;i++)
                level1[i][r]=fill;
        }
    }

    public void changeCol(int c, char fill){
        if(c>0 && c<colCount){
            for(int i=1;i<colCount-1;i++)
                level1[c][i]=fill;
        }
    }

    //add error checking for map edges
    public void changeLevel(char direction){
        if(direction=='u')
            Main.moveLevel('r','+');
        if (direction=='d')
            Main.moveLevel('r','-');
        if(direction=='r')
            Main.moveLevel('c','+');
        if(direction=='l')
            Main.moveLevel('c','-');
    }

//add swap tiles

    public void drawLevel(SpriteBatch batch){

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (level1[j][i] == 'w') {
                    batch.draw(bucketTexture, i * 32, j * 32, 32, 32);
                }
                if (level1[j][i] == 'f') {
                    batch.draw(dropTexture, i * 32, j * 32, 32, 32);
                }

            }
        }
    }


}
