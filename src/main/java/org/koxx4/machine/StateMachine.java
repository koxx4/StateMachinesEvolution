package org.koxx4.machine;

import java.util.ArrayList;
import java.util.List;

public abstract class StateMachine<StateType extends State>{
    private final List<StateType> states;
    private StateMachineConfiguration configuration;

    public StateMachine(StateMachineConfiguration configuration) {
        this.configuration = configuration;
        this.states = new ArrayList<>();
    }

    public StateMachineConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(StateMachineConfiguration configuration) {
        this.configuration = configuration;
    }

    public List<StateType> getStates() {
        return states;
    }

    public void addState(StateType state){
        this.states.add(state);
    }

    public void addAllStates(List<StateType> states){
        this.states.addAll(states);
    }

}