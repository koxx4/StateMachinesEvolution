package org.koxx4.machine;

import java.util.List;

public abstract class Edge {
    private final String name;
    private final int index;

    public Edge(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return name + index;
    }

}
