package GoT.DnD;

public interface Observable<E> {
    public void register(Observer observer);
    public void unregister(Observer observer);
    public void notifyObserver(E message);
}
