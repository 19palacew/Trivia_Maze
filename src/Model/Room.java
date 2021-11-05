package Model;

public class Room {
    private final Door[] theRoomDoors;
    private final RoomBlocker theBlockedDoors;

    public Room(final RoomBlocker myBlocker){
        this.theBlockedDoors = myBlocker;
        this.theRoomDoors = new Door[4];
        if (myBlocker.getNorth()){
            this.theRoomDoors[0] = new Door();
        }
        if (myBlocker.getSouth()){
            this.theRoomDoors[1] = new Door();
        }
        if (myBlocker.getEast()){
            this.theRoomDoors[2] = new Door();
        }
        if (myBlocker.getWest()){
            this.theRoomDoors[3] = new Door();
        }
    }

    public RoomBlocker getBlockedDoors() {
        return theBlockedDoors;
    }

    public Door getDoor(final int myDoorIndex){
        return theRoomDoors[myDoorIndex];
    }
}
