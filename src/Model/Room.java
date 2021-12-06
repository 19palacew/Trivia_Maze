package Model;

import java.io.Serializable;

/**
 * Rooms contain Doors in every compass direction
 * that the player has to open to move through.
 */
public class Room implements Serializable {
    /**
     * Door array field representing true false values for if the doors
     * in the room are blocked or not.
     */
    private final Door[] myRoomDoors;
    /**
     * RoomBlocker field that holds what sides will need doors.
     */
    private final RoomBlocker myBlockedDoors;
    /**
     * Boolean field stating whether room has been visited, used
     * by the isPossible method of the Maze class.
     */
    private boolean myVisited = false;
    /**
     * int field holding index of the north Door.
     */
    private static final int NORTH_DOOR_INDEX = 0;
    /**
     * int field holding index of the south Door.
     */
    private static final int SOUTH_DOOR_INDEX = 1;
    /**
     * int field holding index of the east Door.
     */
    private static final int EAST_DOOR_INDEX = 2;
    /**
     * int field holding index of the west Door.
     */
    private static final int WEST_DOOR_INDEX = 3;
    /**
     * int field holding total door value.
     */
    private static final int TOTAL_DOORS = 4;

    /**
     * Creates a new Room.
     *
     * @param theBlocker   RoomBlocker of un-openable Doors
     * @param theNorthDoor North Shared or New Door
     * @param theSouthDoor South Shared or New Door
     * @param theEastDoor  East Shared or New Door
     * @param theWestDoor  West Shared or New Door
     */
    public Room(final RoomBlocker theBlocker, final Door theNorthDoor, final Door theSouthDoor, final Door theEastDoor, final Door theWestDoor) {
        myBlockedDoors = theBlocker;
        myRoomDoors = new Door[TOTAL_DOORS];
        if (theBlocker.getNorth()) {
            myRoomDoors[NORTH_DOOR_INDEX] = theNorthDoor;
        }
        if (theBlocker.getSouth()) {
            myRoomDoors[SOUTH_DOOR_INDEX] = theSouthDoor;
        }
        if (theBlocker.getEast()) {
            myRoomDoors[EAST_DOOR_INDEX] = theEastDoor;
        }
        if (theBlocker.getWest()) {
            myRoomDoors[WEST_DOOR_INDEX] = theWestDoor;
        }
    }

    /**
     * Setter to change the visited status of room.
     *
     * @param theVisited true if visited, false if else
     */
    public void setVisited(final boolean theVisited) {
        myVisited = theVisited;
    }

    /**
     * Getter to get the visited status of room.
     *
     * @return myVisited, the field that holds whether room has been visited
     */
    public boolean getVisited() {
        return myVisited;
    }

    /**
     * Returns which doors are permanently blocked.
     *
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
    public Door getDoor(final int theDoorIndex) {
        return myRoomDoors[theDoorIndex];
    }

    /**
     *
     * @param doorDirection
     * @return
     */
    public Door getDoor(final Direction doorDirection) {
        Door localDoor = null;
        switch (doorDirection) {
            case NORTH -> localDoor = getDoor(NORTH_DOOR_INDEX);
            case SOUTH -> localDoor = getDoor(SOUTH_DOOR_INDEX);
            case EAST -> localDoor = getDoor(EAST_DOOR_INDEX);
            case WEST -> localDoor = getDoor(WEST_DOOR_INDEX);
        }
        return localDoor;
    }

    /**
     * toString method for the room, includes whether each direction is blocked
     * and shows the player in the center of each room.
     *
     * @return string representing the room
     */
    @Override
    public String toString() {
        //StringBuilder to build our output
        StringBuilder roomString = new StringBuilder();
        //Center the North movement message
        roomString.append("\n\t\t\t");
        checkMovement(NORTH_DOOR_INDEX, roomString);
        //Go to next line for West,Player, and East
        roomString.append("\n\n");
        checkMovement(WEST_DOOR_INDEX, roomString);
        //Center the placement of player
        roomString.append("\t\tPlayer\t\t");
        checkMovement(EAST_DOOR_INDEX, roomString);
        //Go to next line
        roomString.append("\n\n\t\t\t");
        checkMovement(SOUTH_DOOR_INDEX, roomString);
        return roomString.toString();
    }

    /**
     * Helper method for toString to show available directions.
     * @param theDoorIndex index of the doors for direction
     * @param theBuilder string builder being appended to
     */
    private void checkMovement(final int theDoorIndex,
                               final StringBuilder theBuilder) {
        if (myRoomDoors[theDoorIndex] == null) {
            theBuilder.append("BLOCKED");
        } else if (myRoomDoors[theDoorIndex].isDead()) {
            theBuilder.append("DEAD DOOR");
        } else {
            String direction = switch (theDoorIndex) {
                case NORTH_DOOR_INDEX -> Direction.NORTH.toString();
                case SOUTH_DOOR_INDEX -> Direction.SOUTH.toString();
                case EAST_DOOR_INDEX -> Direction.EAST.toString();
                case WEST_DOOR_INDEX -> Direction.WEST.toString();
                default -> "";
            };
            theBuilder.append("MOVE ");
            theBuilder.append(direction);
        }
    }

    /**
     * Cheat to re-activate dead doors.
     */
    public void undeadRoom() {
        if (myBlockedDoors.getNorth()) {
            getDoor(NORTH_DOOR_INDEX).undead();
        }
        if (myBlockedDoors.getSouth()) {
            getDoor(SOUTH_DOOR_INDEX).undead();
        }
        if (myBlockedDoors.getEast()) {
            getDoor(EAST_DOOR_INDEX).undead();
        }
        if (myBlockedDoors.getWest()) {
            getDoor(WEST_DOOR_INDEX).undead();
        }
    }
}
