package io.github.some_example_name;

public class Tile {

    private int TileLimit=10;
    TileFills[] inTile = new TileFills[TileLimit];
    private TileFills temp = new TileFills();

    public Tile(){
        inTile[0]=new TileFills();
    }

    public Tile(char letter){
        inTile[0]=temp.CreateTileFills(letter);
    }

    public Tile(TileFills[] filled){
        inTile=filled;
    }

    public TileFills[] getTile(){
        return inTile;
    }

    public int findInTile(char search){
        for(int i=0;i<TileLimit;i++){
            if(inTile[i].getTileChar()==search)
                return i;
        }
        return -1;
    }

    public void addToTile(TileFills fill){
        for(int i=0;i<TileLimit;i++){
            if(inTile[i].equals(new Tile().inTile[5])){
                inTile[i]=fill;
                break;
            }
        }
    }

    public void addToTile(char fillChar){
        for(int i=0;i<TileLimit;i++){
            if(inTile[i].equals(null)){
                inTile[i]=temp.CreateTileFills(fillChar);
                break;
            }
        }
    }

    public void replaceTile(TileFills[] fill){
        inTile=fill;
    }

}
