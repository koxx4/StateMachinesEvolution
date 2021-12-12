package org.koxx4.utilities;

import org.junit.Test;

import static org.junit.Assert.*;

public class MooreMachineDecoderTest {

    @Test
    public void decodeFromEquation() {
        MooreMachineDecoder decoder = new MooreMachineDecoder();
        try {
            decoder.decodeFromEquation("(s02(x11s033(x1s2,x1s2)(,x1s1(x0s0,x1s0)))");
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void decodeFromNaturalExpression() {
    }
}