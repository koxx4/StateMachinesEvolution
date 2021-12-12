package org.koxx4.utilities;


import org.koxx4.machine.*;
import org.koxx4.syntax.InvalidSyntaxException;
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

        if (!equation.startsWith("("))
            throw new InvalidStateMachineEquation("Equation should start with '('");

        if (!equation.endsWith(")"))
            throw new InvalidStateMachineEquation("Equation should end with ')'");


        SyntaxValidator syntaxValidator = new SimpleSyntaxValidatorBuilder()
                .nextTokenShouldBeDistinctive("(", 1)
                .nextTokenShouldBeDistinctive(ANY_LETTER, 1)
                .nextTokenShouldBeDistinctive(ANY_LETTER, 1).orDistinctive(ANY_DIGIT, 1)
                .nextTokenShouldBeDistinctive(ANY_DIGIT, 1).orDistinctive("(", 2)
                .nextTokenShouldBeDistinctive(ANY_LETTER,2)
                .nextTokenShouldBeDistinctive(ANY_LETTER,2).orDistinctive(ANY_DIGIT,2)
                .nextTokenShouldBeDistinctive(ANY_DIGIT, 2).orDistinctive(ANY_LETTER,3)
                .nextTokenShouldBeDistinctive(ANY_LETTER,3).orDistinctive(ANY_DIGIT,3)
                .nextTokenShouldBeDistinctive(ANY_DIGIT,3)
                .orDistinctive("(", 2).or(",").or(")")
                .nextTokenShouldBe(")").or(",")
                .nextTokenShouldBeDistinctive(ANY_LETTER, 2)
                .build();
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