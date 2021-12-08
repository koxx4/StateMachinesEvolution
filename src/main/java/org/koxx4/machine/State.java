package org.koxx4.machine;

import java.util.ArrayList;
import java.util.List;

public abstract class State {
    private final List<Edge> edges;

    public State() {
        edges = new ArrayList<>();
    }

    public void addEdge(Edge edge){
        this.edges.add(edge);
    }

    public void removeEdge(Edge edge){
        this.edges.remove(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
