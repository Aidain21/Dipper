package io.github.some_example_name;

import com.badlogic.gdx.math.Vector2;

public class Portal extends TileFills{
    private int nextX;
    private int nextY;
    public Portal(int x, int y){
        fill='p';
        nextX=x;
        nextY=y;
    }

    public Vector2Int nextLevelPos(){
        return new Vector2Int(nextX,nextY);
    }

}
