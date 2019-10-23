package by.andersen.kudko.threads.util;

import by.andersen.kudko.threads.model.Cargo;

public class CargoCreator {
    private  static int count = 0;
    public static Cargo create (){
        return new Cargo( count++, "box");
    }
}
