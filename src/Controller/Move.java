package Controller;

import Model.Maze;

public class Move {

    /**
     * Field that holds the player's X position
     */
    private final int myX;
    /**
     * Field that holds the player's Y position
     */
    private final int myY;
    /**
     * Field that holds the player's entered direction
     */
    private final String myDirection;
    /**
     * Field that holds the player's X position after moving
     */
    private int myNewX;
    /**
     * Field that holds the player's Y position after moving
     */
    private int myNewY;

    /**
     * Constructor that takes current location in Maze and finds new location after moving.
     * @param theMaze the maze the game takes place in
     * @param theDirection input from user of direction they want to go
     */
    public Move(final Maze theMaze, final String theDirection){
        myX = theMaze.getX();
        myY = theMaze.getY();
        myDirection = theDirection;
        goDirection();
        theMaze.setXAndY(myNewX, myNewY);
    }

    /**
     * Finds the new X or Y coordinate of the Maze based on the direction entered.
     */
    public void goDirection() {
        if ("n".equals(myDirection)) {
            myNewY = myY + 1;
        } else if ("s".equals(myDirection)) {
            myNewY = myY - 1;
        } else if ("e".equals(myDirection)) {
            myNewX = myX + 1;
        } else if ("w".equals(myDirection)) {
            myNewX = myX - 1;
        }
    }
}
