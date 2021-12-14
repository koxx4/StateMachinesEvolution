package org.koxx4.machine;

import java.util.Objects;

public class MooreState extends State{

    private final int outputValue;

    public MooreState(String name,int index, int outputValue) {
        super(name, index);
        this.outputValue = outputValue;
    }

    public int getOutputValue() {
        return outputValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MooreState that = (MooreState) o;
        return getOutputValue() == that.getOutputValue();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
