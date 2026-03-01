package io.github.some_example_name;

public class Box extends TileFills{
    int coverX, coverY, oldX, oldY;
    boolean covering;
    public Box(){
        fill="box";
        texture = LevelDraw.crateTexture;
        drawBackground = false;
        movable=true;
        covering = false;
    }
    public void cover(int x, int y, int x2, int y2) {
        this.coverX = x;
        this.coverY = y;
        this.oldX = x2;
        this.oldY = y2;
        this.covering = true;}
    public boolean isCovering() {return this.covering;}
    public void uncover() {this.coverX = 0; this.coverY = 0; this.oldX = 0; this.oldY = 0; this.covering = false;}
    public void tryPush(int lookX, int lookY, int facingX, int facingY, level curLevel) {
        int tx = lookX + facingX;
        int ty = lookY + facingY;
        TileFills currentTile = curLevel.level1[lookY][lookX];
        TileFills targetTile  = curLevel.level1[ty][tx];
        if (targetTile instanceof Box) return;
        Box box = (Box) currentTile;
        if (box.isCovering()) {
            if (!(targetTile instanceof PressureButton))
                curLevel.swapTiles(lookX, lookY, tx, ty);
            curLevel.swapTiles(box.coverX, box.coverY, box.oldX, box.oldY);
            TileFills coveredTile = curLevel.level1[lookY][lookX];
            if (coveredTile instanceof PressureButton)
                ((PressureButton) coveredTile).unpress();
            box.uncover();
            return;
        }
        // Presses pressure button if the box is moved onto it
        if (targetTile instanceof PressureButton) {
            ((PressureButton) targetTile).press();
            box.cover(tx, ty, lookX, lookY);
        }
        curLevel.swapTiles(lookX, lookY, tx, ty);
    }
}
