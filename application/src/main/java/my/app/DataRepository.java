package my.app;

import modular.di.annotations.DiComponent;

@DiComponent
public class DataRepository {

    public void doWork() {
        System.out.println("Do work");
    }
}
