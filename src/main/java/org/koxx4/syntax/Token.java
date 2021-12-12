package org.koxx4.syntax;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Token implements Comparable<Token>{
    private final String value;
    private final SimpleSyntaxRegexType regexType;

    public Token(@NonNull String value) {
        this.value = value;
        this.regexType = null;
    }

    public Token(SimpleSyntaxRegexType regexType){
        this.value = "";
        this.regexType = regexType;
    }

    public String getValue() {
        return value;
    }

    public int hashCode(){
        return value.hashCode();
    }

    public SimpleSyntaxRegexType getRegexType() {
        return regexType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (!getValue().equals(token.getValue()))
            return false;

        return getRegexType() == token.getRegexType();
    }

    @Override
    public String toString() {
        if(regexType != null)
            return regexType.toString();

        return value;
    }

    @Override
    public int compareTo(Token token) {
       return this.value.compareTo(token.getValue());
    }
}
