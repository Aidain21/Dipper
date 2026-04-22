package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class level extends ApplicationAdapter {
    TileFills[][] level1;
    int rowCount;
    int colCount;
    int spawnRow=1;
    int spawnCol=1;
    String name ="";
    boolean filledIn=false;
    String filename = "";
    TileFills icon = Tile.floor;

    //generates new tiles
    TileFills generator = new TileFills();

    public level(int r, int c) {
        rowCount=r;
        colCount=c;
        createLevel();
        filledIn=true;
    }

    public level(int r, int c, int spawnR, int spawnC) {
        rowCount=r;
        colCount=c;
        spawnRow=spawnR;
        spawnCol=spawnC;
        createLevel();
        filledIn=true;
    }

    public level(int spawnR, int spawnC, boolean auto){
        spawnRow=spawnR;
        spawnCol=spawnC;
        filledIn=true;
    }

    public level(int r, int c, int spawnR, int spawnC, boolean auto){
        rowCount=r;
        colCount=c;
        spawnRow=spawnR;
        spawnCol=spawnC;
        createLevel();
        filledIn=true;
    }

    //creates a new level and fills it in with walls and floors
    public void createLevel(){
        level1=new TileFills[colCount][rowCount];
        for (int i = 0; i < rowCount; i++) {
            level1[0][i]=(generator.CreateTileFills("wall"));
            level1[colCount-1][i]=(generator.CreateTileFills("wall"));
        }
        for (int i = 0; i < colCount; i++) {
            level1[i][0]=(generator.CreateTileFills("wall"));
            level1[i][rowCount-1]=(generator.CreateTileFills("wall"));
        }
        for (int i = 1; i < rowCount-1; i++) {
            for (int j = 1; j < colCount-1; j++) {
                level1[j][i]=(generator.CreateTileFills("floor"));
            }
        }
    }

    public TileFills[][] getLevel(){
        return level1;
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
    //changeTile creates a new tile depending on given inputs
    //only given name
    public void changeTile(int r, int c, String fill){
        if((r>0 && r<rowCount) && (c>0 && c<colCount)){

            level1[c][r]=generator.CreateTileFills(fill);
        }
    }

    //given name and 2 nums, portals
    public void changeTile(int r, int c, String fill, int nextX, int nextY){
        level1[c][r]=generator.CreateTileFills(fill,nextX,nextY);
    }

    //given name, 2 nums and an unused boolean to differentiate, 2 way portal
    public void changeTile(int r, int c, String fill, int nextX, int nextY, boolean twoway){
        level1[c][r]=generator.CreateTileFills(fill,nextX,nextY);
        level1[nextY][nextX]=generator.CreateTileFills(fill,r,c);
    }

    //given 2 names, lever
    public void changeTile(int r, int c, String fill, String nFill){
        level1[c][r]=generator.CreateTileFills(fill,nFill);
    }

    //given name and 1 num, spikes
    public void changeTile(int r, int c, String fill, int damage){
        if((r>0 && r<rowCount) && (c>0 && c<colCount)){
            level1[c][r]=generator.CreateTileFills(fill,damage);
        }
    }

    // Rotation Overload
    public void changeTile(int r, int c, String fill, float i){
        if (r > 0 && r < rowCount && c > 0 && c < colCount){
            level1[c][r] = generator.CreateTileFills(fill, i);
        }
    }

    public float rotationAt(float x, float y) {
        int rX = Math.round(x / 32);
        int rY = Math.round(y / 32);
        TileFills tile = level1[rY][rX];
        if (tile instanceof SimpleTextures.BouncyWall) return tile.getRotation();
        return -1;
    }

    public TileFills tileObjectAt(float x, float y) {
        int rX = Math.round(x / 32);
        int rY = Math.round(y / 32);
        return level1[rY][rX];
    }

    public String tileAtWorldPos(float x, float y) {
        int rX = Math.round( x / 32);
        int rY = Math.round( y / 32);
        return level1[rY][rX].getTileString();
    }

    public Vector2Int changeLevel(SimpleTextures.Portal p){
        Main.bow.deleteArrows();
        return Main.moveLevel(p.dataX,p.dataY);
    }


    //add error checking
    public void swapTiles(int r1,int c1,int r2,int c2){
        if((level1[c1][r1].movable && level1[c2][r2].movable)
            || (level1[c1][r1].movable && (level1[c2][r2] instanceof Box) && !level1[c2][r2].movable)) {
            TileFills temp = level1[c1][r1];
            level1[c1][r1] = level1[c2][r2];
            level1[c2][r2] = temp;
        }
    }



}
