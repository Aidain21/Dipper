package io.github.some_example_name;

import com.badlogic.gdx.math.Vector2;

public class Portal extends TileFills{
    int nextX;
    int nextY;
    public Portal(int x, int y){
        fill="portal";
        nextX=x;
        nextY=y;
        canWalk=true;
    }

    public Vector2Int nextLevelPos(){
        return new Vector2Int(nextX,nextY);
    }

}
