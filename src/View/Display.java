package View;

import Model.Maze;

/**
 * Display shows the user the output
 */
public class Display {
    private final Maze myMaze;

    /**
     * Creates a new Display
     *
     * @param theMaze The Maze that the User traverses
     */
    public Display(final Maze theMaze) {
        myMaze = theMaze;
    }

    /**
     * Displays the Maze to the User
     */
    public void printMaze() {
        System.out.println(myMaze.toString());
    }

    /**
     * Displays the Room to the User
     */
    public void printRoom() {
        System.out.println(myMaze.getCurrentRoom().toString());
    }

    /**
     * Displays the Instructions to the User
     */
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

    /**
     * Displays that the Player Won
     */
    public static void playerWon() {
        System.out.println("Goal Reached: Congratulations you won!");
    }

    /**
     * Displays that the Player Lost
     */
    public static void playerLost() {
        System.out.println("No Possible Pathways Left: Game Over");
    }

    /**
     * Displays the File Menu options to the User
     */
    public static void fileMenu() {
        System.out.println("""
                File:
                - Save Game
                - Load Game
                - Exit""");
    }

    /**
     * Displays the Start Message to the User
     */
    public static void startMsg() {
        System.out.println("- New Game");
        System.out.println("- Load Game");
        printPrompt();
    }

    /**
     * Displays a warning to the User for incorrect input of New or Load Game
     */
    public static void beginGameWarning() {
        System.out.println("Please Type \"New Game\" or \"Load Game\"");
        printPrompt();
    }

    /**
     * Displays a warning to the User for incorrect input of an action
     */
    public static void userActionWarning() {
        System.out.println("Please Type a Direction or \"File\" or \"Help\"");
    }

    /**
     * Displays a warning to the User for incorrect input of a Direction
     */
    public static void directionWarning() {
        System.out.println("Please Type a Valid Direction");
    }

    /**
     * Displays a warning to the User for incorrect Help Menu input
     */
    public static void helpMenuWarning() {
        System.out.println("Please Type \"about\" or \"instructions\" or \"cheats\" ");
    }

    /**
     * Displays a warning to the User for incorrect File Menu input
     */
    public static void fileMenuWarning() {
        System.out.println("Please Type \"Save Game\" or \"Load Game\" or \"Exit\" ");
    }

    /**
     * Displays a warning to the User for incorrect answer input
     */
    public static void incorrectAnswer(String correctAnswer) {
        System.out.println("Answer is Incorrect, Correct Answer is: " + correctAnswer);
    }

    /**
     * Displays the Help Menu Options to the User
     */
    public static void helpMenu() {
        System.out.println("""
                Help:
                - About
                - Game Play Instructions
                - Cheats""");
    }

    /**
     * Displays the question to the User
     */
    public static void displayQuestion(String theQuestion) {
        System.out.println(theQuestion);
    }

    /**
     * Displays a prompt to the User to enter input
     */
    public static void printPrompt() {
        System.out.print("Choose: ");
    }

}
