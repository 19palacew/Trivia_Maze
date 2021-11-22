package View;

import Model.Maze;

public class Display {
    private final Maze myMaze;
    public Display(final Maze theMaze){
        myMaze = theMaze;
    }
    /**
     * Displays the Maze to The User
     */
    public void printMaze() {
        System.out.println(myMaze.toString());
    }

    public void printRoom() {
        System.out.println(myMaze.getCurrentRoom().toString());
    }

    public static void printInstructions() {
        System.out.println("""
                How to Play:
                You are in a maze of rooms and you need to escape by finding the door out.
                To escape a room you need to answer a question correctly to unlock the door.
                You can move in a direction by typing North, South, East, or West
                Questions are multiple choice; answer with A, B, C, or D
                Type "File" to Save Game, Load Game, Exit or type "Help" to access About,
                Game Play Instructions, Cheats at anytime""");
    }

    public static void playerWon() {
        System.out.println("Goal Reached: Congratulations you won!");
    }

    public static void playerLost() {
        System.out.println("No Possible Pathways Left: Game Over");
    }

    public static void fileMenu() {
        System.out.println("""
                File:
                - Save Game
                - Load Game
                - Exit""");
    }

    public static void startMsg(){
        System.out.println("- New Game");
        System.out.println("- Load Game");
    }

    public static void beginGameWarning(){
        System.out.println("Please Type \"New Game\" or \"Load Game\"");
    }

    public static void helpMenu() {
        System.out.println("""
                Help:
                - About
                - Game Play Instructions
                - Cheats""");
    }
}
