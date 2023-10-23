package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IdentityFunctionTest {

    @Test
    public void apply() {
        IdentityFunction Q = new IdentityFunction();
        assertEquals(Q.apply(1), 1);
        assertEquals(Q.apply(-4), -4);
        assertEquals(Q.apply(0.8975), 0.8975);
    }

    @Test
    public void testToString() {
        IdentityFunction func = new IdentityFunction();
        assertEquals(func.toString(), "IdentityFunction{}");
    }

    @Test
    public void testEquals() {
        IdentityFunction idenFunc_1 = new IdentityFunction();
        IdentityFunction idenFunc_2 = new IdentityFunction();
        ZeroFunction zeroFunc = new ZeroFunction();
        assertTrue(idenFunc_1.equals(idenFunc_2));
        assertFalse(idenFunc_1.equals(zeroFunc));
    }

    @Test
    public void testHashCode() {
        IdentityFunction idenFunc_1 = new IdentityFunction();
        IdentityFunction idenFunc_2 = new IdentityFunction();
        assertNotEquals(idenFunc_1.hashCode(), idenFunc_2.hashCode());
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        IdentityFunction idenFunc_1 = new IdentityFunction();
        IdentityFunction idenFunc_2 = (IdentityFunction) idenFunc_1.clone();
        assertEquals(idenFunc_1, idenFunc_2);
    }
}