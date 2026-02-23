package io.github.some_example_name;

public class TileFills {
    char fill;
    boolean canWalk=false;
    boolean movable=false;

    public TileFills() {
        fill = ' ';
    }

    public TileFills CreateTileFills(char fill){
        switch (fill) {
            case 'f': return new Floor();
            case 'w': return new Walls.Wall();
            case 'b': return new Box();
            case '1':
            case '2':
            case '3':
            case '4': return new Walls.Bouncy(fill);
            default: break;
        }
        return new TileFills();
    }

    public TileFills CreateTileFills(char fill, int x, int y){
        if(fill=='l')
            return new Portal(x,y);
        else if(fill=='p')
            return new InLevelPortal(x,y);
        return new TileFills();
    }

    public char getTileChar(){
        return fill;
    }
}

