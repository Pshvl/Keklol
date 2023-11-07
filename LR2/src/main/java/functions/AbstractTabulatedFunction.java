package functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;

public abstract class AbstractTabulatedFunction implements TabulatedFunction{
    private int count;

    public int getCount() {
        return count;
    }

    abstract protected int floorIndexOfX(double x);

    abstract protected double extrapolateLeft(double x);

    abstract protected double extrapolateRight(double x);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) / (rightX - leftX) * (x - leftX);
    }

    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        }

        if (x > rightBound()) {
            return extrapolateRight(x);
        }

        if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        }

        int index = floorIndexOfX(x);
        return getY(index);
    }

    public static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("arrays have different length");
        }
    }
    public static void checkSorted(double[] xValues) {
        for (int i = 0; i < xValues.length-1; i++) {
            if (xValues[i] > xValues[i+1]) {
                throw new ArrayIsNotSortedException("array is not sorted");
            }
        }

    }


}
