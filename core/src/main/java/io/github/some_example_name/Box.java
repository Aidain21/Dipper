package io.github.some_example_name;

public class Box extends TileFills{
    int coverX, coverY, oldX, oldY;
    boolean covering;
    String tileCovering;
    public Box(){
        fill="box";
        texture = LevelDraw.crateTexture;
        drawBackground = false;
        movable=true;
        covering = false;
    }
    public void cover(level curLevel, int x, int y, int x2, int y2) {
        this.coverX = x;
        this.coverY = y;
        this.oldX = x2;
        this.oldY = y2;
        this.covering = true;
        this.tileCovering = curLevel.level1[y][x].getTileString();
    }
    public boolean isCovering() {return this.covering;}
    public String stringCovering() {return this.tileCovering;}
    public void uncover() {this.coverX = 0; this.coverY = 0; this.oldX = 0; this.oldY = 0; this.covering = false; this.tileCovering = null;}
    public void tryPush(int lookX, int lookY, int facingX, int facingY, level curLevel) {
        int tx = lookX + facingX;
        int ty = lookY + facingY;
        TileFills currentTile = curLevel.level1[lookY][lookX];
        TileFills targetTile  = curLevel.level1[ty][tx];
        Box box = (Box) currentTile;
        if (targetTile instanceof Box && targetTile.canWalk && currentTile.movable) {
            if (box.isCovering() && box.stringCovering().equals("box")) {
                box.cover(curLevel, tx, ty, lookX, lookY);
                curLevel.swapTiles(lookX, lookY, tx, ty);
                boxFall(curLevel, lookX, lookY);
                return;
            }
            box.cover(curLevel, tx, ty, lookX, lookY);
            curLevel.swapTiles(lookX, lookY, tx, ty);
            curLevel.level1[lookY][lookX] = new SimpleTextures.Floor();
            return;
        }
        if (targetTile instanceof SimpleTextures.Void && currentTile.movable) {
            if (!box.isCovering()) {
                boxFall(curLevel, tx, ty);
                curLevel.level1[lookY][lookX] = new SimpleTextures.Floor();
                return;
            }
            if (box.stringCovering().equals("box")) {
                boxFall(curLevel, tx, ty);
                boxFall(curLevel, lookX, lookY);
                return;
            }
        }
        if (box.isCovering()) {
            if (!(targetTile instanceof PressureButton) && box.stringCovering().equals("pressureButton"))
                curLevel.swapTiles(lookX, lookY, tx, ty);
            if (!box.stringCovering().equals("box"))
                curLevel.swapTiles(box.coverX, box.coverY, box.oldX, box.oldY);
            TileFills coveredTile = curLevel.level1[lookY][lookX];
            switch(box.stringCovering()) {
                case "box":
                    boxFall(curLevel, lookX, lookY);
                    curLevel.level1[ty][tx] = new Box();
                    return;
                case "pressureButton":
                    ((PressureButton) coveredTile).unpress();
                    box.uncover();break;
                default: break;
            }
            return;
        }
        // Presses pressure button if the box is moved onto it
        if (targetTile instanceof PressureButton) {
            ((PressureButton) targetTile).press();
            box.cover(curLevel, tx, ty, lookX, lookY);
        }
        curLevel.swapTiles(lookX, lookY, tx, ty);
    }
    private void boxFall(level curLevel, int tx, int ty) {
        curLevel.level1[ty][tx] = new Box();
        curLevel.level1[ty][tx].texture = LevelDraw.fallBoxTx;
        curLevel.level1[ty][tx].movable = false;
        curLevel.level1[ty][tx].canWalk = true;
    }
}
