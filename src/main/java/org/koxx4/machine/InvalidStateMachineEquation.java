package org.koxx4.machine;

import org.koxx4.syntax.InvalidSyntaxException;

public class InvalidStateMachineEquation extends InvalidSyntaxException {

    public InvalidStateMachineEquation() {
        super("Provided machine equation has invalid syntax!");
    }

    public InvalidStateMachineEquation(String message) {
        super(message);
    }
}
