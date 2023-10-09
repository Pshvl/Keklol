package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantFunctionTest {

        MathFunction e = new ConstantFunction(2.71);
        MathFunction test_zeroFunc = new ZeroFunction();
        MathFunction test_unitFunc = new UnitFunction();

    @Test
    void apply() throws Exception {

        double actual_const = e.apply(-42);
        double expected_const = 2.71;
        assertEquals(expected_const, actual_const);

        double actual_zero = test_zeroFunc.apply(42.3);
        double expected_zero = 0;
        assertEquals(expected_zero, actual_zero);

        double actual_unit = test_unitFunc.apply(0);
        double expected_unit = 1;
        assertEquals(expected_unit, actual_unit);
    }

}