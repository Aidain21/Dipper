package io.github.some_example_name;

public class Lever extends TileFills{
    TileFills gen;
    boolean isFlipped=false;
    String newFill="floor";
    public Lever(String nFill){
        fill="lever";
        texture = LevelDraw.leverTx;
        newFill=nFill;
    }

    public void onFlip(int x, int y, level l){
        if(!isFlipped){
            l.changeTile(x,y,newFill);
            isFlipped=true;
            texture = LevelDraw.lever2Tx;
        }
    }

    public void onFlip(String fill, int x, int y, level l,int nextx, int nexty){
        if(!isFlipped){
            l.changeTile(x,y,newFill,nextx,nexty);
            isFlipped=true;
        }
    }
}
