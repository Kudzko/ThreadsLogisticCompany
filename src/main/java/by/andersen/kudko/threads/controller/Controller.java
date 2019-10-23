package by.andersen.kudko.threads.controller;

import by.andersen.kudko.threads.model.Company;
import by.andersen.kudko.threads.model.Ramp;
import by.andersen.kudko.threads.model.Store;
import by.andersen.kudko.threads.model.Track;
import by.andersen.kudko.threads.util.CargoCreator;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class Controller {
    public static final org.apache.log4j.Logger LOG;

 public static final int RAMP_AMOUNT = 2;
 public static final int COMPANY_STORE_CAPACITY = 100;
 public static final int TRACK_AMOUNT = 2;
 public static final int TRACK_CAPACITY = 10;
 public static final  int AMOUNT_BOXES = 10;


    private static final ExecutorService trackHeap = Executors.newFixedThreadPool
            (TRACK_AMOUNT);



    static {
        LOG = org.apache.log4j.Logger.getRootLogger();
    }


    public static void main(String[] args) {







        Store companyStore = new Store(100);

        for (int i = 0; i < AMOUNT_BOXES; i++) {
            companyStore.put(CargoCreator.create());
        }

        List<Ramp> ramps = new ArrayList<>();

        for (int i = 0; i < RAMP_AMOUNT; i++) {
            ramps.add(new Ramp(companyStore));
        }

        Company company = new Company(companyStore, ramps);

        List<Track> tracks = new ArrayList<>();
        for (int i = 0; i < TRACK_AMOUNT; i++) {
            tracks.add(new Track(company, TRACK_CAPACITY));
        }

        for (int i = 0; i < TRACK_AMOUNT; i++) {
            trackHeap.execute(tracks.get(i));;
        }
        trackHeap.shutdown();


        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < TRACK_AMOUNT; i++) {
            tracks.get(i).stopWork();
        }



    }
}
