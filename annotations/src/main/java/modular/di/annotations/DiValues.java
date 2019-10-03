package modular.di.annotations;

public @interface DiValues {

    @interface MapValue {

        String key();

        String value();
    }


    String value();

    DiCollectionOperation operation();

    String[] list();

    MapValue[] map();
}
