package modular.di.processor;

import modular.di.annotations.DiCollectionOperation;
import modular.di.annotations.DiScope;

import java.util.HashMap;
import java.util.Map;

public class AnnotationUtils {

    public static final String ATG_GEN_OPTION = "digendir";

    public static final Map<DiScope, String> scopeNames;

    public static final Map<DiCollectionOperation, String> operatorSymbols;

    static {

        scopeNames = new HashMap<>();
        scopeNames.put(DiScope.SINGLETON, "singleton");
        scopeNames.put(DiScope.PROTOTYPE, "prototype");

        operatorSymbols = new HashMap<>();
        operatorSymbols.put(DiCollectionOperation.APPEND, "+");
        operatorSymbols.put(DiCollectionOperation.REMOVE, "-");
        operatorSymbols.put(DiCollectionOperation.SET, "");

    }

    // prevent from instantiation
    private AnnotationUtils() {
    }

    public static boolean isNullOrBlank(final String s) {
        return s == null || s.trim().isEmpty();
    }


}
