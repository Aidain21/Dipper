package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
public class Lever extends TileFills{
    public static Texture leverTexture = new Texture("lever.png");
    //public static Texture lever2Texture = new Texture("lever2.png");
    TileFills gen;
    boolean isFlipped=false;
    char newFill;
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

    public void onFlip(char fill, int x, int y, level l, int nextx, int nexty){
        if(!isFlipped){
            l.changeTile(x,y,newFill,nextx,nexty);
            isFlipped=true;
        }
    }
}
