package functions;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {
    @org.junit.jupiter.api.Test
    void apply() {
        MathFunction testFunc = new SqrFunction();
        double actual = testFunc.apply(1.2);
        double expected = 1.44;
        assertEquals(expected, actual);
    }
}