package io.github.some_example_name;

public class InLevelPortal extends TileFills{
    int newX;
    int newY;

    public InLevelPortal(int x, int y){
        fill="inportal";
        newX=x;
        newY=y;
        canWalk=true;
    }

    public Vector2Int newPos(){
        return new Vector2Int(newX,newY);
    }
}
