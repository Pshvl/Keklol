package operations;

import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedDifferentialOperatorTest {

    double [] xValuesTest = {0, 1, 2, 3, 4};
    double [] yValuesTest = {4, 5, 6, 7, 12};
    LinkedListTabulatedFunctionFactory testFact = new LinkedListTabulatedFunctionFactory();
    TabulatedDifferentialOperator testOper = new TabulatedDifferentialOperator(testFact);
    ArrayTabulatedFunction testFunc = new ArrayTabulatedFunction(xValuesTest, yValuesTest);
    @Test
    void deriveSynchronously() {
        TabulatedFunction difFunc = testOper.deriveSynchronously(testFunc);
        for(int i = 0; i < testFunc.getCount()-2; i++){
            assertEquals(1, difFunc.getY(i));
        }
        for(int i = 3; i < testFunc.getCount(); i++){
            assertEquals(5, difFunc.getY(i));
        }
    }
}