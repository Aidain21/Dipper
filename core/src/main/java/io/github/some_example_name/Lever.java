package io.github.some_example_name;

public class Lever extends TileFills{
    TileFills gen;
    boolean isFlipped=false;
    public Lever(){
        fill='s';
    }

    public void onFlip(char fill, int x, int y, level l){
        if(!isFlipped){
            l.changeTile(x,y,fill);
            isFlipped=true;
        }
    }

    public void onFlip(char fill, int x, int y, level l,int nextx, int nexty){
        if(!isFlipped){
            l.changeTile(x,y,fill,nextx,nexty);
            isFlipped=true;
        }
    }
}
