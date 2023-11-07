package operations;

import functions.MathFunction;

public class MiddleSteppingDifferentialOperator extends SteppingDifferentialOperator {
    protected double step;
    MiddleSteppingDifferentialOperator(double step){
        super(step);
    }
    @Override
    public MathFunction derive(MathFunction function) {
        return new MathFunction() {
            @Override
            public double apply(double x) {
                return (function.apply(x + GetStep()) - function.apply(x - GetStep())) / (2 * GetStep());
            }
        };
    }
}
