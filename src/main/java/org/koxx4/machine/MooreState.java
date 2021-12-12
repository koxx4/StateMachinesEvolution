package org.koxx4.machine;

public class MooreState extends State{

    private final int outputValue;

    public MooreState(String name, int outputValue) {
        super(name);
        this.outputValue = outputValue;
    }

    public int getOutputValue() {
        return outputValue;
    }
}
