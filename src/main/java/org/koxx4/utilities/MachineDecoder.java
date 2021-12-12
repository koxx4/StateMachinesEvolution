package org.koxx4.utilities;

import org.koxx4.machine.InvalidStateMachineEquation;
import org.koxx4.machine.StateMachine;

public interface MachineDecoder<MachineType extends StateMachine> {

    MachineType decodeFromEquation(String equation) throws InvalidStateMachineEquation;
    MachineType decodeFromNaturalExpression(String expression);

}
