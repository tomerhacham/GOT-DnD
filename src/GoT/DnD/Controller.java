package GoT.DnD;

import java.util.LinkedList;
import java.util.Scanner;

public class Controller implements Observable {
    //Fields:
    LinkedList<Observer> observers;

    //Constructor
    public Controller(){
        observers=new LinkedList<Observer>();
    }

    public void getInput(){
        Scanner reader = new Scanner(System.in);
        char c = reader.next().charAt(0);
        while(!reader.hasNext()){

        }

        //TODO: notifiyObserver

    }

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
}
