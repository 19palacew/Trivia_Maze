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
    private static final Scanner input = new Scanner(System.in);
    private static Display display;
    private static Maze mainMaze;
    private static final File saveOne = new File("Save1.txt");
    private static final File saveTwo = new File("Save2.txt");
    private static final File saveThree = new File("Save3.txt");
    private static boolean turnOffQuestions = false;

    /**
     * Starts Trivia Maze
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
        //Initialize the maze and display
        gameSetup();
        //Load from beginning
        if (userGameStartInput.equalsIgnoreCase("load game")) {
            loadGame();
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
     * Sets up Maze and Display
     */
    private static void gameSetup() {
        mainMaze = new Maze();
        display = new Display(mainMaze);
    }

    /**
     * Loads a Old Game
     */
    private static void loadGame() {
        Display.loadPrompt();
        int loadFileNumber = input.nextInt();
        try {
            if (loadFileNumber == 1 && saveOne.length() != 0) {
                FileInputStream file = new FileInputStream
                        (saveOne);
                ObjectInputStream in = new ObjectInputStream
                        (file);

                // Method for deserialization of object
                mainMaze = (Maze) in.readObject();
                display.setMyMaze(mainMaze);

                in.close();
                file.close();
                Display.loadCompletePrompt();
            } else if (loadFileNumber == 2 && saveTwo.length() != 0) {
                System.out.println("Made it in");
                FileInputStream file = new FileInputStream
                        (saveTwo);
                ObjectInputStream in = new ObjectInputStream
                        (file);

                // Method for deserialization of object
                mainMaze = (Maze) in.readObject();
                display.setMyMaze(mainMaze);

                in.close();
                file.close();
                Display.loadCompletePrompt();
            } else if (loadFileNumber == 3 && saveThree.length() != 0) {
                FileInputStream file = new FileInputStream
                        (saveThree);
                ObjectInputStream in = new ObjectInputStream
                        (file);

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
            } else if (userAction.toLowerCase().matches("really lazy")) {
                turnOffQuestions = !turnOffQuestions;
                Display.cheatActive();
            } else if (userAction.toLowerCase().matches("really really lazy")) {
                inputGood = true;
                Display.cheatActive();
                mainMaze.setRoom(mainMaze.getMazeSize() - 1, mainMaze.getMazeSize() - 1);
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
    private static void movePlayer(final Direction theDirection) {
        Door localDoor = mainMaze.getCurrentRoom().getDoor(theDirection);
        if (mainMaze.canMovePlayer(theDirection)) {
            if (localDoor.isLocked() && !turnOffQuestions) {
                Display.displayQuestion(localDoor.getQuestion());
                String userAnswer = input.nextLine().toLowerCase();
                if (userAnswer.matches("lazy")) {
                    System.out.println(localDoor.getAnswer());
                } else {
                    localDoor.unlock(userAnswer);
                    if (localDoor.isDead()) {
                        Display.incorrectAnswer(localDoor.getAnswer());
                    } else {
                        mainMaze.movePlayer(theDirection);
                    }
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
                saveGame();
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

    private static void saveGame() {
        Display.saveOptions();
        int userSaveOption = input.nextInt();
        try {
            if (userSaveOption == 1) {
                // Saving of object in a file
                FileOutputStream file = new FileOutputStream
                        (saveOne);
                ObjectOutputStream out = new ObjectOutputStream
                        (file);

                // Method for serialization of object
                out.writeObject(mainMaze);

                out.close();
                file.close();

            } else if (userSaveOption == 2) {
                // Saving of object in a file
                FileOutputStream file = new FileOutputStream
                        (saveTwo);
                ObjectOutputStream out = new ObjectOutputStream
                        (file);

                // Method for serialization of object
                out.writeObject(mainMaze);

                out.close();
                file.close();

            } else if (userSaveOption == 3) {
                // Saving of object in a file
                FileOutputStream file = new FileOutputStream
                        (saveThree);
                ObjectOutputStream out = new ObjectOutputStream
                        (file);

                // Method for serialization of object
                out.writeObject(mainMaze);

                out.close();
                file.close();

            } else {
                Display.generalWarning();
            }

        } catch (IOException ex) {
            System.out.println("Save Files Unreachable");
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
            } else if (helpAction.toLowerCase().matches("game play instructions|instructions")) {
                inputGood = true;
                Display.printInstructions();
            } else if (helpAction.toLowerCase().matches("cheats")) {
                inputGood = true;
                Display.cheatsMenu();
                Display.promptForKey();
                input.nextLine();
            } else {
                Display.helpMenuWarning();
            }
        }
    }
}
