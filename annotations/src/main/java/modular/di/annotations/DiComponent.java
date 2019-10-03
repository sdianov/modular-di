package modular.di.annotations;

public @interface DiComponent {

    String name() default "";

    boolean isInterface() default false;

    String description() default "";

    DiScope scope() default DiScope.SINGLETON;

    String[] rawLines() default {};
}
