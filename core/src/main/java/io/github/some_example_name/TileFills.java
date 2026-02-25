package io.github.some_example_name;

public class TileFills {
    String fill;
    boolean canWalk=false;
    boolean movable=false;

    public TileFills() {
        fill = " ";
    }

    public TileFills CreateTileFills(String fill){
        switch (fill) {
            case "floor": return new Floor();
            case "wall": return new Walls.Wall();
            case "box": return new Box();
            case "1":
            case "2":
            case "3":
            case "4": return new Walls.Bouncy(fill);
            default: break;
        }
        return new TileFills();
    }

    public TileFills CreateTileFills(String fill, int x, int y){
        if(fill=="portal")
            return new Portal(x,y);
        else if(fill=="inportal")
            return new InLevelPortal(x,y);
        return new TileFills();
    }

    public TileFills CreateTileFills(String fill, String newFill){
        return new Lever(newFill);
    }

    public String getTileString(){
        return fill;
    }
}

