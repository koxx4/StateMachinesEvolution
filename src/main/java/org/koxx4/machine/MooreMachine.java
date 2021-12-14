package org.koxx4.machine;

import com.google.common.graph.MutableValueGraph;

public class MooreMachine extends StateMachine<MooreState, MooreEdge>{


    public MooreMachine(StateMachineConfiguration configuration) {
        super(configuration);
    }

    public MooreMachine(MutableValueGraph<MooreState, MooreEdge> graph, StateMachineConfiguration configuration) {
        super(graph, configuration);
    }

    public MooreMachine(StateMachine<MooreState, MooreEdge> other) {
        super(other);
    }
}
