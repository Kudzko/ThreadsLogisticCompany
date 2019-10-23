package by.andersen.kudko.threads.model;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Company {
    private Store companyStore;
    private List<Ramp> ramps;
    private Semaphore checkingIn;



    public Company() {
        ramps = new ArrayList<>();

    }

    public Company(int storeCapacity, List<Ramp> ramps) {
        this.companyStore = new Store(storeCapacity);
        this.ramps = ramps;
        this.checkingIn = new Semaphore(ramps.size(), true);
    }

    public Company(Store companyStore, List<Ramp> ramps) {
        this.companyStore = companyStore;
        this.ramps = ramps;
        this.checkingIn = new Semaphore(ramps.size(), true);
    }

    public void addRamp(Ramp newRamp) {
        ramps.add(newRamp);
        changePermits(ramps.size());
    }

    public boolean removeRamp(Ramp ramp) {
        boolean isRemoved = ramps.remove(ramp);
        changePermits(ramps.size());
        return isRemoved;

    }

    public Ramp removeRamp(int index) {
        Ramp rampOld = ramps.remove(index);
        changePermits(ramps.size());
        return rampOld;
    }

    private void changePermits(int permits) {
        checkingIn = new Semaphore(permits, true);
    }

    public void checkIn(){
        try{
            checkingIn.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void checkOut(){
        try{
            checkingIn.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Ramp> getRamps(){
        return ramps;
    }

}
