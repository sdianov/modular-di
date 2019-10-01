package main;

import modular.di.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Startup {

    private List<String> initialServices = new ArrayList<>();

    public List<String> getInitialServices() {
        return initialServices;
    }

    public void setInitialServices(List<String> initialServices) {
        this.initialServices = initialServices;
    }
}
