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
        System.out.println(mainMaze.getCurrentRoom().getDoor(1).getQuestion());
        mainMaze.setXAndY(0,1);
        System.out.println(mainMaze.getCurrentRoom().getDoor(0).getQuestion());
        Display.printMaze(mainMaze);
        mainMaze.setXAndY(1,1);
        System.out.println(mainMaze.getCurrentRoom().getDoor(0).getQuestion());
    }

    public static void playGame(){

    }
}