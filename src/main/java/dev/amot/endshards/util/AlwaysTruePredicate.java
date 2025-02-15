package dev.amot.endshards.util;

import java.util.function.Predicate;

public class AlwaysTruePredicate<Object> implements Predicate<Object> {
    public boolean test(Object x) {
        return true;
    }
}
