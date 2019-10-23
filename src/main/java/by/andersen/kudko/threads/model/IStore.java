package by.andersen.kudko.threads.model;

public interface IStore {
    boolean isEmpty();
    boolean put(Cargo cargo);
    Cargo take();
    boolean hasPlace();
    int amountEmptyPlaces();
}
