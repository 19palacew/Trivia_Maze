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
     * Int for first save file when saving or loading.
     */
    private static final int FILE_NUMBER_ONE = 1;

    /**
     * Int for second save file when saving or loading.
     */
    private static final int FILE_NUMBER_TWO = 2;

    /**
     * Int for third save file when saving or loading.
     */
    private static final int FILE_NUMBER_THREE = 3;

    /**
     * Field for turn off questions cheat status.
     */
    private static boolean turnOffQuestions = false;

    /**
     * Starts Trivia Maze.
     * @param args Arguments
     */
    public static void main(final String[] args) {
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
            if(canPlay){
                runGame();
            }
            if(!canPlay) {
                Display.startMessageSequence();
                bootGame();
                runGame();
            }
        }
        INPUT.close();
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
            if (userGameStartInput.equalsIgnoreCase("new game")) {
                //Initialize the maze and display
                gameStarted = true;
                gameSetup();
            } else if (userGameStartInput.equalsIgnoreCase("load game")) {
                //Initialize the maze and display
                gameSetup();
                //Load from beginning
                if (loadGame()) {
                    gameStarted = true;
                }
            } else if (userGameStartInput.equalsIgnoreCase("instructions")) {
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
        int loadFileNumber = INPUT.nextInt();
        boolean loadedGame = false;
        try {
            File loadFile = switch (loadFileNumber) {
                case FILE_NUMBER_ONE -> SAVE_ONE;
                case FILE_NUMBER_TWO -> SAVE_TWO;
                case FILE_NUMBER_THREE -> SAVE_THREE;
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
                mainMaze.undeadAllRooms();
            } else if (userAction.toLowerCase().matches("really lazy")) {
                inputGood = true;
                turnOffQuestions = !turnOffQuestions;
                Display.cheatActive();
            } else if (userAction.toLowerCase().matches("really really lazy")) {
                inputGood = true;
                Display.cheatActive();
                mainMaze.moveToEnd();
            } else if (!userAction.toLowerCase().matches("")) {
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
            File saveFile = null;
            switch (userSaveOption) {
                case FILE_NUMBER_ONE -> saveFile = SAVE_ONE;
                case FILE_NUMBER_TWO -> saveFile = SAVE_TWO;
                case FILE_NUMBER_THREE -> saveFile = SAVE_THREE;
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
                Display.printAbout();
                Display.promptForKey();
                INPUT.nextLine();
            } else if (helpAction.toLowerCase().matches("instructions")) {
                inputGood = true;
                Display.printInstructions();
                Display.promptForKey();
                INPUT.nextLine();
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
