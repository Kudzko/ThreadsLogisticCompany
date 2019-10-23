package by.andersen.kudko.threads.model;



import org.apache.log4j.Logger;


import java.util.concurrent.TimeUnit;

public class Track implements Runnable, IStore {
    public static final Logger LOG;

    private Store internalTrackStore;
    private boolean isWorking = true;
    private Thread thread;
    private Company company;

    static {
        LOG = Logger.getRootLogger();
    }

    public Track(Company company, int capacity) {
        this.internalTrackStore = new Store(capacity);
        this.company = company;
        this.thread = new Thread(this);
    }


    public void run() {
        LOG.info("[Thread " + " name = " + this
                .thread.getName() + "] " +
                " is " +
                "STARTED.");
        while (isWorking) {
            company.checkIn();
            for (Ramp r :
                    company.getRamps()) {
                if (r.isFree()) {
                    try {
                        if (internalTrackStore.isEmpty()) {
                            LOG.info("[IF BRANCH]");
                            LOG.info("[" + this.thread.getName() +
                                    "] [ramp " + r.getId() + "] boxes are " +
                                    "loading  from STORE to TRACK");

                            r.unloadStore(this);

                            LOG.info("[" + this.thread.getName() +
                                    "] [ramp " + r.getId() + "] boxes are " +
                                    "loaded  from  STORE to TRACK");
                        } else {
                            LOG.info("[ELSE BRANCH]");

                            LOG.info("[" + this.thread.getName() +
                                    "] [ramp " + r.getId() + "] boxes are " +
                                    "loading  from TRACK to STORE");


                            r.loadStore(this);

                            LOG.info("[" + this.thread.getName() +
                                    "] [ramp " + r.getId() + "] boxes are " +
                                    "loaded  from TRACK to STORE");
                        }

                        doDelay(100);

                    } finally {
                        r.leaveRamp();
                    }



                }
            }

            company.checkOut();

            doDelay(300);
        }

        LOG.info("[Thread " + " name = " + this
                .thread.getName() + "] " +
                " is " +
                "FINISHED.");

    }

    private void doDelay(int Milliseconds){
        //++++++++++++++++++++++++++++delay
        try {
            TimeUnit.MILLISECONDS.sleep(Milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //------------------------------delay
    }


    public Store getInternalStore() {
        return internalTrackStore;
    }


    @Override
    public boolean isEmpty() {
        return internalTrackStore.isEmpty();
    }

    @Override
    public boolean put(Cargo cargo) {
        return internalTrackStore.put(cargo);
    }

    @Override
    public Cargo take() {
        return internalTrackStore.take();
    }

    @Override
    public boolean hasPlace() {
        return internalTrackStore.hasPlace();
    }

    @Override
    public int amountEmptyPlaces() {
        return internalTrackStore.amountEmptyPlaces();
    }

    public void stopWork() {
        isWorking = false;
    }


}
