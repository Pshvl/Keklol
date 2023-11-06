package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {

    double [] xValuesTest = {3.3, 4.3, 5.9, 9, 11.32};
    double [] yValuesTest = {36, 2.2, 0.1, 42, 6.54};
    ArrayTabulatedFunction testArray = new ArrayTabulatedFunction(xValuesTest, yValuesTest);
    MathFunction source = new SqrFunction();
    ArrayTabulatedFunction testArray_2 = new ArrayTabulatedFunction(source, 2, 10, 5);

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
        double actual = testArray.floorIndexOfX(2); // 1 вариант: значение меньше левой границы
        double expected = 0;
        assertEquals(expected, actual);

        actual = testArray.floorIndexOfX(1122); // 2 вариант: значение больше правой границы
        expected = 5;
        assertEquals(expected, actual);

        actual = testArray.floorIndexOfX(8); // 3 вариант: значение находится в границах
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
        double leftX = testArray.getX(testArray.floorIndexOfX(6)-1); // x слева от заданного значения
        double rightX = testArray.getX(testArray.floorIndexOfX(6)); // x справа от заданного значения
        double leftY = testArray.getY(testArray.floorIndexOfX(6)-1); // y слева от заданного значения
        double rightY = testArray.getY(testArray.floorIndexOfX(6)); // y справа от заданного значения
        double actual = testArray.interpolate(6, leftX, rightX, leftY, rightY);
        double expected = 0.1 + 4.19/3.1;
        assertEquals(expected, actual, 0.000001);
    }

    @Test
    void andThen(){

        /* Значение функции^2 */
        MathFunction testFunc_1 = testArray;
        MathFunction testFunc_2 = new SqrFunction();
        CompositeFunction testCompFunc = testFunc_2.andThen(testFunc_1);
        double actual = testCompFunc.apply(4);
        double expected = Math.pow(2.2,2);
        assertEquals(expected, actual);


        /* По значению первой функции вычисляем значение второй */
        double [] xValuesTest = {3.14, 6, 13, 13.4};
        double [] yValuesTest = {-3, 12, 413, 5.4};
        testFunc_2 = new ArrayTabulatedFunction(xValuesTest, yValuesTest);
        testCompFunc = testFunc_1.andThen(testFunc_2);
        actual = testCompFunc.apply(13.4);
        expected = 0.1;
        assertEquals(expected,actual,0.0001);

    }

    @Test
    void testToString() {
        String expected = "{(3.3,36.0)(4.3,2.2)(5.9,0.1)(9.0,42.0)(11.32,6.54)}";
        String actual = testArray.toString();
        assertEquals(expected, actual);

        expected = "{(2.0,4.0)(4.0,16.0)(6.0,36.0)(8.0,64.0)(10.0,100.0)}";
        actual = testArray_2.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testEquals() {
        ArrayTabulatedFunction testArray_2 = new ArrayTabulatedFunction(xValuesTest, yValuesTest); // точно такой же элемент класса как и testArray

        double [] xValuesTest_2 = {3.3, 4.3, 5.9, 9, 15};
        double [] yValuesTest_2 = {36, 2.2, 0.1, 42, 9};
        ArrayTabulatedFunction testArray_3 = new ArrayTabulatedFunction(xValuesTest_2, yValuesTest_2); // отличие от testArray только на последние элементы массивов, для лучшей проверки

        UnitFunction testFunc = new UnitFunction();

        assertTrue(testArray.equals(testArray_2));
        assertFalse(testArray.equals(testArray_3));
        assertFalse(testArray.equals(testFunc));

    }

    @Test
    void testHashCode() {
        ArrayTabulatedFunction testArray_2 = new ArrayTabulatedFunction(xValuesTest, yValuesTest); // точно такой же элемент класса как и testArray
        double [] xValuesTest = {3, 4, 5, 9, 11};
        double [] yValuesTest = {36, 2, 0, 42, 6};
        ArrayTabulatedFunction testArray_3 = new ArrayTabulatedFunction(xValuesTest, yValuesTest); // такой же элемент класса как и testArray, но с "целыми" значениями
        double [] xValuesTest_2 = {8.2, 4.3, 5.9, 9.21, 15};
        double [] yValuesTest_2 = {36, 3.2, 0.1, 42, 9};
        ArrayTabulatedFunction testArray_4 = new ArrayTabulatedFunction(xValuesTest_2, yValuesTest_2);

        assertNotEquals(testArray.hashCode(), testArray_4.hashCode());
        assertEquals(testArray.hashCode(), testArray_2.hashCode());
        assertEquals(testArray.hashCode(), testArray_3.hashCode());
    }

    @Test
    void testClone() {
        ArrayTabulatedFunction testArray_2 = (ArrayTabulatedFunction) testArray.clone();
        assertEquals(testArray, testArray_2);
    }
}