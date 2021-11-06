package Model;

public class Room{

    private final Door[] myDoors;
    private Door myNorth;
    private Door mySouth;
    private Door myEast;
    private Door myWest;

    public Room(Boolean theNorth,Boolean theSouth, Boolean theEast, Boolean theWest) {
        if (theNorth){
            myNorth = new Door('a', "test?");
        }
        if (theSouth){
            mySouth = new Door('a', "test?");
        }
        if (theEast){
            myEast = new Door('a', "test?");
        }
        if (theWest){
            myWest = new Door('a', "test?");
        }
        myDoors = new Door[]{myNorth, mySouth, myEast, myWest};
    }

    public Door[] getDoors() {
        return myDoors;
    }
}
