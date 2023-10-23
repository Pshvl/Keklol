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
    void andThen() {

        MathFunction actual_sqrVarFunc = sqrFunc.andThen(varFunc); // (1 + tg^2(x))^2
        double expected_sqrVarFunc = 4;
        assertEquals(expected_sqrVarFunc, actual_sqrVarFunc.apply(Math.PI/4), 0.000001);

        MathFunction actual_sqrCosFunc = sqrFunc.andThen(cosFunc); // cos^2(2x)
        double expected_sqrCosFunc = 1;
        assertEquals(expected_sqrCosFunc, actual_sqrCosFunc.apply(0), 0.000001);

        MathFunction actual_sinZeroFunc = cosFunc.andThen(zeroFunc); // cos(2*0)
        double expected_sinZeroFunc = 1;
        assertEquals(expected_sinZeroFunc, actual_sinZeroFunc.apply(42), 0.000001);

        MathFunction actual_zeroUnitFunc = zeroFunc.andThen(unitFunc); // val -> 1 -> 0
        double expected_zeroUnitFunc = 0;
        assertEquals(expected_zeroUnitFunc, actual_zeroUnitFunc.apply(42));

        MathFunction actual_sqrCosZeroFunc = sqrFunc.andThen(cosFunc).andThen(zeroFunc); // val -> 0 -> cos^2(2*0)
        double expected_sqrCosZeroFunc = 1;
        assertEquals(expected_sqrCosZeroFunc, actual_sqrCosZeroFunc.apply(231));
    }


    @Test
    public void testApply1(){
        CompositeFunction Q = new CompositeFunction(sqrFunc,cosFunc);
        assertEquals(1.0, Q.apply(0), 0.000001);}

    @Test public void testApply2(){
        CompositeFunction Q = new CompositeFunction(cosFunc,sqrFunc);
        assertEquals(1.0, Q.apply(Math.PI), 0.00001);

    }
    @Test public void testApply3(){
        CompositeFunction Q = new CompositeFunction(cosFunc,zeroFunc);
        assertEquals(0.0, Q.apply(Math.PI/4), 0.00001);

    }

    @Test public void testApply4(){
        CompositeFunction Q = new CompositeFunction(cosFunc,unitFunc);
        assertEquals(1.0, Q.apply(Math.PI/4), 0.00001);

    }
    @Test public void testApply5(){
        CompositeFunction Q = new CompositeFunction(cosFunc,varFunc);
        CompositeFunction QQ = new CompositeFunction(Q,unitFunc);
        assertEquals(1.0, QQ.apply(Math.PI), 0.00001);

    }
}