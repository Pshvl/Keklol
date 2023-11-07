package operations;

import functions.MathFunction;

public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator {
    protected double step;
    LeftSteppingDifferentialOperator(double step){
        super(step);
    }
  @Override
  public MathFunction derive(MathFunction function) {
      return new MathFunction() {
          @Override
          public double apply(double x) {
              return (function.apply(x) - function.apply(x - GetStep())) / GetStep();
          }
      };
  }
}
