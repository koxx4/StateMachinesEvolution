package org.koxx4.syntax;

import com.google.common.graph.ImmutableValueGraph;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

public class SimpleSyntaxValidatorBuilder {
    private final MutableValueGraph<UniqueToken, String> tokenGraph;
    private UniqueToken lastTokenNode;
    private UniqueToken currentTokenNode;
    private static final int nonUniqueId = -1;
    private static final UniqueToken ENTRY_POINT_TOKEN = new UniqueToken("*", nonUniqueId);

    public SimpleSyntaxValidatorBuilder() {
        this.tokenGraph = ValueGraphBuilder.directed().allowsSelfLoops(true).build();
        this.initialize();
    }

    private void initialize(){
        lastTokenNode = ENTRY_POINT_TOKEN;
        currentTokenNode = ENTRY_POINT_TOKEN;
    }

    public SimpleSyntaxValidatorBuilder nextTokenShouldBe(String text){
        nextTokenShouldBe(new UniqueToken(text, nonUniqueId));
        return this;
    }

    public SimpleSyntaxValidatorBuilder nextTokenShouldBe(SimpleSyntaxRegexType type){
        nextTokenShouldBe(new UniqueToken(type, nonUniqueId));
        return this;
    }

    public SimpleSyntaxValidatorBuilder nextTokenShouldBeDistinctive(String text, int uniqueId){
        nextTokenShouldBe(new UniqueToken(text, uniqueId));
        return this;
    }

    public SimpleSyntaxValidatorBuilder nextTokenShouldBeDistinctive(SimpleSyntaxRegexType type, int uniqueId){
        nextTokenShouldBe(new UniqueToken(type, uniqueId));
        return this;
    }

    public SimpleSyntaxValidatorBuilder or(String text){
        or(new UniqueToken(text, nonUniqueId));
        return this;
    }

    public SimpleSyntaxValidatorBuilder or(SimpleSyntaxRegexType type){
        orDistinctive(type, nonUniqueId);
        return this;
    }

    public SimpleSyntaxValidatorBuilder orDistinctive(String text, int uniqueId){
        or(new UniqueToken(text, uniqueId));
        return this;
    }

    public SimpleSyntaxValidatorBuilder orDistinctive(SimpleSyntaxRegexType type, int uniqueId){
        or(new UniqueToken(type, uniqueId));
        return this;
    }

    private void nextTokenShouldBe(UniqueToken token){
        tokenGraph.putEdgeValue(
                currentTokenNode,
                token,
                String.format("Error in syntax, expected '%s' after '%s'", token, currentTokenNode));

        lastTokenNode = currentTokenNode;
        currentTokenNode = token;
    }

    private void or(UniqueToken token){
        tokenGraph.putEdgeValue(
                lastTokenNode, token,
                String.format("Error in syntax, expected '%s' after '%s'", token, lastTokenNode));
        currentTokenNode = token;
    }

    public SimpleSyntaxValidatorBuilder withExceptionMessage(String msg){
        tokenGraph.putEdgeValue(lastTokenNode, currentTokenNode, msg);
        return this;
    }

    public SimpleSyntaxValidatorBuilder selectTokenTransition(String tokenBefore, String tokenToSelect){
        selectTokenTransition(new UniqueToken(tokenBefore, nonUniqueId), new UniqueToken(tokenToSelect, nonUniqueId));
        return this;
    }

    public SimpleSyntaxValidatorBuilder selectTokenTransition(SimpleSyntaxRegexType typeBefore,
                                                              SimpleSyntaxRegexType typeToSelect){
        selectTokenTransition(new UniqueToken(typeBefore, nonUniqueId), new UniqueToken(typeToSelect, nonUniqueId));
        return this;
    }

    public SimpleSyntaxValidatorBuilder selectDistinctiveTokenTransition(String tokenBefore, int id1,
                                                                         String tokenToSelect, int id2){
        selectTokenTransition(new UniqueToken(tokenBefore, id1), new UniqueToken(tokenToSelect, id2));
        return this;
    }

    public SimpleSyntaxValidatorBuilder selectDistinctiveTokenTransition(SimpleSyntaxRegexType typeBefore, int id1,
                                                                         SimpleSyntaxRegexType typeToSelect, int id2){
        selectTokenTransition(new UniqueToken(typeBefore, id1), new UniqueToken(typeToSelect, id2));
        return this;
    }

    private void selectTokenTransition(UniqueToken tokenBefore, UniqueToken tokenToSelect){
        if (!tokenGraph.hasEdgeConnecting(tokenBefore, tokenToSelect))
            throw new IllegalArgumentException(
                    "There are no such tokens (yet), or connection between them does not exist!");

        this.lastTokenNode = tokenBefore;
        this.currentTokenNode = tokenToSelect;
    }

    public SyntaxValidator build() {
        return new SimpleSyntaxValidator(
                ImmutableValueGraph.copyOf(tokenGraph),
                ENTRY_POINT_TOKEN);
    }

}


