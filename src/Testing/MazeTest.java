package Testing;

import Model.Database;
import Model.Direction;
import Model.Maze;
import View.Display;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MazeTest {

    private static Display display;

    /**
     * int field holding index of north Door.
     */
    private static final int NORTH_DOOR_INDEX = 0;
    /**
     * int field holding index of south Door.
     */
    private static final int SOUTH_DOOR_INDEX = 1;
    /**
     * int field holding index of east Door.
     */
    private static final int EAST_DOOR_INDEX = 2;
    /**
     * int field holding index of west Door.
     */
    private static final int WEST_DOOR_INDEX = 3;

    private static final int TEST_X = 1;
    private static final int TEST_Y = 1;

    @Test
    void testIsPossibleAllDoorsOpen() {
        Maze testMaze = new Maze();
        assertTrue(testMaze.isPossible(), "Maze isPossible failed");
    }

    @Test
    void testIsPossibleFirstDoorsClose() {
        Database.connectToDatabase();
        Maze testMaze = new Maze();
        testMaze.getCurrentRoom().getDoor(SOUTH_DOOR_INDEX).unlock("");
        testMaze.getCurrentRoom().getDoor(EAST_DOOR_INDEX).unlock("");
        assertFalse(testMaze.isPossible(), "Maze isPossible failed");
    }

    @Test
    void testSetX() {
        Database.connectToDatabase();
        Maze testMaze = new Maze();
        testMaze.setX(TEST_X);
        assertEquals(TEST_X, testMaze.getX(), "Maze setX failed");
    }

    @Test
    void testSetY() {
        Database.connectToDatabase();
        Maze testMaze = new Maze();
        testMaze.setY(TEST_Y);
        assertEquals(TEST_Y, testMaze.getY(), "Maze setY failed");
    }

    @Test
    void testMoveNorth() {
        Maze testMaze = new Maze();
        testMaze.setX(TEST_X);
        testMaze.setY(TEST_Y);
        testMaze.movePlayer(Direction.NORTH);
        assertEquals(TEST_Y - 1, testMaze.getY(),
                "Maze movePlayer NORTH failed");
    }

    @Test
    void testMoveSouth() {
        Maze testMaze = new Maze();
        testMaze.setX(TEST_X);
        testMaze.setY(TEST_Y);
        testMaze.movePlayer(Direction.SOUTH);
        assertEquals(TEST_Y + 1, testMaze.getY(),
                "Maze movePlayer SOUTH failed");
    }

    @Test
    void testMoveEast() {
        Maze testMaze = new Maze();
        testMaze.setX(TEST_X);
        testMaze.setY(TEST_Y);
        testMaze.movePlayer(Direction.EAST);
        assertEquals(TEST_X + 1, testMaze.getX(),
                "Maze movePlayer EAST failed");
    }

    @Test
    void testMoveWest() {
        Maze testMaze = new Maze();
        testMaze.setX(TEST_X);
        testMaze.setY(TEST_Y);
        testMaze.movePlayer(Direction.WEST);
        assertEquals(TEST_X - 1, testMaze.getX(),
                "Maze movePlayer WEST failed");
    }

    @Test
    void testGoalReached() {
        Maze testMaze = new Maze();
        testMaze.moveToEnd();
        assertTrue(testMaze.goalReached(), "Maze goalReach failed");
    }
}
