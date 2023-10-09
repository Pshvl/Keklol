package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeFunctionTest {

    MathFunction sqrFunc = new SqrFunction();
    MathFunction varFunc = new VarFunction();
    MathFunction cosFunc = new CosFunction();
    MathFunction unitFunc = new UnitFunction();
    MathFunction zeroFunc = new ZeroFunction();


    @Test
    void andThen() throws Exception {

        MathFunction actual_sqrVarFunc = sqrFunc.andThen(varFunc);
        double expected_sqrVarFunc = 4;
        assertEquals(expected_sqrVarFunc, actual_sqrVarFunc.apply(Math.PI/4), 0.000001);

        MathFunction actual_sqrSinFunc = sqrFunc.andThen(cosFunc);
        double expected_sqrSinFunc = 1;
        assertEquals(expected_sqrSinFunc, actual_sqrSinFunc.apply(0), 0.000001);

        MathFunction actual_sinZeroFunc = cosFunc.andThen(zeroFunc);
        double expected_sinZeroFunc = 1;
        assertEquals(expected_sinZeroFunc, actual_sinZeroFunc.apply(42), 0.000001);

        MathFunction actual_zeroUnitFunc = zeroFunc.andThen(unitFunc);
        double expected_zeroUnitFunc = 0;
        assertEquals(expected_zeroUnitFunc, actual_zeroUnitFunc.apply(42));
    }

}