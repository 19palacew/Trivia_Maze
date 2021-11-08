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

    public RoomBlocker getBlockedDoors() {
        return myBlockedDoors;
    }

    public Door getDoor(final int theDoorIndex){
        return myRoomDoors[theDoorIndex];
    }

    public void enterRoom(Direction theDirection){
        switch (theDirection){
            case NORTH -> getDoor(DOOR1_INDEX).unlockedFromOtherSide();
            case SOUTH -> getDoor(DOOR2_INDEX).unlockedFromOtherSide();
            case EAST -> getDoor(DOOR3_INDEX).unlockedFromOtherSide();
            case WEST -> getDoor(DOOR4_INDEX).unlockedFromOtherSide();
        }
    }
}
