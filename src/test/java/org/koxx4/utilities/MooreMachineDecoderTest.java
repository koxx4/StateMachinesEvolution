package org.koxx4.utilities;

import org.junit.Test;
import org.koxx4.decoding.MooreMachineDecoder;
import static org.junit.Assert.*;

public class MooreMachineDecoderTest {

    @Test
    public void decodeFromEquation_TestStatesCount() {
        MooreMachineDecoder decoder = new MooreMachineDecoder();
        try {
            var m = decoder.decodeFromEquation("(s0(x0s1(x1s1,x0s0),x0s0))");
            assertEquals(2, m.getGraph().nodes().size());

            var m2 = decoder.decodeFromEquation("(s0(x0s1(x1s1,x0s2(x1s0,x0s1)),x0s0))");
            assertEquals(3, m2.getGraph().nodes().size());

            var m3 = decoder.decodeFromEquation("(s0(x0s1(x1s1,x0s2(x1s0,x0s1)),x0s3(x1s2,x0s3)))");
            assertEquals(4, m3.getGraph().nodes().size());
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    @Test
    public void decodeFromNaturalExpression() {
    }
}