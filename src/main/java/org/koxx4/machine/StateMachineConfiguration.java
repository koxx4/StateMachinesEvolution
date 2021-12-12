package org.koxx4.machine;

public record StateMachineConfiguration(String stateSign,
                                        String inputSign,
                                        String outputSign,
                                        int clockSpeed,
                                        boolean tickOnHigh) {

    public StateMachineConfiguration(){
        this("q", "z", "y", 1, true);
    }

    public StateMachineConfiguration(String stateSign, String inputSign, String outputSign){
        this(stateSign, inputSign, outputSign, 1, true);
    }

    public StateMachineConfiguration(int clockSpeed, boolean tickOnHigh){
        this("q", "z", "y", clockSpeed, tickOnHigh);
    }

}
