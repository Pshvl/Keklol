package operations;

import functions.Point;
import functions.TabulatedFunction;
import java.util.function.BinaryOperator;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int length = tabulatedFunction.getCount();
        Point [] pointArray = new Point[length];

        int i = 0;
        for (Point point: tabulatedFunction)
        {
            pointArray[i] = point;
            i++;
        }
        return pointArray;
    }
    public TabulatedFunction multiply(TabulatedFunction firstFunction, TabulatedFunction secondFunction) {
        BiOperation operation = (u, v) -> u * v;
        return doOperation(firstFunction, secondFunction, operation);
    }

    public TabulatedFunction divide(TabulatedFunction firstFunction, TabulatedFunction secondFunction) {
        BiOperation operation = (u, v) -> {
            if (v != 0) {
                return u / v;
            } else {
                throw new ArithmeticException("Деление на 0");
            }
        };
        return doOperation(firstFunction, secondFunction, operation);
    }

}
