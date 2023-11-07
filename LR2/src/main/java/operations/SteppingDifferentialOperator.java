package operations;

import functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {
    protected double step;

    SteppingDifferentialOperator(double step) {
        if (step <= 0 || Double.isInfinite(step) || Double.isNaN(step)) {
            throw new IllegalArgumentException();
        }
        this.step = step;
    }
    public double GetStep() {
        return step;
    }
    public void SetStep(double step) {
        if (step <= 0 || Double.isInfinite(step) || Double.isNaN(step)) {
            throw new IllegalArgumentException();
        }
        this.step = step;
    }
}
