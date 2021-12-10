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
     * String for selecting first save file when saving or loading.
     */
    private static final String FILE_NUMBER_ONE = "1";

    /**
     * String for selecting second save file when saving or loading.
     */
    private static final String FILE_NUMBER_TWO = "2";

    /**
     * String for selecting third save file when saving or loading.
     */
    private static final String FILE_NUMBER_THREE = "3";

    /**
     * Field for turn off questions cheat status.
     */
    private static boolean turnOffQuestions = false;

    /**
     * Starts Trivia Maze.
     * @param args Arguments
     */
    public static void main(final String[] args) {
        gameLoop();
    }

    /**
     * Loop to keep game running until lose or win.
     */
    public static void gameLoop() {
        Database.connectToDatabase();
        Display.startMessageSequence();
        bootGame();
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
            if (canPlay) {
                runGame();
            }
            if (!canPlay) {
                gameLoop();
            }
        }
    }
    /**
     * Either Loads or Starts a New Game.
     */
    private static void bootGame() {
        // Load Game / Start New Game
        boolean gameStarted = false;
        String userGameStartInput;
        while (!gameStarted) {
            userGameStartInput = INPUT.nextLine();
            if ("new game".equalsIgnoreCase(userGameStartInput)) {
                //Initialize the maze and display
                gameStarted = true;
                gameSetup();
            } else if ("load game".equalsIgnoreCase(userGameStartInput)) {
                //Initialize the maze and display
                gameSetup();
                //Load from beginning
                if (loadGame()) {
                    gameStarted = true;
                } else {
                    Display.startMsg();
                }
            } else if ("instructions".equalsIgnoreCase(userGameStartInput)) {
                Display.printInstructions();
            } else {
                Display.beginGameWarning();
            }
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
     * @return Boolean for if game is loaded
     */
     private static Boolean loadGame() {
        Display.loadPrompt();
        String loadFileNumber = INPUT.nextLine();
        boolean loadedGame = false;
        try {
            File loadFile = switch (loadFileNumber) {
                case FILE_NUMBER_ONE -> SAVE_ONE;
                case FILE_NUMBER_TWO -> SAVE_TWO;
                case FILE_NUMBER_THREE -> SAVE_THREE;
                default -> throw new IOException("Save file not found");
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
                loadedGame = true;
            } else {
                Display.loadErrorPrompt();
            }
        } catch (IOException ex) {
            System.out.println("Save file not found");
        } catch (ClassNotFoundException ex) {
            System.out.println("Maze class casting issue");
        }
        return loadedGame;
    }

    /**
     * Gets the next action from the user, either a direction or a menu request.
     */
    private static void nextAction() {
        boolean inputGood = false;
        String userAction = "";
        Display.printPrompt();
        while (!inputGood) {
            userAction = INPUT.nextLine();
            if ("north|south|east|west".equalsIgnoreCase(userAction)) {
                inputGood = true;
            } else if ("help".equalsIgnoreCase(userAction)) {
                inputGood = true;
                help();
            } else if ("file".equalsIgnoreCase(userAction)) {
                inputGood = true;
                file();
            } else if ("open sesame".equalsIgnoreCase(userAction)) {
                inputGood = true;
                Display.cheatActive();
                mainMaze.undeadAllRooms();
            } else if ("really lazy".equalsIgnoreCase(userAction)) {
                inputGood = true;
                turnOffQuestions = !turnOffQuestions;
                Display.cheatActive();
            } else if ("really really lazy".equalsIgnoreCase(userAction)) {
                inputGood = true;
                Display.cheatActive();
                mainMaze.moveToEnd();
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
                    attemptUnlock(localDoor.getAnswer(), localDoor);
                } else {
                    attemptUnlock(userAnswer, localDoor);
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
     * Helper method to enter answers to Door questions.
     * @param theUserAnswer entered response
     * @param theLocalDoor the Door being opened
     */
    private static void attemptUnlock(final String theUserAnswer,
                                      final Door theLocalDoor) {
        theLocalDoor.unlock(theUserAnswer);
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
        String userSaveOption = INPUT.nextLine();
        try {
            File saveFile = switch (userSaveOption) {
                case FILE_NUMBER_ONE -> SAVE_ONE;
                case FILE_NUMBER_TWO -> SAVE_TWO;
                case FILE_NUMBER_THREE -> SAVE_THREE;
                default -> throw new IOException();
            };
            FileOutputStream file = new FileOutputStream(saveFile);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(mainMaze);

            out.close();
            file.close();
        } catch (IOException ex) {
            Display.saveUnreachable();
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
            if ("about".equalsIgnoreCase(helpAction)) {
                inputGood = true;
                Display.printAbout();
                Display.promptForKey();
                INPUT.nextLine();
            } else if ("instructions".equalsIgnoreCase(helpAction)) {
                inputGood = true;
                Display.printInstructions();
                Display.promptForKey();
                INPUT.nextLine();
            } else if ("cheats".equalsIgnoreCase(helpAction)) {
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
