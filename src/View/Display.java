package View;

import Model.Maze;
import Model.SoundPlay;
import java.io.File;

/**
 * Display shows the user the output.
 */
public class Display {
    /**
     *The maze being displayed by the display
     */
    private Maze myMaze;
    /**
     * A file representing the sound for when a user gets a sound correct
     */
    private final static File CORRECT_SOUND = new File("Correct.wav");
    /**
     * A file representing the sound for when a user gets a sound incorrect
     */
    private final static File INCORRECT_SOUND = new File("Incorrect.wav");
    /**
     * A file representing the sound for when a user wins
     */
    private final static File WON_GAME_SOUND = new File("WonGame.wav");
    /**
     * A file representing the sound for when a user loses
     */
    private final static File LOST_GAME_SOUND = new File("LostGame.wav");
    /**
     * A file representing the sound for when game starts and user is in
     */
    private final static File IN_GAME_SOUND = new File("Instruction.wav");
    /**
     * A file representing the sound for when a user opens a menu
     */
    private final static File POP_GAME_SOUND = new File("Pops.wav");
    /**
     * A file representing the sound for when a user opens a menu
     */
    private final static File ERROR_GAME_SOUND = new File("Errors.wav");
    /**
     * Creates a new Display.
     *
     * @param theMaze The Maze that the User traverses
     */
    public Display(final Maze theMaze) {
        myMaze = theMaze;
    }

    /**
     * Setter to change the maze the display is showing
     * @param theMaze the maze to display
     */
    public void setMyMaze(Maze theMaze) {
        myMaze = theMaze;
    }
    /**
     * Displays the Maze to the User.
     */
    public void printMaze() {
        System.out.println(myMaze.toString());
    }

    /**
     * Displays the Room to the User.
     */
    public void printRoom() {
        System.out.println(myMaze.getCurrentRoom().toString());
    }

    public static void startMessageSequence() {
        SoundPlay.PlaySound(IN_GAME_SOUND);
        title();
        startMsg();
    }

    /**
     * Displays the Instructions to the User.
     */
    public static void printInstructions() {
        SoundPlay.PlaySound(POP_GAME_SOUND);
        System.out.println();
        System.out.println("""
                How to Play:
                
                On maze screen; enter direction of door to go that way
                For Multiple choice questions; answer with "A", "B", "C", or "D"
                For True or False questions; answer with "True" or "False"
                
                During the game:
                Type "File" to Save Game, Load Game, Exit
                Type "Help" to access About, Game Play Instructions, or Cheats 
                at any time""");
        printPrompt();
    }

    /**
     * Displays info about the game.
     */
    public static void printAbout() {
        SoundPlay.PlaySound(POP_GAME_SOUND);
        System.out.println("""
                You are in a maze of rooms and you need to escape by finding the door
                out. To escape a room you need to answer a question correctly to unlock 
                the door. You can move in a direction by typing North, South, 
                East, or West.
                """);
    }

    /**
     * Displays that the Player Won.
     */
    public static void playerWon() {
        SoundPlay.PlaySound(WON_GAME_SOUND);
        System.out.println("Goal Reached: Congratulations you won!");
    }

    /**
     * Displays that the Player Lost.
     */
    public static void playerLost() {
        SoundPlay.PlaySound(LOST_GAME_SOUND);
        System.out.println("No Possible Pathways Left: Game Over");
    }

    /**
     * Displays the File Menu options to the User.
     */
    public static void fileMenu() {
        SoundPlay.PlaySound(POP_GAME_SOUND);
        System.out.println("""
                File:
                - Save Game
                - Load Game
                - Exit""");
    }

    /**
     * Displays the Start Message to the User.
     */
    public static void startMsg() {
        System.out.println("\n- New Game");
        System.out.println("- Load Game");
        System.out.println("- Instructions");
        printPrompt();
    }

    /**
     * Displays a warning to the User for incorrect input of New or Load Game.
     */
    public static void beginGameWarning() {
        SoundPlay.PlaySound(ERROR_GAME_SOUND);
        System.out.println("Please Type \"New Game\" or \"Load Game\"");
        printPrompt();
    }

    /**
     * Displays a warning to the User for incorrect input of an action.
     */
    public static void userActionWarning() {
        SoundPlay.PlaySound(ERROR_GAME_SOUND);
        System.out.println(
                "Please Type a Direction or \"File\" or \"Help\"");
    }

