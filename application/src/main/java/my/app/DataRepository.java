package my.app;

import modular.di.annotations.Component;

@Component
public class DataRepository {

    public void doWork() {
        System.out.println("Do work");
    }
}
