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

    @Test
    void testToString() {
        String expected = "(1.0; 16.0), (5.0; 4.0), (10.0; 19.0), (0.0; 2.0)";
        assertEquals(expected, Q.toString());
    }

    @Test
    void testEquals() {
        LinkedListTabulatedFunction.Node node1 = new LinkedListTabulatedFunction.Node(51, 7);
        LinkedListTabulatedFunction.Node node2 = new LinkedListTabulatedFunction.Node(51, 7);
        assertTrue(node1.equals(node2));
    }

    @Test
    void testHashCode() {
        LinkedListTabulatedFunction.Node node1 = new LinkedListTabulatedFunction.Node(123.0, 456.0);
        LinkedListTabulatedFunction.Node node2 = new LinkedListTabulatedFunction.Node(123.0, 456.0);
        LinkedListTabulatedFunction.Node node3 = new LinkedListTabulatedFunction.Node(456.0, 123.0);

        assertEquals(node1.hashCode(), node2.hashCode());
        assertNotEquals(node1.hashCode(), node3.hashCode());
    }

    @Test
    void testClone() {
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(111, 111);
        Object copy = node.clone();
        assertEquals(node, copy);
    }

    @Test
    public void testHashCodeLinkedList() {
        LinkedListTabulatedFunction functionCopy = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(Q.hashCode(), functionCopy.hashCode());
        assertNotEquals(10, Q.hashCode());
    }

    @Test
    public void testEqualsLinkedList() {
        LinkedListTabulatedFunction functionCopy = new LinkedListTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction functionNotCopy = new LinkedListTabulatedFunction(new double[]{1.5, 2.33, -5.0}, new double[]{-1.5, -2.33, 5.0});
        assertTrue(Q.equals(functionCopy));
        assertFalse(Q.equals(functionNotCopy));

    }

    @Test
    public void testCloneLinkedList() {
        assertEquals(Q, ((LinkedListTabulatedFunction) Q).clone());
    }


}