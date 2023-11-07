package operations;

import functions.MathFunction;

public class RightSteppingDifferentialOperator extends SteppingDifferentialOperator {
    protected double step;
    RightSteppingDifferentialOperator(double step){
        super(step);
    }
    @Override
    public MathFunction derive(MathFunction function) {
        return new MathFunction() {
            @Override
            public double apply(double x) {
                return (function.apply(x + GetStep()) - function.apply(x)) / GetStep();
            }
        };
    }
}
