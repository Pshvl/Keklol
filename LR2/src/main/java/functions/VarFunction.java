package functions;

import java.lang.Math;

public class VarFunction implements MathFunction {
    public double apply(double x) {
        return 1 + Math.pow(Math.tan(x),2);
    }
}