package org.koxx4.machine;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class StateMachine<StateType extends State, EdgeType extends Edge>{

    private final MutableValueGraph<StateType, EdgeType> graph;
    private StateMachineConfiguration configuration;

    public StateMachine(StateMachineConfiguration configuration) {
        this.configuration = configuration;
        this.graph = ValueGraphBuilder.directed().allowsSelfLoops(true).build();
    }

    public StateMachine(MutableValueGraph<StateType, EdgeType> graph, StateMachineConfiguration configuration) {
        this.configuration = configuration;
        this.graph = graph;
    }

    public StateMachine(StateMachine<StateType, EdgeType> other) {
        this.configuration = other.getConfiguration();
        this.graph = other.getGraph();
    }

    public StateMachineConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(StateMachineConfiguration configuration) {
        this.configuration = configuration;
    }

    public MutableValueGraph<StateType, EdgeType> getGraph() {
        return this.graph;
    }

    public void addState(StateType stateType){
        this.graph.addNode(stateType);
    }

    public void addOrUpdateTransition(StateType startingState, StateType endingState, EdgeType edge ){
        this.graph.putEdgeValue(startingState, endingState, edge);
    }

    public void addOrUpdateTransition(StateType startingState, Transition<StateType, EdgeType> transition){
        this.graph.putEdgeValue(startingState, transition.state(), transition.edge() );
    }

}