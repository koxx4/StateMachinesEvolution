package org.koxx4.syntax;

import com.google.common.graph.ImmutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleSyntaxValidator implements SyntaxValidator{

    private final ImmutableValueGraph<UniqueToken, String> expectedTokensGraph;
    private final UniqueToken entryPointToken;

    public SimpleSyntaxValidator(ImmutableValueGraph<UniqueToken, String> expectedTokensGraph,
                                 UniqueToken entryPointToken) {
        this.expectedTokensGraph = expectedTokensGraph;
        this.entryPointToken = entryPointToken;
    }

    @Override
    public void validate(String textToCheck) throws InvalidSyntaxException {
        char[] tokens = (entryPointToken.getValue() + textToCheck).toCharArray();
        UniqueToken lastToken = entryPointToken;
        int nextIndexLeap = 0;

        for (int i = 0; i < tokens.length - 1; i += nextIndexLeap){

            var expectedTokensSorted = getAdjacentSuccessors(lastToken)
                    .stream().sorted(Comparator.reverseOrder()).toList();
            var isAnyTokenValid = false;

            for (var expectedToken : expectedTokensSorted){

                if (expectedToken.getRegexType() != null){

                    String nextTokenValue = String.valueOf(textToCheck.charAt(i));

                    if (!matchesSimpleSyntaxRegex(nextTokenValue, expectedToken.getRegexType()))
                        continue;

                    isAnyTokenValid = true;
                    lastToken = expectedToken;
                    nextIndexLeap = 1;
                    break;
                }

                //if we can fall out of bounds
                if (expectedToken.getValue().length() + i > tokens.length)
                    break;

                UniqueToken nextToken = new UniqueToken(
                        textToCheck.substring(i, i + expectedToken.getValue().length()),
                        expectedToken.getId());

                if (expectedTokensGraph.hasEdgeConnecting(lastToken, nextToken)){
                    isAnyTokenValid = true;
                    lastToken = nextToken;
                    nextIndexLeap = nextToken.getValue().length();
                    break;
                }
            }

            if (!isAnyTokenValid)
                throw new InvalidSyntaxException(constructExceptionMessage(lastToken, expectedTokensSorted), i+1);
        }
    }

    private String constructExceptionMessage(UniqueToken node, List<UniqueToken> expectedTokens){
        StringBuilder exceptionMessageBuilder = new StringBuilder();

        if (expectedTokens.size() == 1) {
            for (var expectedToken : expectedTokens){
                String msg = expectedTokensGraph.edgeValue(node, expectedToken).get();
                exceptionMessageBuilder.append(msg);
            }
            return exceptionMessageBuilder.toString();
        }

        for (var expectedToken : expectedTokens){
            String msg = expectedTokensGraph.edgeValue(node, expectedToken).get();

            if (!(expectedTokens.indexOf(expectedToken) == 0))
                msg = msg.replace("Error in syntax", "");

            exceptionMessageBuilder.append(msg);

            if (!(expectedTokens.indexOf(expectedToken) == expectedTokens.size() - 1))
                exceptionMessageBuilder.append(" or ");
        };
        return exceptionMessageBuilder.toString();
    }

    private List<UniqueToken> getAdjacentSuccessors(UniqueToken node){
        var adjacentNodes = expectedTokensGraph.adjacentNodes(node);
        var successorsNodes = expectedTokensGraph.successors(node);
        return adjacentNodes.stream()
                .filter(successorsNodes::contains)
                .toList();
    }

    private boolean matchesSimpleSyntaxRegex(String text, SimpleSyntaxRegexType syntaxRegexType){
        return switch (syntaxRegexType){
            case ANY_DIGIT -> text.matches("[0-9]");
            case ANY_LETTER -> text.matches("[a-z]|[A-Z]");
        };
    }


}