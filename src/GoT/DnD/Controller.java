package GoT.DnD;

import java.util.LinkedList;
import java.util.Scanner;

public class Controller {

/*    //Fields:
    LinkedList<Observer> observers;

    //Constructor
    public Controller(){
        observers=new LinkedList<Observer>();
    }

 */

    public static String getInput(){
        Scanner reader = new Scanner(System.in);
        String validInput = "wsadeq";
        String input;
        do {
            input = reader.next().toLowerCase();
        } while (input.length() != 1 || !validInput.contains(input));
        return input;
    }


/*
    //region Observable implement
    @Override
    public void register(Observer observer) {

    }

    @Override
    public void unregister(Observer observer) {

    }

    @Override
    public void notifyObserver(Object message) {
        for (Observer obs:observers){
            obs.update((char)message);
        }
    }
    //endregion

 */
}
