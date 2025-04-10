package util;

import model.Animal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public final class FeedEveryXHours implements Serializable {
    private final Map<Class<? extends Animal>, Integer> staticValues;

    public FeedEveryXHours() {
        this.staticValues = new HashMap<>();
    }

    public int get(Class<? extends Animal> className) {
        return staticValues.get(className);
    }

    public void add(Class<? extends Animal> className, Integer integer) {
        this.staticValues.put(className, integer);
    }
}