    /**
     * Displays a warning to the User for incorrect Help Menu input.
     */
    public static void helpMenuWarning() {
        SoundPlay.PlaySound(ERROR_GAME_SOUND);
        System.out.println("Please Type \"about\" or \"instructions\" or \"cheats\" ");
    }

    /**
     * Displays a warning to the User for incorrect File Menu input.
     */
    public static void fileMenuWarning() {
        SoundPlay.PlaySound(ERROR_GAME_SOUND);
        System.out.println("Please Type \"Save Game\" or \"Load Game\" or \"Exit\" ");
    }

    /**
     * Displays a message to the User for correct answer input.
     */
    public static void correctAnswer() {
        SoundPlay.PlaySound(CORRECT_SOUND);
        System.out.println("Correct!");
    }

    /**
     * Displays a warning to the User for incorrect answer input.
     * @param correctAnswer the correct answer to be shown
     */
    public static void incorrectAnswer(final String correctAnswer) {
        SoundPlay.PlaySound(INCORRECT_SOUND);
        System.out.println("Answer is Incorrect, Correct Answer is: " + correctAnswer);
    }

    /**
     * Displays the Help Menu Options to the User.
     */
    public static void helpMenu() {
        SoundPlay.PlaySound(POP_GAME_SOUND);
        System.out.println("""
                Help:
                - About
                - Instructions
                - Cheats""");
    }

    /**
     * Displays options for saving game.
     */
    public static void saveOptions() {
        SoundPlay.PlaySound(POP_GAME_SOUND);
        System.out.println("Save to which save file? Options: 1, 2, 3");
    }

    /**
     * Displays the question to the User.
     * @param theQuestion The question being displayed
     */
    public static void displayQuestion(final String theQuestion) {
        SoundPlay.PlaySound(POP_GAME_SOUND);
        System.out.println(theQuestion);
    }

    /**
     * Displays a prompt to the User to enter input.
     */
    public static void printPrompt() {
        System.out.print("Choose: ");
    }
    /**
     * Displays a general warning.
     */
    public static void generalWarning() {
        SoundPlay.PlaySound(ERROR_GAME_SOUND);
        System.out.println("Command not recognized, continue playing or retry " +
                "command with valid "
                + "syntax");
    }

    /**
     * Displays a prompt to select save to load.
     */
    public static void loadPrompt() {
        SoundPlay.PlaySound(POP_GAME_SOUND);
        System.out.println("Load which game? Options: 1, 2, 3");
    }

    /**
     * Displays an error when loading an empty file.
     */
    public static void loadErrorPrompt() {
        SoundPlay.PlaySound(ERROR_GAME_SOUND);
        System.out.println("Sorry that save file is empty");
    }

    /**
     * Displays message for successfully loaded game.
     */
    public static void loadCompletePrompt() {
        SoundPlay.PlaySound(POP_GAME_SOUND);
        System.out.println("Loaded from save file");
    }

    /**
     * Displays the Cheats to the User.
     */
    public static void cheatsMenu() {
        SoundPlay.PlaySound(POP_GAME_SOUND);
        System.out.println("""
                Cheats:
                - Unblock Dead Doors (open sesame)
                - See Answer (lazy)
                - Turn Off Questions (really lazy)
                - Teleport to End (really really lazy)""");
    }

    /**
     * Displays a continue prompt to the User to enter input.
     */
    public static void promptForKey() {
        System.out.print("Press any key to return to game... ");
    }

    /**
     * Displays Triva Maze title.
     */
    public static void title() {
        System.out.println("""
                 _______     _         _          __  __                 \s
                |__   __|   (_)       (_)        |  \\/  |                \s
                   | | _ __  _ __   __ _   __ _  | \\  / |  __ _  ____ ___\s
                   | || '__|| |\\ \\ / /| | / _` | | |\\/| | / _` ||_  // _ \\
                   | || |   | | \\ V / | || (_| | | |  | || (_| | / /|  __/
                   |_||_|   |_|  \\_/  |_| \\__,_| |_|  |_| \\__,_|/___|\\___|""");
    }

    /**
     * Displays when cheat has been turned on.
     */
    public static void cheatActive() {
        SoundPlay.PlaySound(POP_GAME_SOUND);
        System.out.println("Cheat has been activated ");
    }

}
