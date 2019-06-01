package GoT.DnD.Business_Layer;

public interface Observable {
    public void register(Observer observer);
    public void unregister(Observer observer);
    public void notifyObserver();
}
