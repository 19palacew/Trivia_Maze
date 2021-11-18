package Controller;

import Model.Database;
import Model.Maze;
import View.Display;

import java.util.Scanner;

public class GameManager {
    private static Display display;
    private static Maze mainMaze;

    public static void main(String[] args) {
        Database.connectToDatabase();
        Display.printInstructions();
        System.out.println("1) New Game");
        System.out.println("2) Load Game");
        Scanner input = new Scanner(System.in);
        // Load Game / Start New Game
        boolean gameStarted = false;
        String userGameStartInput = "";
        while (!gameStarted) {
            userGameStartInput = input.nextLine();
            if (userGameStartInput.equalsIgnoreCase("1")
                    || userGameStartInput.equalsIgnoreCase("2")
                    || userGameStartInput.equalsIgnoreCase("quit")) {
                gameStarted = true;
            } else {
                System.out.println("Please Type 1 or 2");
            }
        }
        input.close();
        if (userGameStartInput.equalsIgnoreCase("1")) {
            newGame();
        } else if (userGameStartInput.equalsIgnoreCase("2")) {
            loadGame();
        } else if (userGameStartInput.equalsIgnoreCase("File")) {
            File();
        } else if (userGameStartInput.equalsIgnoreCase("Help")) {
            Help();
        }
        else {
            //This should never be reached
            System.out.println("Wait what?");
        }
        boolean gameOver = false;
        while (!gameOver){
            updateScreen();
            nextAction();
            if (!mainMaze.isPossible()){
                Display.playerLost();
                gameOver = true;
            }
            if (mainMaze.goalReached()){
                Display.playerWon();
                gameOver = true;
            }
        }
    }

    private static void newGame() {
        mainMaze = new Maze();
        display = new Display(mainMaze);
    }

    private static void loadGame() {
        // Not Yet Implemented
    }

    private static void nextAction(){
        // Not Yet Implemented
    }

    private static void updateScreen(){
        display.printMaze();
        display.printRoom();
    }

    private static void File() {
        // Not Yet Implemented
    }

    private static void Help() {
        // Not Yet Implemented
    }
}