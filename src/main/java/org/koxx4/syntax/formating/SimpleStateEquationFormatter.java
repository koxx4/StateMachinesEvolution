package org.koxx4.syntax.formating;

import org.koxx4.syntax.formating.SyntaxFormatter;
import org.koxx4.utilities.ColorUtilities;

import java.awt.*;
import java.util.Stack;

public class SimpleStateEquationFormatter implements SyntaxFormatter {

    private final Color startingSyntaxColor = new Color(0,100,20);

    @Override
    public String format(String input) {
        return colorParentheses(input);
    }

    private String colorParentheses(String input){
        ColorUtilities.resetColorProgression();
        Color currentParenthesesColor = startingSyntaxColor;
        Stack<Color> parenthesesColorPairs = new Stack<>();
        char[] tokens = input.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for (char token : tokens){

            if (!isTokenParenthesis(token)) {
                stringBuilder.append(token);
            }
            else if (isTokenOpeningParenthesis(token)){

                //Generate new color and save it on stack
                currentParenthesesColor = parenthesesColorPairs
                        .push(ColorUtilities.getNextColorFrom(currentParenthesesColor,
                                40, 70 , 107));

                stringBuilder.append("<font color=").
                        append(ColorUtilities.colorToHexString(currentParenthesesColor)).
                        append("><b>(</b></font>");
            }
            else if (isTokenClosingParenthesis(token)){
                var matchingOpeningColor = new Color(0,0,0);
                if(!parenthesesColorPairs.isEmpty())
                    matchingOpeningColor = parenthesesColorPairs.pop();

                stringBuilder.append("<font color=").
                        append(ColorUtilities.colorToHexString(matchingOpeningColor)).
                        append("><b>)</b></font>");
            }
        }
        return stringBuilder.toString();
    }

    private boolean isTokenOpeningParenthesis(char token){
        return token == '(';
    }

    private boolean isTokenClosingParenthesis(char token){
        return token == ')';
    }

    private boolean isTokenParenthesis(char token){
        return isTokenOpeningParenthesis(token) || isTokenClosingParenthesis(token);
    }

}
