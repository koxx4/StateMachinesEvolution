package org.koxx4.machine;

import com.google.common.graph.MutableValueGraph;

public class MealyMachine extends StateMachine<MealyState, MealyEdge>{

    public MealyMachine(StateMachineConfiguration configuration) {
        super(configuration);
    }

    public MealyMachine(MutableValueGraph<MealyState, MealyEdge> graph, StateMachineConfiguration configuration) {
        super(graph, configuration);
    }

    public MealyMachine(StateMachine<MealyState, MealyEdge> other) {
        super(other);
    }
}
