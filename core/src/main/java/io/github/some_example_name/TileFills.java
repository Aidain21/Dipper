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
            case "wall": return new Wall();
            case "box": return new Box();
            case "button": return new Button();
            default: break;
        }
        return new TileFills();
    }

    public TileFills CreateTileFills(String fill, int x, int y){
        if(fill.equals("portal"))
            return new Portal(x,y);
        else if(fill.equals("inportal"))
            return new InLevelPortal(x,y);
        return new TileFills();
    }

    public TileFills CreateTileFills(String fill, String newFill){
        return new Lever(newFill);
    }
    public BouncyWall CreateTileFills(int x, int y, String fill, float r) {
        return new BouncyWall(x, y, r);
    }

    public String getTileString(){
        return fill;
    }
}

