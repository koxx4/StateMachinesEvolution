package org.koxx4.syntax;

public interface SyntaxValidator {
    void validate(String textToCheck) throws InvalidSyntaxException;
}
