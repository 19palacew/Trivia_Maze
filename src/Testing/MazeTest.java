package Testing;

import Model.Database;
import Model.Maze;
import View.Display;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    private static Display display;

    @Test
    void isPossibleAllDoorsOpen() {
        Database.connectToDatabase();
        Maze testMaze = new Maze();
        assertTrue(testMaze.isPossible());
    }

    @Test
    void isPossibleFirstDoorsClose() {
        Database.connectToDatabase();
        Maze testMaze = new Maze();
        testMaze.getCurrentRoom().getDoor(1).unlock("");
        testMaze.getCurrentRoom().getDoor(2).unlock("");
        assertFalse(testMaze.isPossible());
    }
}