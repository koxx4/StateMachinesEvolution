package org.koxx4.machine;

public class MealyEdge extends Edge{

    private final int outputValue;

    public MealyEdge(State startingState, State endingState, int outputValue) {
        super(startingState, endingState);
        this.outputValue = outputValue;
    }

    public int getOutputValue() {
        return outputValue;
    }
}
