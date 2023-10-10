package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VarFunctionTest {

    @Test
    void apply() {
        MathFunction testFunc = new VarFunction();
        double actual = testFunc.apply(Math.PI);
        double expected = 1;
        assertEquals(expected, actual, 0.000001);
    }
}