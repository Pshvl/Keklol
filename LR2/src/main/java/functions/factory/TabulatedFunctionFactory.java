package functions.factory;

import functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] var1, double[] var2);
}
