public class Maze {
    /**
     * Field containing the 2D array of Room Objects that represents the overall maze
     */
    private Room[][] myMaze;
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
        myX = 1;
        myY = 1;
        myMaze = new Room[MAX_X][MAX_Y];
        roomSetup();
    }

    /**
     * Helper method that assigns doors to the rooms depending on the rooms location
     */
    private void roomSetup() {

        for (int i = 0; i < myMaze.length; i++){
            for (int j = 0; j < myMaze[i].length; j++){
                //We put our directions in the innermost loop so they reset for each room
                boolean north = false;
                boolean east = false;
                boolean south = false;
                boolean west = false;
                //If row value above is greater or equal to 0 we know exists north door
                if (i-1 >= 0) {
                    north = true;
                }
                /* If column value is 1 less than the inner mazes length - 1 there is an
                   east facing door */
                if (j < myMaze[i].length - 1) {
                    east = true;
                }
                //If room is not in a row on the southern edge, there is a south door
                if (i <= myMaze.length - 1) {
                    south = true;
                }
                //If column is greater than 0 we know there is a west facing door
                if (j > 0) {
                    west = true;
                }
                myMaze[i][j] = new Room(north,south,east,west);
            }
        }
    }

    /**
     * Setter to change the players X and Y position
     * @param theX the next X position the player will be in
     * @param theY the next Y position the player will be in
     */
    public void setX (int theX, int theY) {
        myX = theX;
        myY = theY;
    }

    /**
     * Getter that returns the current room the player is in
     * @return the room the player is currently located in
     */
    public Room getCurrentRoom () {
        return myMaze[myX][myY];
    }
}
