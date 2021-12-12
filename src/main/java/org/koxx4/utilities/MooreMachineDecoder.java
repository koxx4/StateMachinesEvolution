package org.koxx4.utilities;


import org.koxx4.machine.*;
import org.koxx4.syntax.InvalidSyntaxException;
import org.koxx4.syntax.MooreMachineEquationValidator;
import org.koxx4.syntax.SimpleSyntaxValidatorBuilder;
import org.koxx4.syntax.SyntaxValidator;

import static org.koxx4.syntax.SimpleSyntaxRegexType.ANY_DIGIT;
import static org.koxx4.syntax.SimpleSyntaxRegexType.ANY_LETTER;

import java.util.*;


public class MooreMachineDecoder implements MachineDecoder<MooreMachine>{

    @Override
    public MooreMachine decodeFromEquation(String equation) throws InvalidStateMachineEquation {
        equation = equation.strip();
        checkEquationForSyntaxErrors(equation);

//        //Get partial terms and remove first element which is "" empty string
//        //as specified in documentation by split method.
//        List<String> partialTerms = Arrays.stream(equation.split("[()]+")).toList();
//        partialTerms.remove(0);
//
//        String assumedStateSign = partialTerms.get(1).split("[0-9]+")[0];
//        String assumedInputSign = partialTerms.get(2).split("[0-9]+")[0];
//
//        //We assume default output sign because we can't infer it from equation
//        MooreMachine decodedMachine = new MooreMachine(
//                new StateMachineConfiguration(assumedStateSign, assumedInputSign, "y"));
//
//        return decodedMachine;
        return null;
    }

    private void checkEquationForSyntaxErrors(String equation) throws InvalidStateMachineEquation {
        SyntaxValidator syntaxValidator = new MooreMachineEquationValidator();
        try {
            syntaxValidator.validate(equation);
        } catch (InvalidSyntaxException exception){
            throw new InvalidStateMachineEquation(exception.getMessage());
        }
    }

    @Override
    public MooreMachine decodeFromNaturalExpression(String expression) {
        return null;
    }

}