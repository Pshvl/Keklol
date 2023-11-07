package operations;

import functions.MathFunction;
import functions.SqrFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MiddleSteppingDifferentialOperatorTest {

    @Test
    void derive() {
        SqrFunction sqrFunc = new SqrFunction();
        MiddleSteppingDifferentialOperator diffOp = new MiddleSteppingDifferentialOperator(0.1);
        MathFunction dif = diffOp.derive(sqrFunc);
        assertEquals(8.6, dif.apply(4.3), 0.00001);

        assertThrowsExactly(IllegalArgumentException.class, ()-> new MiddleSteppingDifferentialOperator(1.0/0.0));
    }
}