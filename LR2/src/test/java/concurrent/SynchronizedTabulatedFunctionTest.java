package concurrent;

import functions.ArrayTabulatedFunction;
import functions.Point;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class SynchronizedTabulatedFunctionTest {

    double [] xValuesTest = {3.3, 4.3, 5.9, 9, 11.32};
    double [] yValuesTest = {36, 2.2, 0.1, 42, 6.54};
    ArrayTabulatedFunction testArray = new ArrayTabulatedFunction(xValuesTest, yValuesTest);
    SynchronizedTabulatedFunction syncFunc = new SynchronizedTabulatedFunction(testArray);

    @Test
    void apply() {
        assertEquals(0.1, syncFunc.apply(5.9));
    }

    @Test
    void getCount() {
        assertEquals(5, syncFunc.getCount());
    }

    @Test
    void getX() {
        assertEquals(4.3, syncFunc.getX(1));
    }

    @Test
    void getY() {
        assertEquals(2.2, syncFunc.getY(1));
    }

    @Test
    void setY() {
        syncFunc.setY(1, 9);
        assertEquals(9, syncFunc.getY(1));
    }

    @Test
    void indexOfX() {
        assertEquals(1, syncFunc.indexOfX(4.3));
    }

    @Test
    void indexOfY() {
        assertEquals(1, syncFunc.indexOfY(2.2));
    }

    @Test
    void leftBound() {
        assertEquals(3.3, syncFunc.leftBound());
    }

    @Test
    void rightBound() {
        assertEquals(11.32, syncFunc.rightBound());
    }

    @Test
    void iterator() {
        Iterator<Point> iterator_1 = syncFunc.iterator();
        int i = 0;
        for (Point point : syncFunc){
            point = iterator_1.next();
            assertEquals(syncFunc.getX(i), point.x);
            assertEquals(syncFunc.getY(i), point.y);
            i++;
        }

        Iterator<Point> iterator_2 = syncFunc.iterator();
        i = 0;
        while (iterator_2.hasNext()) {
            Point point = iterator_2.next();
            assertEquals(syncFunc.getX(i), point.x);
            assertEquals(syncFunc.getY(i), point.y);
            i++;
        }
    }
}