package com.monpro.file;

import java.util.HashMap;
import java.util.Map;

public class Path {
    private String path;
    private Integer value;
    private Map<String, Path> children;

    public Path() {
        path = null;
        value = null;
        children = new HashMap<>();
    }

    public boolean containsKey(String path) {
        return children.containsKey(path);
    }

    public Path get(String key) {
        return children.get(key);
    }

    public void put(String key) {
        children.put(key, new Path());
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean hasValue() {
        return this.value != null;
    }

    public int getValue() {
        return value;
    }
}
