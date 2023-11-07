package operations;

import functions.ArrayTabulatedFunction;
import functions.Point;
import functions.TabulatedFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionOperationServiceTest {

    @Test
    void asPoints() {
        double [] xValuesTest = {3.3, 4.3, 5.9, 9, 11.32};
        double [] yValuesTest = {36, 2.2, 0.1, 42, 6.54};
        ArrayTabulatedFunction testArray = new ArrayTabulatedFunction(xValuesTest, yValuesTest);

        Point[] points = TabulatedFunctionOperationService.asPoints(testArray);

        for (int i = 0; i < testArray.getCount(); i++) {
            assertEquals(testArray.getX(i), points[i].x);
            assertEquals(testArray.getY(i), points[i].y);
        }
    }
}