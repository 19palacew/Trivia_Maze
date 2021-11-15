package Controller;

import java.util.Scanner;

public class Input {

    private static String myInput;

    public Input(){
        prompt();
        Scanner input = new Scanner(System.in);
        myInput = input.next().toLowerCase();
    }

    private static void prompt() {
        System.out.print("Input: ");
    }

    public static String getMyInput() {
        return myInput;
    }
}
