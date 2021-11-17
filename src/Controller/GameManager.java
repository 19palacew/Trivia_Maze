package Controller;

import Model.Database;
import Model.Maze;
import Model.Room;
import Model.RoomBlocker;
import View.Display;

public class GameManager {
    public static void main(String[] args) {
        Database.connectToDatabase();
        Maze mainMaze = new Maze();
        Display.printInstructions();
        Display.printMaze(mainMaze);
    }

    public static void playGame(){

    }
}