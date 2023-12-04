package concurrent;
import java.util.*;

import functions.*;

public class SynchronizedTabulatedFunction implements TabulatedFunction{
    private final TabulatedFunction function;
    private final Object mutex;

    public SynchronizedTabulatedFunction(TabulatedFunction function) {
        this.function = function;
        mutex = this;
    }

    @Override
    public double apply(double x) {
        synchronized (mutex) {return function.apply(x);}
    }

    @Override
    public int getCount() {
        synchronized (mutex) {return function.getCount();}
    }

    @Override
    public double getX(int index) {
        synchronized (mutex) {return function.getX(index);}
    }

    @Override
    public double getY(int index) {
        synchronized (mutex) {return function.getY(index);}
    }

    @Override
    public void setY(int index, double value) {
        synchronized (mutex) {function.setY(index, value);}
    }

    @Override
    public int indexOfX(double x) {
        synchronized (mutex) {return function.indexOfX(x);}
    }

    @Override
    public int indexOfY(double y) {
        synchronized (mutex) {return function.indexOfY(y);}
    }

    @Override
    public double leftBound() {
        synchronized (mutex) {return function.leftBound();}
    }

    @Override
    public double rightBound() {
        synchronized (mutex) {return function.rightBound();}
    }

    @Override
    public Iterator<Point> iterator() {
        synchronized (mutex) {
            return new Iterator<Point>() {
                int i;
                @Override
                public boolean hasNext() {
                    return i < function.getCount();
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
}
