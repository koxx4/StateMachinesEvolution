package org.koxx4.machine;

public record StateMachineConfiguration(char stateChar,
                                        char inputChar,
                                        char outputChar,
                                        int clockSpeed,
                                        boolean tickOnHigh) {
    public StateMachineConfiguration(){
        this('q', 'z', 'y', 1, true);
    }

    public StateMachineConfiguration(char stateChar, char inputChar, char outputChar){
        this(stateChar, inputChar, outputChar, 1, true);
    }

    public StateMachineConfiguration(int clockSpeed, boolean tickOnHigh){
        this('q', 'z', 'y', clockSpeed, tickOnHigh);
    }

}
