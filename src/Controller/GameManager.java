package Controller;

import Model.Database;
import Model.Direction;
import Model.Door;
import Model.Maze;
import View.Display;

import java.io.*;
import java.util.Scanner;

/**
 * Trivia Maze Start.
 */
public class GameManager {
    /**
     * Scanner for user input.
     */
    private static final Scanner INPUT = new Scanner(System.in);

    /**
     * The Display.
     */
    private static Display display;

    /**
     * The Maze.
     */
    private static Maze mainMaze;

    /**
     * File for first save game.
     */
    private static final File SAVE_ONE = new File("Save1.txt");

    /**
     * File for second save game.
     */
    private static final File SAVE_TWO = new File("Save2.txt");

    /**
     * File for third save game.
     */
    private static final File SAVE_THREE = new File("Save3.txt");

    /**
     * Field for turn off questions cheat status.
     */
    private static boolean turnOffQuestions = false;

    /**
     * Starts Trivia Maze.
     *
     * @param args Arguments
     */
    public static void main(final String[] args) {
        Database.connectToDatabase();
        Display.title();
        Display.printInstructions();
        Display.startMsg();
        bootGame();
        runGame();
        boolean canPlay = true;
        while (canPlay) {
            if (!mainMaze.isPossible()) {
                Display.playerLost();
                canPlay = false;
            }
            if (mainMaze.goalReached()) {
                Display.playerWon();
                canPlay = false;
            }
            runGame();
        }
        INPUT.close();
    }

    /**
     * Either Loads or Starts a New Game.
     */
    private static void bootGame() {
        // Load Game / Start New Game
        boolean gameStarted = false;
        String userGameStartInput = "";
        while (!gameStarted) {
            userGameStartInput = INPUT.nextLine();
            if (userGameStartInput.toLowerCase().matches(
                    "load game|new game")) {
                gameStarted = true;
            } else {
                Display.beginGameWarning();
            }
        }
        //Initialize the maze and display
        gameSetup();
        //Load from beginning
        if (userGameStartInput.equalsIgnoreCase("load game")) {
            loadGame();
        }
    }

    /**
     * Main Game Loop, updates screen and requests next action.
     * Will stop if game is won/lost.
     */
    private static void runGame() {
        updateScreen();
        nextAction();
    }

    /**
     * Sets up Maze and Display.
     */
    private static void gameSetup() {
        mainMaze = new Maze();
        display = new Display(mainMaze);
    }

    /**
     * Loads a saved Game.
     */
     private static void loadGame() {
        Display.loadPrompt();
        int loadFileNumber = INPUT.nextInt();
        try {
            File loadFile = switch (loadFileNumber) {
                case 1 -> SAVE_ONE;
                case 2 -> SAVE_TWO;
                case 3 -> SAVE_THREE;
                default -> throw new IllegalStateException("Unexpected value: "
                        + loadFileNumber);
            };
            FileInputStream file = new FileInputStream(loadFile);
            if (loadFile.length() != 0) {
                ObjectInputStream in = new ObjectInputStream(file);

                // Method for deserialization of object
                mainMaze = (Maze) in.readObject();
                display.setMyMaze(mainMaze);

                in.close();
                file.close();
                Display.loadCompletePrompt();
            } else {
                Display.loadErrorPrompt();
            }
        } catch (IOException ex) {
            System.out.println("Save file not found");
        } catch (ClassNotFoundException ex) {
            System.out.println("Maze class casting issue");
        }
    }

    /**
     * Gets the next action from the user, either a direction or a menu request.
     */
    private static void nextAction() {
        boolean inputGood = false;
        String userAction = "";
        while (!inputGood) {
            Display.printPrompt();
            userAction = INPUT.nextLine();
            if (userAction.toLowerCase().matches("north|south|east|west")) {
                inputGood = true;
            } else if (userAction.toLowerCase().matches("help")) {
                inputGood = true;
                help();
            } else if (userAction.toLowerCase().matches("file")) {
                inputGood = true;
                file();
            } else if (userAction.toLowerCase().matches("open sesame")) {
                inputGood = true;
                Display.cheatActive();
                mainMaze.getCurrentRoom().undeadCheat();
            } else if (userAction.toLowerCase().matches("really lazy")) {
                inputGood = true;
                turnOffQuestions = !turnOffQuestions;
                Display.cheatActive();
            } else if (userAction.toLowerCase().matches("really really lazy")) {
                inputGood = true;
                Display.cheatActive();
                mainMaze.teleportCheat();
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
     * Moves the Player based on desired direction.
     *
     * @param theDirection Compass Direction to Move
     */
    private static void movePlayer(final Direction theDirection) {
        Door localDoor = mainMaze.getCurrentRoom().getDoor(theDirection);
        if (mainMaze.canMovePlayer(theDirection)) {
            if (localDoor.isLocked() && !turnOffQuestions) {
                Display.displayQuestion(localDoor.getQuestion());
                String userAnswer = INPUT.nextLine().toLowerCase();
                if (userAnswer.matches("lazy")) {
                    Display.cheatActive();
                    System.out.println(localDoor.getAnswer());
                } else {
                    localDoor.unlock(userAnswer);
                }
                if (localDoor.isDead()) {
                    Display.incorrectAnswer(localDoor.getAnswer());
                } else {
                    Display.correctAnswer();
                    mainMaze.movePlayer(theDirection);
                }
            } else {
                mainMaze.movePlayer(theDirection);
            }
        }
    }

    /**
     * Calls the display to updates the maze and room view.
     */
    private static void updateScreen() {
        display.printMaze();
        display.printRoom();
    }

    /**
     * Opens the File Menu.
     */
    private static void file() {
        Display.fileMenu();
        boolean inputGood = false;
        String helpAction;
        while (!inputGood) {
            Display.printPrompt();
            helpAction = INPUT.nextLine();
            if (helpAction.toLowerCase().matches("save game")) {
                inputGood = true;
                saveGame();
            } else if (helpAction.toLowerCase().matches("load game")) {
                inputGood = true;
                loadGame();
            } else if (helpAction.toLowerCase().matches("exit")) {
                inputGood = true;
                INPUT.close();
                System.exit(0);
            } else {
                Display.fileMenuWarning();
            }
        }
    }

    /**
     * Saves a game.
     */
     private static void saveGame() {
        Display.saveOptions();
        int userSaveOption = INPUT.nextInt();
        try {
            File saveFile = SAVE_ONE;
            switch (userSaveOption) {
                case 1 -> saveFile = SAVE_ONE;
                case 2 -> saveFile = SAVE_TWO;
                case 3 -> saveFile = SAVE_THREE;
                default -> Display.generalWarning();
            }
            FileOutputStream file = new FileOutputStream(saveFile);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(mainMaze);

            out.close();
            file.close();

        } catch (IOException ex) {
            System.out.println("Save Files Unreachable");
        }
    }

    /**
     * Opens the Help Menu.
     */
    private static void help() {
        Display.helpMenu();
        boolean inputGood = false;
        String helpAction;
        while (!inputGood) {
            Display.printPrompt();
            helpAction = INPUT.nextLine();
            if (helpAction.toLowerCase().matches("about")) {
                inputGood = true;
                //TODO menu about
            } else if (helpAction.toLowerCase().matches(
                    "game play instructions")) {
                inputGood = true;
                Display.printInstructions();
            } else if (helpAction.toLowerCase().matches("cheats")) {
                inputGood = true;
                Display.cheatsMenu();
                Display.promptForKey();
                INPUT.nextLine();
            } else {
                Display.helpMenuWarning();
            }
        }
    }
}
