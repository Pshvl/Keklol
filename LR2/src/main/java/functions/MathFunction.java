package functions;

public interface MathFunction {
    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) { // метод вовзращающий сложную функцию
        return new CompositeFunction(afterFunction, this);
    }
}
