package io.github.some_example_name;

public class TileFills {
    char fill;
    boolean canWalk=false;
    boolean movable=false;
    //float rotation = 0;

    public TileFills() {
        fill = ' ';
    }

    public TileFills CreateTileFills(char fill){
        switch (fill) {
            case 'f': return new Floor();
            case 'w': return new Wall();
            case 'b': return new Box();
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

    public TileFills CreateTileFills(char fill, char newFill){
        return new Lever(newFill);
    }
    public BouncyWall CreateTileFills(int x, int y, char fill, float r) {
        return new BouncyWall(x, y, r);
    }

    public char getTileChar(){
        return fill;
    }
}

