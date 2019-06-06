package GoT.DnD;

import java.util.LinkedList;
import java.util.Scanner;

public class Controller {

    public static int choosePlayer(){
        Scanner reader = new Scanner(System.in);
        int input;
        do {
            while (!reader.hasNextInt()) {
                reader.next();
            }
            input = reader.nextInt();
        } while(input < 1 || input > 6);
        return input;
    }

    public static String getInput(){
        Scanner reader = new Scanner(System.in);
        String validInput = "wsadeq";
        String input;
        do {
            input = reader.next().toLowerCase();
        } while (input.length() != 1 || !validInput.contains(input));
        return input;
    }

}
