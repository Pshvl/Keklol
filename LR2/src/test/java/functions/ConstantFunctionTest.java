package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantFunctionTest {

        MathFunction e = new ConstantFunction(2.71);
        MathFunction testZeroFunc = new ZeroFunction();
        MathFunction testUnitFunc = new UnitFunction();

    @Test
    void apply() {

        double actual_const = e.apply(-42);
        double expected_const = 2.71;
        assertEquals(expected_const, actual_const);

        double actual_zero = testZeroFunc.apply(42.3);
        double expected_zero = 0;
        assertEquals(expected_zero, actual_zero);

        double actual_unit = testUnitFunc.apply(0);
        double expected_unit = 1;
        assertEquals(expected_unit, actual_unit);
    }

}