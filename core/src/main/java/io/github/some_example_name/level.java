package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class level extends ApplicationAdapter {
    TileFills[][] level1;
    int rowCount;
    int colCount;
    int spawnRow=1;
    int spawnCol=1;
    TileFills generator = new TileFills();

    Texture brickWallTexture = new Texture("brickWall.png");
    Texture backgroundTexture = new Texture("background.png");
    Texture crateTexture = new Texture("blockCrate.png");
    Texture portalTexture = new Texture("portal.png");
    Texture diagonalWall = new Texture("diagonalWall.png");

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

    public void createLevel(){
        level1=new TileFills[colCount][rowCount];

        for (int i = 0; i < rowCount; i++) {
            level1[0][i]=(generator.CreateTileFills('w'));
            level1[colCount-1][i]=(generator.CreateTileFills('w'));
        }
        for (int i = 0; i < colCount; i++) {
            level1[i][0]=(generator.CreateTileFills('w'));
            level1[i][rowCount-1]=(generator.CreateTileFills('w'));
        }
        for (int i = 1; i < rowCount-1; i++) {
            for (int j = 1; j < colCount-1; j++) {
                level1[j][i]=(generator.CreateTileFills('f'));
            }
        }
    }

    public TileFills[][] getLevel(){
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
/*
    public void fillEmpty(char fill){
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<colCount;j++){
                if(level1[j][i].findInTile('\u0000')>-1)
                    level1[j][i].addToTile(fill);;
            }
        }
    }


 */
    public void changeTile(int r, int c, char fill){
        if((r>0 && r<rowCount) && (c>0 && c<colCount)){
            level1[c][r]=generator.CreateTileFills(fill);
        }
    }

    public void changeTile(int r, int c, char fill, int nextX, int nextY){
        if((r>0 && r<rowCount) && (c>0 && c<colCount)){
            level1[c][r]=generator.CreateTileFills(fill,nextX,nextY);
        }
    }

    public char tileAtWorldPos(float x, float y) {
        int rX = (int) x / 32;
        int rY = (int) y / 32;
        return level1[rY][rX].getTileChar();
    }
    /*
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

     */

    public Vector2Int changeLevel(Portal p){
        return Main.moveLevel(p.nextX,p.nextY);
    }


//add error checking
    public void swapTiles(int r1,int c1,int r2,int c2){
        TileFills temp=level1[c1][r1];
        level1[c1][r1]=level1[c2][r2];
        level1[c2][r2]=temp;
    }



    public void drawLevel(SpriteBatch batch){

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (level1[j][i].getTileChar()=='l') {
                    batch.draw(portalTexture, i * 32, j * 32, 32, 32);
                }
                if (level1[j][i].getTileChar()=='b') {
                    batch.draw(crateTexture, i * 32, j * 32, 32, 32);
                }
                if (level1[j][i].getTileChar()=='w') {
                    batch.draw(brickWallTexture, i * 32, j * 32, 32, 32);
                }
                if (level1[j][i].getTileChar()=='f') {
                    batch.draw(backgroundTexture, i * 32, j * 32, 32, 32);
                }

            }
        }
    }

}
