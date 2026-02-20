package io.github.some_example_name;

public class TileFills {
    char fill;

    public TileFills() {
        fill = ' ';
    }

    public TileFills CreateTileFills(char fill){
        if(fill=='p')
            return new Portal(1,0);
        else if(fill=='f')
            return new Floor();
        else if(fill=='w')
            return new Wall();
        return new TileFills();


    }

    public char getTileChar(){
        return fill;
    }
}

