package org.koxx4.machine;

import java.util.List;

public abstract class Edge {
    private final State startingState;
    private final State endingState;
    private String name;

    public Edge(String name, State startingState, State endingState) {
        this.startingState = startingState;
        this.endingState = endingState;
        this.name = name;
    }

    public State getStartingState() {
        return startingState;
    }

    public State getEndingState() {
        return endingState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
