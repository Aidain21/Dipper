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

    public static class IceFloor extends TileFills{
        public IceFloor(){
            fill="iceFloor";
            texture = LevelDraw.iceTx;
            drawBackground = false;
            canWalk=true;
        }

        public void slide() {
            TextBox.updateTextBox("every day im slidin", 1);
            Player.playerSliding = true;
            Player.playerLock = true;
            Player.lockTimer = 0.12f;

        }
    }

    // Portal
    public static class Portal extends TileFills{
        public Portal(int x, int y){
            fill = "portal";
            texture = LevelDraw.portalTexture;
            dataX=x;
            dataY=y;
            canWalk=true;
        }
    }

    // In Level Portal
    public static class InLevelPortal extends TileFills{
        public InLevelPortal(int x, int y){
            fill="inportal";
            texture = LevelDraw.inPortalTx;
            dataX=x;
            dataY=y;
            canWalk=true;
        }
        public Vector2Int newPos(){
            return new Vector2Int(dataX,dataY);
        }
    }



    // Spikes
    public static class Spikes extends TileFills{

        public Spikes(int d){
            fill="spikes";
            texture = LevelDraw.spikesTx;
            dataX=d;
            canWalk=true;
        }

        public void spiked() {
            Player.playerLock = true;
            Player.lockTimer = 0.7f;
            Player.dealDamage(dataX);
            TextBox.updateTextBox("OOUCH", 1);
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
        public void fall() {
            TextBox.updateTextBox("AAAAaaa", 1);
            Player.playerLock = true;
            Player.playerFalling = true;
            Player.lockTimer = 1.7f;
            Player.dealDamage(0.5f);
        }
    }

    // Bouncy Wall
    public static class BouncyWall extends TileFills {
        public BouncyWall(float r) {
            fill = "bouncy";
            sprite = new Sprite(LevelDraw.bouncy);
            rotation = r;
            sprite.setRotation(rotation);
        }
        public void wallRotate(float r) {
            this.rotation = (this.rotation+r)%360;
            this.sprite.setRotation(this.rotation);
        }
    }

    // Color Gate
    public static class ColorGate extends TileFills {
        boolean open;
        public ColorGate(String tileFill) {
            fill = tileFill;
            drawBackground = false;
            open = false;
            setColor();
        }
        public void setColor() {
            this.open = false;
            this.canWalk = false;
            this.drawBackground = false;
            switch(fill) {
                case "rGate": texture = LevelDraw.redGateTx; break;
                case "gGate": texture = LevelDraw.greenGateTx; break;
                case "bGate": texture = LevelDraw.blueGateTx; break;
                case "yGate": texture = LevelDraw.yellowGateTx; break;
                default: break;
            }
        }
        public void open() {
            this.open = true;
            this.canWalk = true;
            this.drawBackground = true;
            this.texture = LevelDraw.openGateTx;
        }
    }

    public static class Health extends TileFills{
        public Health() {
            fill = "health";
            texture = LevelDraw.healthTx;
            drawBackground = true;
            canWalk = true;
        }
    }
}
