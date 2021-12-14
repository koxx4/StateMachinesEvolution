package org.koxx4.syntax;

public class InvalidSyntaxException extends Exception{

    public InvalidSyntaxException() {
        super("Provided text has an invalid syntax");
    }

    public InvalidSyntaxException(String message) {
        super(message);
    }

    public InvalidSyntaxException(String message, int positionInText) {
        super(message + ". Position: " + positionInText);
    }
}
