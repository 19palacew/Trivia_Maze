package Controller;

import Model.Database;
import Model.Maze;
import View.Display;

import java.util.Scanner;

public class GameManager {
    private static Display display;
    private static Maze mainMaze;
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Database.connectToDatabase();
        Display.printInstructions();
        Display.startMsg();
        bootGame();
        runGame();
        input.close();
    }

    private static void bootGame(){
        // Load Game / Start New Game
        boolean gameStarted = false;
        String userGameStartInput = "";
        while (!gameStarted) {
            userGameStartInput = input.nextLine();
            if (userGameStartInput.toLowerCase().matches("load game|new game")) {
                gameStarted = true;
            }else {
                Display.beginGameWarning();
            }
        }
        if (userGameStartInput.equalsIgnoreCase("load game")) {
            loadGame();
        } else {
            newGame();
        }
    }

    private static void runGame(){
        updateScreen();
        nextAction();
        if (!mainMaze.isPossible()){
            Display.playerLost();
            return;
        }
        if (mainMaze.goalReached()){
            Display.playerWon();
            return;
        }
        runGame();
    }

    private static void newGame() {
        mainMaze = new Maze();
        display = new Display(mainMaze);
    }

    private static void loadGame() {
        // Not Yet Implemented
    }

    private static void nextAction(){
        boolean inputGood = false;
        String userAction;
        while (!inputGood){
            Display.printPrompt();
            userAction = input.nextLine();
            if (userAction.toLowerCase().matches("north|south|east|west")){
                //TODO needs to ask question first
                Move movement = new Move(mainMaze, userAction);
                inputGood = movement.checkValidMove();
                if (!inputGood) {
                    Display.directionWarning();
                }
            } else if (userAction.toLowerCase().matches("help")){
                inputGood = true;
                Help();
            } else if (userAction.toLowerCase().matches("file")) {
                inputGood = true;
                File();
            } else {
                Display.userActionWarning();
            }
        }
    }

    private static void updateScreen(){
        display.printMaze();
        display.printRoom();
    }

    private static void File() {
        // Not Yet Implemented
    }

    private static void Help() {
        Display.helpMenu();
        boolean inputGood = false;
        String helpAction;
        while (!inputGood){
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
                Display.menuWarning();
            }
        }
    }
}