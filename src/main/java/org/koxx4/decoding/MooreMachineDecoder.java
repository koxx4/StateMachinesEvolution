package org.koxx4.decoding;


import org.koxx4.machine.*;
import org.koxx4.syntax.InvalidSyntaxException;
import org.koxx4.syntax.MooreMachineEquationValidator;
import org.koxx4.syntax.SyntaxValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class MooreMachineDecoder implements MachineDecoder<MooreMachine> {

    @Override
    public MooreMachine decodeFromEquation(String equation) throws InvalidStateMachineEquation {
        equation = equation.strip();
        checkEquationForSyntaxErrors(equation);


        MooreState currentState = getFirstState(equation);
        MooreState lastState = currentState;
        MooreEdge  firstFoundEdge = getFirstFoundEdge(equation, currentState.getName());
        var tokens = equation.substring(currentState.toString().length() + 2).toCharArray();

        Stack<MooreState> foundStates = new Stack<>();
        foundStates.push(currentState);

        //We assume default output sign because we can't infer it from equation
        MooreMachine machineToDecode = new MooreMachine(
                new StateMachineConfiguration(currentState.getName(), firstFoundEdge.getName(), "y"));
        machineToDecode.addState(currentState);

        for (int i = 0; i < tokens.length - 2; i++){

            if (tokens[i] == '('){
                foundStates.push(currentState);
                lastState = currentState;
            }
            else if (tokens[i] == ')'){
                currentState = foundStates.pop();
                lastState  = foundStates.pop();
                foundStates.push(currentState);
            }
            else if (tokens[i] == firstFoundEdge.getName().charAt(0)){
                var transition =
                        decodeNextTransition(lastState, firstFoundEdge.getName(), i, tokens);
                currentState = transition.state();
                machineToDecode.addOrUpdateTransition(lastState, transition);
                i += transition.state().toString().length() + transition.edge().toString().length() - 1;
            }
        }

        return machineToDecode;
    }


    private Transition<MooreState, MooreEdge> decodeNextTransition(MooreState lastState, String inputSign,
                                            int currentTokenIndex, char[] tokens) throws InvalidStateMachineEquation {
        int inputIndex;
        StringBuilder indexBuilder = new StringBuilder();
        currentTokenIndex = currentTokenIndex + inputSign.length();

        while (Character.isDigit(tokens[currentTokenIndex])){
            indexBuilder.append(tokens[currentTokenIndex]);
            currentTokenIndex++;
        }
        inputIndex = Integer.parseInt(indexBuilder.toString());
        if (inputIndex < 0)
            throw new InvalidStateMachineEquation("Ummm... inputs cannot be indexed with a negative number");

        indexBuilder = new StringBuilder();

        currentTokenIndex += lastState.getName().length();
        while (Character.isDigit(tokens[currentTokenIndex])){
            indexBuilder.append(tokens[currentTokenIndex]);
            currentTokenIndex++;
        }
        int stateIndex = Integer.parseInt(indexBuilder.toString());
        if (stateIndex < 0)
            throw new InvalidStateMachineEquation("Ummm... state cannot be indexed below 0");

        MooreState foundState = new MooreState(lastState.getName(), stateIndex, 0);

        return new Transition<>(foundState, new MooreEdge(inputSign, inputIndex));
    }

    private void checkEquationForSyntaxErrors(String equation) throws InvalidStateMachineEquation {
        SyntaxValidator syntaxValidator = new MooreMachineEquationValidator();
        try {
            syntaxValidator.validate(equation);
        } catch (InvalidSyntaxException exception){
            throw new InvalidStateMachineEquation(exception.getMessage());
        }
    }

    private MooreState getFirstState(String equation) throws InvalidStateMachineEquation {
        var tokens = equation.toCharArray();
        StringBuilder stateSignBuilder = new StringBuilder();
        StringBuilder stateIndexBuilder = new StringBuilder();

        for (int i = 1; i < tokens.length - 1; i++){
            if (tokens[i] == '(')
                break;
            else if (Character.isLetter(tokens[i]))
                stateSignBuilder.append(tokens[i]);
            else
                stateIndexBuilder.append(tokens[i]);
        }

        int stateIndex = Integer.parseInt(stateIndexBuilder.toString());
        if (stateIndex < 0)
            throw new InvalidStateMachineEquation("Ummm... first state cannot be indexed below 0");

        return new MooreState(
                stateSignBuilder.toString(), stateIndex, 0);
    }

    private MooreEdge getFirstFoundEdge(String equation, String stateSign) throws InvalidStateMachineEquation {
        var tokens = equation.substring(equation.indexOf('(',1)).toCharArray();
        StringBuilder outputSignBuilder = new StringBuilder();
        StringBuilder outputIndexBuilder = new StringBuilder();

        for (int i = 1; i < tokens.length - 1; i++){
            if (tokens[i] == stateSign.charAt(0))
                break;
            else if (Character.isLetter(tokens[i]))
                outputSignBuilder.append(tokens[i]);
            else
                outputIndexBuilder.append(tokens[i]);
        }
        int inputIndex = Integer.parseInt(outputIndexBuilder.toString());
        if (inputIndex < 0)
            throw new InvalidStateMachineEquation("Ummm... inputs cannot be indexed with a negative number");

        return new MooreEdge(outputSignBuilder.toString(), inputIndex);
    }

    @Override
    public MooreMachine decodeFromNaturalExpression(String expression) {
        return null;
    }

}