package org.koxx4.machine;

public class MealyEdge extends Edge{

    private final int outputValue;

    public MealyEdge(String name, int index, int outputValue) {
        super(name, index);
        this.outputValue = outputValue;
    }

    public int getOutputValue() {
        return outputValue;
    }

    @Override
    public String toString() {
        return outputValue + getName() + getIndex();
    }
}
