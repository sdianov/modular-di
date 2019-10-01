package main;

public class Program {

    public static void main(String[] args) {

        Container container = new Container();
        container.init();

        Startup startup = container.resolve(Startup.class);

        for (String name : startup.getInitialServices()) {
            container.resolveByName(name);
        }

    }
}
