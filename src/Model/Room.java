package Model;

public class Room {
    private final Door[] myRoomDoors;
    private final RoomBlocker myBlockedDoors;
    private static final int DOOR1_INDEX = 0;
    private static final int DOOR2_INDEX = 1;
    private static final int DOOR3_INDEX = 2;
    private static final int DOOR4_INDEX = 3;
    private static final int TOTAL_DOORS = 4;

    public Room(final RoomBlocker theBlocker, Door theNorthDoor, Door theSouthDoor, Door theEastDoor, Door theWestDoor){
        this.myBlockedDoors = theBlocker;
        this.myRoomDoors = new Door[TOTAL_DOORS];
        if (theBlocker.getNorth()){
            this.myRoomDoors[DOOR1_INDEX] = theNorthDoor;
        }
        if (theBlocker.getSouth()){
            this.myRoomDoors[DOOR2_INDEX] = theSouthDoor;
        }
        if (theBlocker.getEast()){
            this.myRoomDoors[DOOR3_INDEX] = theEastDoor;
        }
        if (theBlocker.getWest()){
            this.myRoomDoors[DOOR4_INDEX] = theWestDoor;
        }
    }

    /**
     * Returns which doors are permanently blocked
     * @return Returns a RoomBlocker
     */
    public RoomBlocker getBlockedDoors() {
        return myBlockedDoors;
    }

    /**
     *
     * @param theDoorIndex The index of the door
     * @return Returns a door based on the index
     */
    public Door getDoor(final int theDoorIndex){
        return myRoomDoors[theDoorIndex];
    }
}
