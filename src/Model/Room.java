package Model;

public class Room {
    private final Door[] myRoomDoors;
    private final RoomBlocker myBlockedDoors;
    private static final int DOOR1_INDEX = 0;
    private static final int DOOR2_INDEX = 1;
    private static final int DOOR3_INDEX = 2;
    private static final int DOOR4_INDEX = 3;
    private static final int TOTAL_DOORS = 4;

    public Room(final RoomBlocker theBlocker){
        this.myBlockedDoors = theBlocker;
        this.myRoomDoors = new Door[TOTAL_DOORS];
        if (theBlocker.getNorth()){
            this.myRoomDoors[DOOR1_INDEX] = new Door();
        }
        if (theBlocker.getSouth()){
            this.myRoomDoors[DOOR2_INDEX] = new Door();
        }
        if (theBlocker.getEast()){
            this.myRoomDoors[DOOR3_INDEX] = new Door();
        }
        if (theBlocker.getWest()){
            this.myRoomDoors[DOOR4_INDEX] = new Door();
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

    /**
     * When Player enters a Room, the door that they entered is unlocked
     * North Direction (Unlocks South Door)
     * South Direction (Unlocks North Door)
     * East Direction (Unlocks West Door)
     * West Direction (Unlocks East Door)
     * @param theDirection The direction that the player comes from.
     */
    public void enterRoom(Direction theDirection){
        switch (theDirection){
            case NORTH -> getDoor(DOOR2_INDEX).unlockedFromOtherSide();
            case SOUTH -> getDoor(DOOR1_INDEX).unlockedFromOtherSide();
            case EAST -> getDoor(DOOR4_INDEX).unlockedFromOtherSide();
            case WEST -> getDoor(DOOR3_INDEX).unlockedFromOtherSide();
        }
    }
}
