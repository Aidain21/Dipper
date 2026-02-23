package io.github.some_example_name;

public class Lever extends TileFills{
    TileFills gen;
    boolean isFlipped=false;
    char newFill='f';
    public Lever(char nFill){
        fill='s';
        newFill=nFill;
    }

    public void onFlip(int x, int y, level l){
        if(!isFlipped){
            l.changeTile(x,y,newFill);
            isFlipped=true;
        }
    }

    public void onFlip(char fill, int x, int y, level l,int nextx, int nexty){
        if(!isFlipped){
            l.changeTile(x,y,newFill,nextx,nexty);
            isFlipped=true;
        }
    }
}
