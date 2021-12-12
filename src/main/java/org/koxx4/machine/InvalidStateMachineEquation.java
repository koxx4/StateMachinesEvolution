package org.koxx4.machine;

public class InvalidStateMachineEquation extends Exception{

    public InvalidStateMachineEquation() {
        super("Provided machine equation has invalid syntax!");
    }

    public InvalidStateMachineEquation(String message) {
        super(message);
    }
}
