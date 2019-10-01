package main;

import java.util.logging.Logger;

class Container {

    private static final Logger LOGGER = Logger.getLogger(Container.class.getName());

    public void init() {
        //scan


    }


    public <T> T resolve(Class<T> clazz) {
        LOGGER.info("Resoling class: " + clazz.getName());
        return null;
    }

    public Object resolveByName(String name) {
        LOGGER.info("Resoling name: " + name);

        return null;
    }
}
