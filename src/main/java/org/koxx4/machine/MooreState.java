package org.koxx4.machine;

public class MooreState extends State{

    private final int outputValue;

    public MooreState(int outputValue) {
        super();
        this.outputValue = outputValue;
    }

    public int getOutputValue() {
        return outputValue;
    }
}
