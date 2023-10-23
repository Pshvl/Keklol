package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionTest {

    private final double[] xValues = {1.0, 5.0, 10.0, 0.0};
    private final double[] yValues = {16.0, 4.0, 19.0, 2.0};
    private final TabulatedFunction Q = new LinkedListTabulatedFunction(xValues, yValues);


    @Test
    void getCount() {
        assertEquals(4, Q.getCount(), 0.0001);
    }

    @Test
    void leftBound() {
        assertEquals(1.0, Q.leftBound(), 0.0001);
    }

    @Test
    void rightBound() {
        assertEquals(0.0, Q.rightBound(), 0.0001);
    }

    @Test
    void getX() {
        assertEquals(5.0, Q.getX(1), 0.0001);
    }

    @Test
    void getY() {
        assertEquals(4.0, Q.getY(1), 0.0001);
    }

    @Test
    void setY() {
        Q.setY(2, 333.0);
        assertEquals(333.0, Q.getY(2), 0.0001);
    }


    @Test
    void indexOfX() {
        assertEquals(2, Q.indexOfX(10.0), 0.0001);
    }

    @Test
    void indexOfY() {
        assertEquals(2, Q.indexOfY(19.0), 0.0001);
    }

    @Test
    void floorIndexOfX() {
        assertEquals(1, ((LinkedListTabulatedFunction) Q).floorIndexOfX(1.5), 0.0001);
    }

    @Test
    void extrapolateLeft() {
        assertEquals(16.0, ((LinkedListTabulatedFunction) Q).extrapolateLeft(1.0), 0.0001);

    }

    @Test
    void extrapolateRight() {
        assertEquals(3.7, ((LinkedListTabulatedFunction) Q).extrapolateRight(1.0), 0.0001);
    }
}