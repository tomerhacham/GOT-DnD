package GoT.DnD;

import java.util.LinkedList;

public class Controller implements Observable {
    //Fields:
    LinkedList<Observer> observers;

    //Constructor
    public Controller(){
        observers=new LinkedList<Observer>();
    }

    @Override
    public void register(Observer newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for(Observer observer:observers){
            observer.update();
        }
    }
}
