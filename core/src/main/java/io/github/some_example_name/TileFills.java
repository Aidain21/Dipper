package io.github.some_example_name;

public class TileFills {
    char fill;

    public TileFills() {
        fill = ' ';
    }

    public TileFills CreateTileFills(char fill){
        if(fill=='l')
            return new Portal(1,0);
        else if(fill=='f')
            return new Floor();
        else if(fill=='w')
            return new Wall();
        else if(fill=='b')
            return new Box();
        else if(fill=='r')
            return new diagonalWall(0);
        return new TileFills();


    }

    public char getTileChar(){
        return fill;
    }
}

