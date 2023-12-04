package concurrent;
import java.util.*;

import java.util.concurrent.locks.*;
import functions.*;
import operations.*;

public class SynchronizedTabulatedFunction implements TabulatedFunction{
    private final TabulatedFunction function;



    public SynchronizedTabulatedFunction(TabulatedFunction function) {
        this.function = function;
    }
}
