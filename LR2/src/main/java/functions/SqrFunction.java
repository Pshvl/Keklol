package functions;//1

import java.lang.Math;
public class SqrFunction implements MathFunction {
    public double apply(double x) {
        return Math.pow(x, 2);
    }
}
