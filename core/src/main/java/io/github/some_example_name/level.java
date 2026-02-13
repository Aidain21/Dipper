package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class level extends ApplicationAdapter {
    char[][] level1;
    int rowCount;
    int colCount;
    int spawnRow=1;
    int spawnCol=1;

    //char[][] levelBase;
    //int currentRow;
    //int currentCol;
    //level currentLevel;
    //map levels;
    Texture brickWallTexture = new Texture("brickWall.png");
    Texture backgroundTexture = new Texture("background.png");
    Texture crateTexture = new Texture("blockCrate.png");
    Texture portalTexture = new Texture("portal.png");

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
        level1=new char[colCount][rowCount];

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
                level1[j][i] = ' ';
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

    public int getSpawnRow(){
        return spawnRow;
    }

    public int getSpawnCol(){
        return spawnCol;
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

    public Vector2Int changeLevel(char direction){
        Vector2Int temp = new Vector2Int();
        if(direction=='u') {
            temp=Main.moveLevel('r', '+');
        }
        if (direction=='d') {
            temp=Main.moveLevel('r', '-');
        }
        if(direction=='r') {
            temp=Main.moveLevel('c', '+');
        }
        if(direction=='l') {
            temp=Main.moveLevel('c', '-');
        }
        return temp;
    }

//add swap tiles
    public void swapTiles(int r1,int c1,int r2,int c2){
        char temp=level1[c1][r1];
        level1[c1][r1]=level1[c2][r2];
        level1[c2][r2]=temp;
    }

    public void drawLevel(SpriteBatch batch){

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (level1[j][i] == 'w') {
                    batch.draw(brickWallTexture, i * 32, j * 32, 32, 32);
                }
                if (level1[j][i] == ' ') {
                    batch.draw(backgroundTexture, i * 32, j * 32, 32, 32);
                }
                if (level1[j][i] == 'l') {
                    batch.draw(portalTexture, i * 32, j * 32, 32, 32);
                }
                if (level1[j][i] == 'b') {
                    batch.draw(crateTexture, i * 32, j * 32, 32, 32);
                }
            }
        }
    }


}
