package GoT_DnD;

public interface Observable<E> {
    void register(Observer observer);
    void unregister(Observer observer);
    void notifyObserver(E message);
}
