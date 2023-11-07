package operations;

import exceptions.InterpolationException;
import functions.MathFunction;
import functions.SqrFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeftSteppingDifferentialOperatorTest {

    @Test
    void derive() {
        SqrFunction sqrFunc = new SqrFunction();
        LeftSteppingDifferentialOperator diffOp = new LeftSteppingDifferentialOperator(2);
        MathFunction dif = diffOp.derive(sqrFunc);
        assertEquals(4, dif.apply(3));

        assertThrowsExactly(IllegalArgumentException.class, ()-> new LeftSteppingDifferentialOperator(-1));
    }
}