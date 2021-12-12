package org.koxx4.machine;

import java.util.ArrayList;
import java.util.List;

public abstract class State {
    private final List<Edge> edges;
    private String name;

    public State(String name) {
        edges = new ArrayList<>();
        this.name = name;
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
