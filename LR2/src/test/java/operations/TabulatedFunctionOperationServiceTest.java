package operations;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionOperationServiceTest {
    double[] xValuesTest1 = {2.5, 5, 12, 44};
    double[] yValuesTest1 = {12.3, -43, 0.5, 0};
    double[] xValuesTest2 = {-2, 0, 1.3, 6};
    double[] yValuesTest2 = {-12.3, 22, 9.5, 16};
    TabulatedFunctionOperationService operation = new TabulatedFunctionOperationService();
    TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
    TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
    TabulatedFunction func1 = arrayFactory.create(xValuesTest1, yValuesTest1);
    TabulatedFunction func2 = linkedListFactory.create(xValuesTest1, yValuesTest1);
    TabulatedFunction func3 = arrayFactory.create(xValuesTest2, yValuesTest2);
    TabulatedFunction func4 = linkedListFactory.create(xValuesTest2, yValuesTest2);


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
    @Test
    void SetGetFactory() {
        operation.SetFactory(arrayFactory);
        Assertions.assertEquals(arrayFactory.getClass(), operation.GetFactory().getClass());

        operation.SetFactory(linkedListFactory);
        Assertions.assertEquals(linkedListFactory.getClass(), operation.GetFactory().getClass());
    }
    @Test
    void Addition() {
        TabulatedFunction resultArr = operation.Addition(func1, func3);;
        TabulatedFunction resultLink = operation.Addition(func2, func4);
        TabulatedFunction resultDiff = operation.Addition(func1, func2);

        for (int i = 0; i < resultArr.getCount(); i++) {
            Assertions.assertEquals(func1.getY(i) + func3.getY(i), resultArr.getY(i));
        }
        for (int i = 0; i < resultLink.getCount(); i++) {
            Assertions.assertEquals(func2.getY(i) + func4.getY(i), resultLink.getY(i));
        }
        for (int i = 0; i < resultDiff.getCount(); i++) {
            Assertions.assertEquals(func1.getY(i) + func2.getY(i), resultDiff.getY(i));
        }
    }
    @Test
    void Subtraction() {
        TabulatedFunction resultArr = operation.Subtraction(func1, func3);;
        TabulatedFunction resultLink = operation.Subtraction(func2, func4);
        TabulatedFunction resultDiff = operation.Subtraction(func1, func2);

        for (int i = 0; i < resultArr.getCount(); i++) {
            Assertions.assertEquals(func1.getY(i) - func3.getY(i), resultArr.getY(i));
        }
        for (int i = 0; i < resultLink.getCount(); i++) {
            Assertions.assertEquals(func2.getY(i) - func4.getY(i), resultLink.getY(i));
        }
        for (int i = 0; i < resultDiff.getCount(); i++) {
            Assertions.assertEquals(func1.getY(i) - func2.getY(i), resultDiff.getY(i));
        }
    }
}