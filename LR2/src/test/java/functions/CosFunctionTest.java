package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CosFunctionTest {
    private final CosFunction cosFunc = new CosFunction();

    @Test
    public void apply() {

        assertEquals(cosFunc.apply(Math.PI), 1);
        assertEquals(cosFunc.apply(Math.PI/2), -1);
        assertEquals(3*cosFunc.apply(Math.PI/2), -3);
        assertEquals(0.0, cosFunc.apply(Math.PI/4), 0.00001);




    }
}