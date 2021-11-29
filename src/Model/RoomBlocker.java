package Model;

import java.io.Serializable;

/**
 * RoomBlocker is a contain for a boolean for every direction on a compass
 */
public class RoomBlocker implements Serializable {
    /**
     * Boolean field indicating whether door exists at North
     */
    private final boolean myNorth;
    /**
     * Boolean field indicating whether door exists at South
     */
    private final boolean mySouth;
    /**
     * Boolean field indicating whether door exists at East
     */
    private final boolean myEast;
    /**
     * Boolean field indicating whether door exists at West
     */
    private final boolean myWest;

    /**
     * Creates a new RoomBlocker
     *
     * @param theNorth North Boolean
     * @param theSouth South Boolean
     * @param theEast  East Boolean
     * @param theWest  West Boolean
     */
    public RoomBlocker(final boolean theNorth, final boolean theSouth, final boolean theEast, final boolean theWest) {
        this.myNorth = theNorth;
        this.mySouth = theSouth;
        this.myEast = theEast;
        this.myWest = theWest;
    }

    /**
     * Returns north boolean
     *
     * @return Returns the north boolean
     */
    public boolean getNorth() {
        return myNorth;
    }

    /**
     * Returns south boolean
     *
     * @return Returns the south boolean
     */
    public boolean getSouth() {
        return mySouth;
    }

    /**
     * Returns east boolean
     *
     * @return Returns the east boolean
     */
    public boolean getEast() {
        return myEast;
    }

    /**
     * Returns west boolean
     *
     * @return Returns the west boolean
     */
    public boolean getWest() {
        return myWest;
    }
}
