package functions;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import exceptions.InterpolationException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private int count;
    private double[] xValues;
    private double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);

        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);

        this.count = xValues.length;
    }
    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        this.count = count;
        this.xValues = new double[count];
        this.yValues = new double[count];

        double fragmentRange = (xTo-xFrom)/(count-1);
        double fragmentVal = xFrom;
        for (int i = 0; i < count; i++){
            this.xValues[i] = fragmentVal;
            this.yValues[i] = source.apply(fragmentVal);
            fragmentVal += fragmentRange;
        }

    }

    @Override
    public String toString() {
        String array = "";
        for (int i = 0; i < count; i++){
            array += '(' + String.valueOf(xValues[i]) + ',' + String.valueOf(yValues[i]) + ')';
        }
        return '{' + array + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass() || this.count != ((ArrayTabulatedFunction) obj).count) {
            return false;
        }
        if (this.hashCode() != obj.hashCode()) {
            return false;
        }
        boolean ans = true;
        int i = 0;
        ArrayTabulatedFunction objArray = (ArrayTabulatedFunction) obj;
        while (ans) {
            if (this.xValues[i] != objArray.xValues[i] || this.yValues[i] != objArray.yValues[i]) {
                ans = false;
            }
            i++;
            if (i == this.count) return ans;
        }
        return ans;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (int i = 0; i < count; i++) {
            hashCode = hashCode ^ ( (int) yValues[i] ^ (int) xValues[i] );
        }
        return hashCode;
    }

    @Override
    public Object clone() {
        return new ArrayTabulatedFunction(xValues.clone(), yValues.clone());
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of range");
        }
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of range");
        }
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of range");
        }
        yValues[index] = value;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++){
            if (xValues[i] == x){
                return i;
            }
        }
        return -1;
    }
    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++){
            if (yValues[i] == y){
                return i;
            }
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        for (int i = 0; i < count; i++){
             if (x < xValues[i]) {
                 return i;
             }
         }
        return count;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, xValues[count-2], xValues[count-1], yValues[count-2], yValues[count-1]);
    }

    protected double interpolate(double x, int floorIndex) {
        if (this.xValues[floorIndex] < x && x < this.xValues[floorIndex+1])
            return interpolate(x, this.xValues[floorIndex], this.xValues[floorIndex+1], this.yValues[floorIndex], this.yValues[floorIndex+1]);
        else
            throw new InterpolationException();
    }
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            int i;
            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (!(hasNext())) throw new NoSuchElementException();
                Point point = new Point(getX(i), getY(i));
                i++;

                return point;
            }
        };
    }
}
