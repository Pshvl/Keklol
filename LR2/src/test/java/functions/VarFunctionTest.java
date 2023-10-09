package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VarFunctionTest {

    @Test
    void apply() throws Exception {
        MathFunction test_func = new VarFunction();
        double actual = test_func.apply(Math.PI);
        double expected = 1;
        assertEquals(expected, actual);
    }
}