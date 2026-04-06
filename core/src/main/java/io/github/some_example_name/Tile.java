package io.github.some_example_name;

public class Tile  {
    public static TileFills wall = new TileFills().CreateTileFills("wall");
    public static TileFills bouncyWall1;
    public static TileFills bouncyWall2;
    public static TileFills bouncyWall3;
    public static TileFills bouncyWall4;
    public static TileFills redButtonU;
    public static TileFills redButtonL;
    public static TileFills redButtonD;
    public static TileFills redButtonR;
    public static TileFills blueButtonU;
    public static TileFills blueButtonL;
    public static TileFills blueButtonD;
    public static TileFills blueButtonR;
    public static TileFills yellowButtonU;
    public static TileFills yellowButtonL;
    public static TileFills yellowButtonD;
    public static TileFills yellowButtonR;
    public static TileFills greenButtonU;
    public static TileFills greenButtonL;
    public static TileFills greenButtonD;
    public static TileFills greenButtonR;
    public static TileFills redGate;
    public static TileFills blueGate;
    public static TileFills yellowGate;
    public static TileFills greenGate;
    public static TileFills button;
    public static TileFills floor;
    public static TileFills box;
    public static TileFills spikes;
    public static TileFills voidTile;
    public static TileFills pressureButton;
    public static TileFills outPortal;
    public static TileFills inPortal;
    public static TileFills iceFloor;

    public static TileFills[] tileArray;
    public static TileFills[] drawTileArray;



    public static void startTile() {
        TileFills gen = new TileFills();

        wall = gen.CreateTileFills("wall");

        bouncyWall1 = gen.CreateTileFills("bouncy",0f);
        bouncyWall2 = gen.CreateTileFills("bouncy",90f);
        bouncyWall3 = gen.CreateTileFills("bouncy",180f);
        bouncyWall4 = gen.CreateTileFills("bouncy",270f);

        redButtonU = gen.CreateTileFills("rB",0f);
        redButtonL = gen.CreateTileFills("rB",90f);
        redButtonD = gen.CreateTileFills("rB",180f);
        redButtonR = gen.CreateTileFills("rB",270f);

        blueButtonU = gen.CreateTileFills("bB",0f);
        blueButtonL = gen.CreateTileFills("bB",90f);
        blueButtonD = gen.CreateTileFills("bB",180f);
        blueButtonR = gen.CreateTileFills("bB",270f);

        yellowButtonU = gen.CreateTileFills("yB",0f);
        yellowButtonL = gen.CreateTileFills("yB",90f);
        yellowButtonD = gen.CreateTileFills("yB",180f);
        yellowButtonR = gen.CreateTileFills("yB",270f);

        greenButtonU = gen.CreateTileFills("gB",0f);
        greenButtonL = gen.CreateTileFills("gB",90f);
        greenButtonD = gen.CreateTileFills("gB",180f);
        greenButtonR = gen.CreateTileFills("gB",270f);

        redGate = gen.CreateTileFills("rGate");
        blueGate = gen.CreateTileFills("bGate");
        yellowGate = gen.CreateTileFills("yGate");
        greenGate = gen.CreateTileFills("gGate");

        button = gen.CreateTileFills("button");
        floor = gen.CreateTileFills("floor");
        spikes = gen.CreateTileFills("spikes", 1);
        box = gen.CreateTileFills("box");
        voidTile = gen.CreateTileFills("void");
        pressureButton = gen.CreateTileFills("pressureButton");
        outPortal = gen.CreateTileFills("portal",-1,-1);
        inPortal = gen.CreateTileFills("inportal",-1,-1);
        iceFloor = gen.CreateTileFills("iceFloor");

        tileArray = new TileFills[] {
            wall, bouncyWall1, bouncyWall2, bouncyWall3, bouncyWall4, redButtonU, redButtonL, redButtonD, redButtonR,
            blueButtonU, blueButtonL, blueButtonD, blueButtonR, yellowButtonU, yellowButtonL, yellowButtonD, yellowButtonR,
            greenButtonU, greenButtonL, greenButtonD, greenButtonR, redGate, blueGate, yellowGate, greenGate, button, floor,
            spikes, box, voidTile, pressureButton, outPortal, inPortal, iceFloor
        };

        drawTileArray = new TileFills[] {
            wall, bouncyWall1, redButtonU, blueButtonU, yellowButtonU, greenButtonU,
            redGate, blueGate, yellowGate, greenGate, button, spikes,
            box, voidTile, pressureButton, outPortal, inPortal, iceFloor
        };



    }
}
