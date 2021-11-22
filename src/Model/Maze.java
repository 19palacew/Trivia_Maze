package Model;

public class Maze {
    /**
     * Field containing the 2D array of Room Objects that represents the overall maze
     */
    private final Room[][] myMaze;
    /**
     * Field that holds the players X position
     */
    private int myX;
    /**
     * Field that holds the players Y position
     */
    private int myY;
    /**
     * Class constant containing the max X-width of the maze
     */
    private static final int MAX_X = 8;
    /**
     * Class constant containing the max Y-width of the maze
     */
    private static final int MAX_Y = 8;

    /**
     * Constructor that builds the 2D-array representation of the maze.
     * Additionally, initial coordinates of the player are set. Finally,
     * the roomSetup helper method is called which will set up the rooms with
     * doors.
     */
    public Maze() {
        myX = 0;
        myY = 0;
        myMaze = new Room[MAX_X][MAX_Y];
        roomSetup();
    }

    /**
     * Helper method that assigns doors to the rooms depending on the rooms location
     */
    private void roomSetup() {

        for (int xMazeCoord = 0; xMazeCoord < myMaze.length; xMazeCoord++){
            for (int yMazeCoord = 0; yMazeCoord < myMaze[xMazeCoord].length; yMazeCoord++){
                //We put our directions in the innermost loop, so they reset for each room
                boolean west = false;
                boolean south = false;
                boolean east = false;
                boolean north = false;
                Door northDoor;
                Door southDoor = new Door();
                Door eastDoor = new Door();
                Door westDoor;
                //If column is greater than 0 we know there is a north facing door
                if (yMazeCoord > 0) {
                    north = true;
                    northDoor = myMaze[xMazeCoord][yMazeCoord-1].getDoor(1);
                }
                else {
                    northDoor = new Door();
                }
                /* If column value is 1 less than the inner maze's length - 1 there is a
                   south facing door */
                if (yMazeCoord < myMaze[xMazeCoord].length - 1) {
                    south = true;
                    // Sets the south door of this room to the north door of the previous room.
                }
                //If room is not in a row on the southern edge, there is a east door
                if (xMazeCoord <= myMaze.length - 1) {
                    east = true;
                }
                //If row value above is greater or equal to 0 we know exists west door
                if (xMazeCoord-1 >= 0) {
                    west = true;
                    // Sets the west door of this room to the east door of the previous room.
                    westDoor = myMaze[xMazeCoord-1][yMazeCoord].getDoor(2);
                }
                else {
                    westDoor = new Door();
                }
                myMaze[xMazeCoord][yMazeCoord] = new Room(new RoomBlocker(north,south,east,west), northDoor, southDoor, eastDoor, westDoor);
            }
        }
    }

    /**
     * Setter to change the players X and Y position
     * @param theX the next X position the player will be in
     * @param theY the next Y position the player will be in
     */
    public void setXAndY (int theX, int theY) {
        myX = theX;
        myY = theY;
    }

    /**
     * Getter for the player's X position
     * @return myX the current X position
     */
    public int getX () {
        return myX;
    }

    /**
     * Getter for the player's Y position
     * @return myY the current Y position
     */
    public int getY () {
        return myY;
    }

    /**
     * Getter for MAX_X
     * @return MAX_X maximum of x
     */
    public int getMaxX () {
        return MAX_X;
    }

    /**
     * Getter for MAX_Y
     * @return MAX_Y maximum of y
     */
    public int getMaxY () {
        return MAX_Y;
    }

    /**
     * Getter that returns the current room the player is in
     * @return the room the player is currently located in
     */
    public Room getCurrentRoom () {
        return myMaze[myX][myY];
    }

    /**
     * toString displays the overview of the maze, including the player, the rooms,
     * the finish, and the start when the player is not in the start.
     * @return string representing maze
     */
    @Override
    public String toString() {
        StringBuilder mazeString = new StringBuilder();
        for (int i = 0; i < myMaze.length; i++) {
            mazeString.append("\n");
            // Loop through all elements of current row
            for (int j = 0; j < myMaze[i].length; j++) {
                if (i == myY && j == myX) {
                    mazeString.append("[PLYR]");
                } else if(i == MAX_X-1 && j == MAX_Y-1) {
                    mazeString.append("[FNSH]");
                } else if(i == 0 && j == 0) {
                    mazeString.append("[STRT]");
                } else {
                    mazeString.append("[ROOM]");

                }
            }
        }
        return mazeString.toString();
    }
}