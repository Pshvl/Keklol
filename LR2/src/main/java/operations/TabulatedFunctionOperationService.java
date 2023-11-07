package operations;

import functions.Point;
import functions.TabulatedFunction;

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
}
