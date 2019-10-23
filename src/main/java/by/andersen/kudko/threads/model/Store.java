package by.andersen.kudko.threads.model;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Store implements IStore {
    private Queue<Cargo> store;
    private int capacity;

    public Store(int capacity) {
        this.capacity = capacity;
        this.store = new ConcurrentLinkedQueue<>();
    }

    public Store(Queue<Cargo> store, int capacity) {
        this.store = store;
        this.capacity = capacity;
    }

    @Override
    public boolean put(Cargo cargo) {
        if (store.size() < capacity) {
            return store.add(cargo);
        } else {
            return false;
        }
    }

    @Override
    public Cargo take() {
        return store.poll();
    }

    @Override
    public boolean hasPlace() {
        return store.size() < capacity ? true : false;
    }

    @Override
    public boolean isEmpty() {
        return store.isEmpty();
    }

    @Override
    public int amountEmptyPlaces() {
        return capacity - store.size();
    }

}
