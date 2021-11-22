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
                Multiple choice questions; answer with A, B, C, or D
                True or False questions; answer with T or F
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

    public static void startMsg() {
        System.out.println("- New Game");
        System.out.println("- Load Game");
        printPrompt();
    }

    public static void beginGameWarning(){
        System.out.println("Please Type \"New Game\" or \"Load Game\"");
        printPrompt();
    }

    public static void userActionWarning(){
        System.out.println("Please Type a Direction or \"File\" or \"Help\"");
    }

    public static void directionWarning(){
        System.out.println("Please Type a Valid Direction");
    }

    public static void helpMenuWarning(){
        System.out.println("Please Type \"about\" or \"instructions\" or \"cheats\" ");
    }

    public static void fileMenuWarning(){
        System.out.println("Please Type \"Save Game\" or \"Load Game\" or \"Exit\" ");
    }

    public static void helpMenu() {
        System.out.println("""
                Help:
                - About
                - Game Play Instructions
                - Cheats""");
    }

    public static void printPrompt() {
        System.out.print("Choose: ");
    }

}
