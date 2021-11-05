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

    public Door getDoor(final int myDoorIndex){
        return myRoomDoors[myDoorIndex];
    }
}
