package View;

import Model.Maze;

public class Display {
    /**
     * Displays the Maze to The User
     */
    public static void printMaze(Maze theMaze) {
        System.out.println(theMaze.displayMaze());
    }

    public static void printInstructions() {
        System.out.println("""
                How to Play:
                You are in a maze of rooms and you need to escape by finding the door out.
                To escape a room you need to answer a question correctly to unlock the door.
                You can move in a direction by typing North, South, East, or West
                Questions are multiple choice; answer with A, B, C, or D""");
    }
}
