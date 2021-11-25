package Controller;

import Model.Database;
import Model.Direction;
import Model.Door;
import Model.Maze;
import View.Display;

import java.util.Scanner;

/**
 * Trivia Maze Start
 */
public class GameManager {
    private static final Scanner input = new Scanner(System.in);
    private static Display display;
    private static Maze mainMaze;

    /**
     * Starts Trivia Maze
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        Database.connectToDatabase();
        Display.printInstructions();
        Display.startMsg();
        bootGame();
        runGame();
        input.close();
    }

    /**
     * Either Loads or Starts a New Game
     */
    private static void bootGame() {
        // Load Game / Start New Game
        boolean gameStarted = false;
        String userGameStartInput = "";
        while (!gameStarted) {
            userGameStartInput = input.nextLine();
            if (userGameStartInput.toLowerCase().matches("load game|new game")) {
                gameStarted = true;
            } else {
                Display.beginGameWarning();
            }
        }
        if (userGameStartInput.equalsIgnoreCase("load game")) {
            loadGame();
        } else {
            newGame();
        }
    }

    /**
     * Main Game Loop, updates screen and requests next action. Will stop if game is won/lost
     */
    private static void runGame() {
        updateScreen();
        nextAction();
        if (!mainMaze.isPossible()) {
            Display.playerLost();
            return;
        }
        if (mainMaze.goalReached()) {
            Display.playerWon();
            return;
        }
        runGame();
    }

    /**
     * Sets up a New Game
     */
    private static void newGame() {
        mainMaze = new Maze();
        display = new Display(mainMaze);
    }

    /**
     * Loads a Old Game
     */
    private static void loadGame() {
        // Not Yet Implemented
    }

    /**
     * Gets the next action from the user, either a direction or a menu request
     */
    private static void nextAction() {
        boolean inputGood = false;
        String userAction = "";
        while (!inputGood) {
            Display.printPrompt();
            userAction = input.nextLine();
            if (userAction.toLowerCase().matches("north|south|east|west")) {
                inputGood = true;
            } else if (userAction.toLowerCase().matches("help")) {
                inputGood = true;
                Help();
            } else if (userAction.toLowerCase().matches("file")) {
                inputGood = true;
                File();
            } else {
                Display.userActionWarning();
            }
        }
        switch (userAction.toLowerCase()) {
            case "north" -> movePlayer(Direction.NORTH);
            case "south" -> movePlayer(Direction.SOUTH);
            case "east" -> movePlayer(Direction.EAST);
            case "west" -> movePlayer(Direction.WEST);
        }
    }

    /**
     * Moves the Player based on desired direction
     *
     * @param theDirection Compass Direction to Move
     */
    private static void movePlayer(Direction theDirection) {
        Door localDoor = mainMaze.getCurrentRoom().getDoor(theDirection);
        if (mainMaze.canMovePlayer(theDirection)) {
            if (localDoor.isLocked()) {
                Display.displayQuestion(localDoor.getQuestion());
                localDoor.unlock(input.nextLine());
                if (localDoor.isDead()) {
                    Display.incorrectAnswer(localDoor.getAnswer());
                } else {
                    mainMaze.movePlayer(theDirection);
                }
            } else {
                mainMaze.movePlayer(theDirection);
            }
        }
    }

    /**
     * Calls the display to updates the maze and room view
     */
    private static void updateScreen() {
        display.printMaze();
        display.printRoom();
    }

    /**
     * Opens the File Menu
     */
    private static void File() {
        Display.fileMenu();
        boolean inputGood = false;
        String helpAction;
        while (!inputGood) {
            Display.printPrompt();
            helpAction = input.nextLine();
            if (helpAction.toLowerCase().matches("save game")) {
                inputGood = true;
                //TODO save game
            } else if (helpAction.toLowerCase().matches("load game")) {
                inputGood = true;
                loadGame();
            } else if (helpAction.toLowerCase().matches("exit")) {
                inputGood = true;
                input.close();
                System.exit(0);
            } else {
                Display.fileMenuWarning();
            }
        }
    }

    /**
     * Opens the Help Menu
     */
    private static void Help() {
        Display.helpMenu();
        boolean inputGood = false;
        String helpAction;
        while (!inputGood) {
            Display.printPrompt();
            helpAction = input.nextLine();
            if (helpAction.toLowerCase().matches("about")) {
                inputGood = true;
                //TODO menu about
            } else if (helpAction.toLowerCase().matches("instructions")) {
                inputGood = true;
                Display.printInstructions();
            } else if (helpAction.toLowerCase().matches("cheats")) {
                inputGood = true;
                //TODO menu cheats
            } else {
                Display.helpMenuWarning();
            }
        }
    }
}