package functions;

/* функция всегдва возвращает заданную константу */
public class ConstantFunction implements MathFunction {
    private final double constant;
    public ConstantFunction(double constNum){ // в конструкторе задается константа
        this.constant = constNum;
    }
    public double apply(double x) {
        return constant;
    }

    public double getConstant() {
        return constant;
    }
}
