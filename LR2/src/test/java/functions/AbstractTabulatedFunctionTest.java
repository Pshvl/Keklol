package functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class AbstractTabulatedFunctionTest {
    double[] xValuesTest = {3.3, 4.3, 5.9, 9, 11.32};
    double[] yValuesTest = {36, 2.2, 0.1, 42, 6.54};
    double[] zValuesTest = {36, 2.2, 0.1, 42};

    @Test
    void checkLengthIsTheSame() {
        AbstractTabulatedFunction.checkLengthIsTheSame(xValuesTest, yValuesTest);

        assertThrowsExactly(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(xValuesTest, zValuesTest));
    }

    @Test
    void checkSorted() {
        AbstractTabulatedFunction.checkSorted(xValuesTest);

        assertThrowsExactly(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(yValuesTest));
    }

   /* @Test
    public void toString() {
        double[] xVal = new double[]{1, 2, 3, 4, 5};
        double[] yVal = new double[]{1, 2, 3, 4, 5};
        LinkedListTabulatedFunction listF = new LinkedListTabulatedFunction(xVal, yVal);
        assertEquals(listF.toString(), "LinkedListTabulatedFunction count = 5\n[1.0; 1.0]\n[2.0; 2.0]\n[3.0; 3.0]\n[4.0; 4.0]\n[5.0; 5.0]");
    }*/
}

