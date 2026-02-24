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
    BouncyWall[][] bouncyWalls;

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

    public void createLevel(){
        level1=new TileFills[colCount][rowCount];
        bouncyWalls = new BouncyWall[colCount][rowCount];
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

    public BouncyWall[][] getObject(char obj){
        return bouncyWalls;
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

    public void changeTile(int r, int c, char fill, char nFill){
        if((r>0 && r<rowCount) && (c>0 && c<colCount)){
            level1[c][r]=generator.CreateTileFills(fill,nFill);
        }
    }

    // Bouncy Wall Object Overload
    public void changeTile(int r, int c, char fill, float i){
        if((r>0 && r<rowCount) && (c>0 && c<colCount)){
            level1[c][r]=generator.CreateTileFills(r, c, fill, i);
        }
        bouncyWalls[r][c]= new BouncyWall(r, c, i);
    }

    public float rotationAt(float x, float y) {
        return bouncyWalls[Math.round((x/32))][Math.round((y/32))].getRotation()%360;
    }

    public char tileAtWorldPos(float x, float y) {
        int rX = Math.round( x / 32);
        int rY = Math.round( y / 32);
        return level1[rY][rX].getTileChar();
    }

    public Vector2Int changeLevel(Portal p){
        Main.bow.deleteArrows();
        return Main.moveLevel(p.nextX,p.nextY);
    }


//add error checking
    public void swapTiles(int r1,int c1,int r2,int c2){
        if(level1[c1][r1].movable && level1[c2][r2].movable) {
            TileFills temp = level1[c1][r1];
            level1[c1][r1] = level1[c2][r2];
            level1[c2][r2] = temp;
        }
    }

    public void drawLevel(SpriteBatch batch){
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                switch(level1[j][i].getTileChar()) {
                    case 'l': batch.draw(portalTexture, i*32, j*32, 32, 32); break;
                    case 'b': batch.draw(crateTexture, i*32, j*32, 32, 32); break;
                    case 'w': batch.draw(Wall.brickWallTexture, i*32, j*32, 32, 32); break;
                    case 'f': drawBackground(batch, i, j); break;
                    case 's': drawBackground(batch, i, j); batch.draw(Lever.leverTexture, i*32, j*32); break;
                    case 'r':
                        drawBackground(batch, i, j);
                        (Main.currentLevel.getObject('b')[i][j]).getSprite().draw(batch);
                        break;
                    default: break;
                }

            }
        }
    }
    private void drawBackground(SpriteBatch batch, int i , int j) {
        batch.draw(backgroundTexture, i * 32, j * 32, 32, 32);
    }
}
