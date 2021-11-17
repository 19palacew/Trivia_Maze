package Controller;

import Model.Maze;
import Model.Room;
import Model.RoomBlocker;

public class GameManager {
    public static void main(String[] args) {
        Maze mainMaze = new Maze();
        Room rm = new Room(new RoomBlocker(true, true,true, true));
        System.out.println(rm.getDoor(0).getQuestion());
    }
}