package functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTabulatedFunctionTest {
    double [] xValuesTest = {3.3, 4.3, 5.9, 9, 11.32};
    double [] yValuesTest = {36, 2.2, 0.1, 42, 6.54};
    double [] zValuesTest = {36, 2.2, 0.1, 42};
    @Test
    void checkLengthIsTheSame() {
        AbstractTabulatedFunction.checkLengthIsTheSame(xValuesTest, yValuesTest);

        assertThrowsExactly(DifferentLengthOfArraysException.class, ()-> AbstractTabulatedFunction.checkLengthIsTheSame(xValuesTest, zValuesTest));
    }

    @Test
    void checkSorted() {
        AbstractTabulatedFunction.checkSorted(xValuesTest);

        assertThrowsExactly(ArrayIsNotSortedException.class, ()-> AbstractTabulatedFunction.checkSorted(yValuesTest));
    }
}