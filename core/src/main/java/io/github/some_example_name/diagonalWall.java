package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

public class diagonalWall extends TileFills{
    public static Texture diagonalWall = new Texture("diagonalWall.png");
    public static Texture diagonalWall2 = new Texture("diagonalWall2.png");
    public static Texture diagonalWall3 = new Texture("diagonalWall3.png");
    public static Texture diagonalWall4 = new Texture("diagonalWall4.png");
    public diagonalWall(int i) {
        switch(i) {
            case 1: fill = '1'; break;
            case 2: fill = '2'; break;
            case 3: fill = '3'; break;
            case 4: fill = '4'; break;
        }

    }
}
