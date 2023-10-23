package functions;

public class CosFunction implements MathFunction {
    public double apply(double x) {
        return Math.cos(x+x);
    }
}
