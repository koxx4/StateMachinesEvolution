package org.koxx4.machine;

public class MealyEdge extends Edge{

    private final int outputValue;

    public MealyEdge(String name, State startingState, State endingState, int outputValue) {
        super(name, startingState, endingState);
        this.outputValue = outputValue;
    }


    public int getOutputValue() {
        return outputValue;
    }

}
