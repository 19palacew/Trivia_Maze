package Model;

public class Room {
    private final Door[] myRoomDoors;
    private final RoomBlocker myBlockedDoors;

    public Room(final RoomBlocker theBlocker){
        this.myBlockedDoors = theBlocker;
        this.myRoomDoors = new Door[4];
        if (theBlocker.getNorth()){
            this.myRoomDoors[0] = new Door();
        }
        if (theBlocker.getSouth()){
            this.myRoomDoors[1] = new Door();
        }
        if (theBlocker.getEast()){
            this.myRoomDoors[2] = new Door();
        }
        if (theBlocker.getWest()){
            this.myRoomDoors[3] = new Door();
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
            case NORTH -> getDoor(0).unlockedFromOtherSide();
            case SOUTH -> getDoor(1).unlockedFromOtherSide();
            case EAST -> getDoor(2).unlockedFromOtherSide();
            case WEST -> getDoor(3).unlockedFromOtherSide();
        }
    }
}
