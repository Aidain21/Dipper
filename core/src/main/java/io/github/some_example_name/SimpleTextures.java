package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;
public class SimpleTextures extends TileFills{

    // Brick Wall
    public static class Wall extends TileFills{
        public Wall() {
            fill = "wall";
            texture = LevelDraw.brickWallTexture;
            drawBackground = false;
        }
    }

    // Floor
    public static class Floor extends TileFills{
        public Floor(){
            fill="floor";
            texture = LevelDraw.backgroundTexture;
            drawBackground = false;
            canWalk=true;
            movable=true;
        }
    }

    // Portal
    public static class Portal extends TileFills{
        int nextX;
        int nextY;
        public Portal(int x, int y){
            fill = "portal";
            texture = LevelDraw.portalTexture;
            nextX=x;
            nextY=y;
            canWalk=true;
        }
    }

    // In Level Portal
    public static class InLevelPortal extends TileFills{
        int newX;
        int newY;
        public InLevelPortal(int x, int y){
            fill="inportal";
            texture = LevelDraw.inPortalTx;
            newX=x;
            newY=y;
            canWalk=true;
        }
        public Vector2Int newPos(){
            return new Vector2Int(newX,newY);
        }
    }


    // Spikes
    public static class Spikes extends TileFills{
        int damage=1;
        public Spikes(int d){
            fill="spikes";
            texture = LevelDraw.spikesTx;
            damage=d;
            canWalk=true;
        }

        public void spiked() {
            Player.playerLock = true;
            Player.lockTimer = 0.7f;
            Player.dealDamage(damage);
            TextBox.text[1] = "OOUCH";
        }
    }

    // Void
    public static class Void extends TileFills{
        public Void() {
            fill = "void";
            texture = LevelDraw.voidTx;
            drawBackground = false;
            canWalk = true;
        }
        public void fall(int x, int y) {
            TextBox.text[1] = "AAAAaaa";
            Player.playerLock = true;
            Player.playerFalling = true;
            Player.lockTimer = 1.7f;
            Player.dealDamage(0.5f);
            Player.originalX = x;
            Player.originalY = y;
        }
    }

    // Bouncy Wall
    public static class BouncyWall extends TileFills {
        public BouncyWall(float r) {
            fill = "bouncy";
            sprite = new Sprite(LevelDraw.bouncy);
            sprite.setRotation(r);
        }
        public BouncyWall(int x, int y, float r) {
            fill = "bouncy";
            sprite = new Sprite(LevelDraw.bouncy);
            sprite.setRotation(r);
            sprite.setPosition(x*32, y*32);
        }
    }

    // Color Gate
    public static class ColorGate extends TileFills {
        boolean open;
        public ColorGate(String tileFill, int x, int y) {
            fill = tileFill;
            drawBackground = false;
            open = false;
            sprite = new Sprite(LevelDraw.playerTx);
            //if (open) this.sprite = new Sprite(LevelDraw.playerTx);
            sprite.setPosition(x*32, y*32);
            setColor();
        }
        public void setColor() {
            switch(fill) {
                case "rGate": sprite.setColor(1, 0, 0, 1); break;
                case "gGate": sprite.setColor(0, 1, 0, 1); break;
                case "bGate": sprite.setColor(0, 0, 1, 1); break;
                case "yGate": sprite.setColor(1, 1, 0, 1); break;
                default: break;
            }
        }
        public void open(int x, int y) {
            this.open = true;
            this.canWalk = true;
            this.sprite = new Sprite(LevelDraw.playerTx);
            this.sprite.setPosition(x*32, y*32);
            this.sprite.setColor(1,1,1,0);
            setColor();
        }
    }
}
