package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {
    @org.junit.jupiter.api.Test
    void apply() throws Exception {
        MathFunction test_func = new SqrFunction();
        double actual = test_func.apply(1.2);
        double expected = 1.44;
        assertEquals(actual, expected);
    }
}