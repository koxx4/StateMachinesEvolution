package org.koxx4.machine;

public class MooreMachine extends StateMachine<MooreState>{

    public MooreMachine(StateMachineConfiguration configuration) {
        super(configuration);
    }

    public static MooreMachine decodeFromEquation(String equation){
        char[] equationTokens = equation.toCharArray();

        return null;
    }

}
