package io.github.some_example_name;

public class Lever extends TileFills{
    TileFills gen;
    public Lever(){
        fill='s';
    }

    public void onFlip(char fill, int x, int y, level l){
        l.changeTile(x,y,fill);
    }

    public void onFlip(char fill, int x, int y, level l,int nextx, int nexty){
        l.changeTile(x,y,fill,nextx,nexty);
    }
}
