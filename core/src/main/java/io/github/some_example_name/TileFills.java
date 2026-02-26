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
            default: return new TileFills();
        }
    }

    public TileFills CreateTileFills(String fill, int x, int y){
        if(fill.equals("portal"))
            return new Portal(x,y);
        else if(fill.equals("inportal"))
            return new InLevelPortal(x,y);
       else if (fill.equals("pressureButton"))
            return new PressureButton(x, y);
       return new TileFills();
    }

    public TileFills CreateTileFills(String fill, String newFill){
        return new Lever(newFill);
    }

    // Rotation
    public TileFills CreateTileFills(int x, int y, String fill, float r) {
        switch(fill) {
            case "bouncy": return new BouncyWall(x, y, r);
            case "r":
            case "g":
            case "b":
            case "y": return new ColorButton(x, y, fill, r);
            default: return new TileFills();
        }
    }

    public String getTileString(){
        return fill;
    }
}

