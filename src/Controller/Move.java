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
     * Field that holds the Maze's maximum X size
     */
    private final int myMaxX;
    /**
     * Field that holds the Maze's maximum Y size
     */
    private final int myMaxY;
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
        myMaxX = theMaze.getMaxX();
        myMaxY = theMaze.getMaxY();
        myDirection = theDirection;
        goDirection();
        if (checkValidMove()) {
            theMaze.setXAndY(myNewX, myNewY);
        }
    }

    /**
     * Finds the new X or Y coordinate of the Maze based on the direction entered.
     */
    public void goDirection() {
        myNewX = myX;
        myNewY = myY;
        switch (myDirection.toLowerCase()) {
            case "north" -> myNewY = myY - 1;
            case "south" -> myNewY = myY + 1;
            case "east" -> myNewX = myX + 1;
            case "west" -> myNewX = myX - 1;
        }
    }

    /**
     * checks if the new coordinates are within the boundaries of the maze
     */
    public boolean checkValidMove() {
        return myNewX >= 0 && myNewX < myMaxX && myNewY >= 0 && myNewY < myMaxY;
    }
}
