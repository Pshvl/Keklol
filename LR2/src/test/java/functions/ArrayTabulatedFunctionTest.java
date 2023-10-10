package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {

    double [] xValuesTest = {3.3, 4.3, 5.9, 9, 11.32};
    double [] yValuesTest = {36, 2.2, 0.1, 42, 6.54};
    ArrayTabulatedFunction testArray = new ArrayTabulatedFunction(xValuesTest, yValuesTest);

    @Test
    void getCount() {
        int actual = testArray.getCount();
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    void getX() {
        double actual = testArray.getX(2);
        double expected = 5.9;
        assertEquals(expected, actual);
    }

    @Test
    void setY() {
        testArray.setY(3,4.13);
        double actual = testArray.getY(3);
        double expected = 4.13;
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        double actual = testArray.getY(3);
        double expected = 42;
        assertEquals(expected, actual);
    }

    @Test
    void leftBound() {
        double actual = testArray.leftBound();
        double expected = 3.3;
        assertEquals(expected, actual);
    }

    @Test
    void rightBound() {
        double actual = testArray.rightBound();
        double expected = 11.32;
        assertEquals(expected, actual);
    }

    @Test
    void indexOfX() {
        double actual = testArray.indexOfX(5.9);
        double expected = 2;
        assertEquals(expected, actual);

        actual = testArray.indexOfX(1122);
        expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    void indexOfY() {
        double actual = testArray.indexOfY(6.54);
        double expected = 4;
        assertEquals(expected, actual);

        actual = testArray.indexOfY(1122);
        expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    void floorIndexOfX() {
        double actual = testArray.floorIndexOfX(2);
        double expected = 0;
        assertEquals(expected, actual);

        actual = testArray.floorIndexOfX(1122);
        expected = 5;
        assertEquals(expected, actual);

        actual = testArray.floorIndexOfX(8);
        expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    void extrapolateLeft() {
        double actual = testArray.extrapolateLeft(1.5);
        double expected = 96.84;
        assertEquals(expected, actual, 0.000001);
    }

    @Test
    void extrapolateRight() {
        double actual = testArray.extrapolateRight(12.8);
        double expected = 42 - 134.748/2.32;
        assertEquals(expected, actual, 0.000001);
    }

    @Test
    void interpolate() {
        double leftX = testArray.getX(testArray.floorIndexOfX(6)-1);
        double rightX = testArray.getX(testArray.floorIndexOfX(6));
        double leftY = testArray.getY(testArray.floorIndexOfX(6)-1);
        double rightY = testArray.getY(testArray.floorIndexOfX(6));
        double actual = testArray.interpolate(6, leftX, rightX, leftY, rightY);
        double expected = 0.1 + 4.19/3.1;
        assertEquals(expected, actual, 0.000001);
    }

    @Test
    void andThen(){

        MathFunction testFunc_1 = testArray;
        MathFunction testFunc_2 = new SqrFunction();
        CompositeFunction testCompFunc = testFunc_2.andThen(testFunc_1);
        double actual = testCompFunc.apply(4);
        double expected = Math.pow(2.2,2);
        assertEquals(expected, actual);


        double [] xValuesTest = {3.14, 6, 13, 13.4};
        double [] yValuesTest = {-3, 12, 413, 5.4};
        testFunc_2 = new ArrayTabulatedFunction(xValuesTest, yValuesTest);
        testCompFunc = testFunc_1.andThen(testFunc_2);
        actual = testCompFunc.apply(13.4);
        expected = 0.1;
        assertEquals(expected,actual,0.0001);

    }
}