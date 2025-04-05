package util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public final class FeedEveryXHours implements Serializable {
    private Map<Class, Integer> staticValues;

    public FeedEveryXHours() {
        this.staticValues = new HashMap<>();
    }

    public int get(Class className) {
        return staticValues.get(className);
    }

    public void add(Class className, Integer integer) {
        this.staticValues.put(className, integer);
    }
}
