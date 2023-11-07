package operations;

import functions.MathFunction;
import functions.SqrFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RightSteppingDifferentialOperatorTest {

    @Test
    void derive() {
        SqrFunction sqrFunc = new SqrFunction();
        RightSteppingDifferentialOperator diffOp = new RightSteppingDifferentialOperator(0.5);
        MathFunction dif = diffOp.derive(sqrFunc);
        assertEquals(3.5, dif.apply(1.5), 0.00001);

        assertThrowsExactly(IllegalArgumentException.class, ()-> new RightSteppingDifferentialOperator(0));
    }
}