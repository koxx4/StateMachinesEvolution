package org.koxx4.syntax;

import com.google.common.graph.ImmutableValueGraph;
import org.koxx4.machine.InvalidStateMachineEquation;

import java.util.Stack;

import static org.koxx4.syntax.SimpleSyntaxRegexType.ANY_DIGIT;
import static org.koxx4.syntax.SimpleSyntaxRegexType.ANY_LETTER;

public class MooreMachineEquationValidator implements SyntaxValidator{

    private final SyntaxValidator syntaxValidator;

    public MooreMachineEquationValidator() {
        this.syntaxValidator = new SimpleSyntaxValidatorBuilder()
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
    }

    @Override
    public void validate(String textToCheck) throws InvalidSyntaxException {

        if (!textToCheck.startsWith("("))
            throw new InvalidStateMachineEquation("Equation should start with '('");

        if (!textToCheck.endsWith(")"))
            throw new InvalidStateMachineEquation("Equation should end with ')'");

        checkParenthesesIntegrity(textToCheck);
        syntaxValidator.validate(textToCheck);
    }

    private void checkParenthesesIntegrity(String equation) throws InvalidSyntaxException {
        Stack<Integer> openingParentheses = new Stack<>();
        char[] tokens = equation.toCharArray();
        for (int i = 0; i < tokens.length; i++){

            if (tokens[i] == '(') {
                openingParentheses.push(i);
                continue;
            }

            if (openingParentheses.isEmpty())
                throw new InvalidStateMachineEquation("Missing closing ')' at position " + (i + 1));
            openingParentheses.pop();
        }
    }

}
