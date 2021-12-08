package org.koxx4.machine;

import java.util.List;

public abstract class Edge {
    private final State startingState;
    private final State endingState;

    public Edge(State startingState, State endingState) {
        this.startingState = startingState;
        this.endingState = endingState;
    }

    public State getStartingState() {
        return startingState;
    }

    public State getEndingState() {
        return endingState;
    }
}
