package Model;

public class RoomBlocker {
    private final boolean myNorth;
    private final boolean mySouth;
    private final boolean myEast;
    private final boolean myWest;

    public RoomBlocker(final boolean theNorth, final boolean theSouth, final boolean theEast, final boolean theWest){
        this.myNorth = theNorth;
        this.mySouth = theSouth;
        this.myEast = theEast;
        this.myWest = theWest;
    }

    public boolean getNorth() {
        return myNorth;
    }

    public boolean getSouth() {
        return mySouth;
    }

    public boolean getEast() {
        return myEast;
    }

    public boolean getWest() {
        return myWest;
    }
}
