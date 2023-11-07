package operations;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.Point;
public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction>{

    protected TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {

        this.factory = factory;
    }

    public TabulatedDifferentialOperator() {

        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return this.factory;
    }

    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] arrayOfPoints = TabulatedFunctionOperationService.asPoints(function);
        double[] xValue = new double[function.getCount()];
        double[] yValue = new double[function.getCount()];
        int i = 0;
        while (i < (xValue.length-1)) {
            xValue[i] = arrayOfPoints[i].x;
            yValue[i] = (arrayOfPoints[i + 1].y - arrayOfPoints[i].y) / (arrayOfPoints[i + 1].x - arrayOfPoints[i].x);
            i++;
        }
        xValue[i] = arrayOfPoints[i].x;
        yValue[i] = yValue[i - 1];
        return factory.create(xValue, yValue);

    }
}
