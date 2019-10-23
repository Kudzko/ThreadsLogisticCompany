package by.andersen.kudko.threads.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ramp {
    private static int counter = 0;

    private Store store;
    private int id;
    private Lock rampLock = new ReentrantLock();

    {
        counter++;
        id = counter;
    }

    public Ramp(Store store) {
        this.store = store;
    }

    public boolean isFree() {
        return rampLock.tryLock();
    }

    public void leaveRamp() {
        rampLock.unlock();
    }

    public void loadStore(Track track) {
        while (store.hasPlace() && (!track.isEmpty())) {
            store.put(track.take());
        }
    }

    public void unloadStore(Track track) {
        while (!store.isEmpty() && (track.hasPlace())) {
            track.put(store.take());
        }
    }

    public int getId() {
        return id;
    }
}
