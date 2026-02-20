package io.github.some_example_name;

public class TileFills {
    char fill;

    public TileFills() {
        fill = ' ';
    }

    public TileFills CreateTileFills(char fill){
        if(fill=='f')
            return new Floor();
        else if(fill=='w')
            return new Wall();
        else if(fill=='b')
            return new Box();
        else if(fill=='1')
            return new diagonalWall(1);
        else if(fill=='2')
            return new diagonalWall(2);
        else if(fill=='3')
            return new diagonalWall(3);
        else if(fill=='4')
            return new diagonalWall(4);
        return new TileFills();
    }

    public TileFills CreateTileFills(char fill, int x, int y){
        if(fill=='l')
            return new Portal(x,y);
        return new TileFills();
    }

    public char getTileChar(){
        return fill;
    }
}

