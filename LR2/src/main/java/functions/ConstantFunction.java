package functions;

public class ConstantFunction implements MathFunction {
    private final double constant;
    public ConstantFunction(double constNum){
        this.constant = constNum;
    }
    public double apply(double x) {
        return constant;
    }

    public double getConstant() {
        return constant;
    }
}
