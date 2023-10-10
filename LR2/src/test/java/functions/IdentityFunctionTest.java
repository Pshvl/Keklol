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
}