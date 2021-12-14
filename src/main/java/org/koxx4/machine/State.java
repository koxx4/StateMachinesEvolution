package org.koxx4.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class State {
    private final String name;
    private final int index;

    public State(String name, int index) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return getIndex() == state.getIndex();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, index);
    }

    @Override
    public String toString() {
        return name + index;
    }
}
