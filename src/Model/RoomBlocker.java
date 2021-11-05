package Model;

public class RoomBlocker {
    private final boolean theNorth;
    private final boolean theSouth;
    private final boolean theEast;
    private final boolean theWest;

    public RoomBlocker(final boolean myNorth, final boolean mySouth, final boolean myEast, final boolean myWest){
        this.theNorth = myNorth;
        this.theSouth = mySouth;
        this.theEast = myEast;
        this.theWest = myWest;
    }

    public boolean getNorth() {
        return theNorth;
    }

    public boolean getSouth() {
        return theSouth;
    }

    public boolean getEast() {
        return theEast;
    }

    public boolean getWest() {
        return theWest;
    }
}
