package functions;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.IntStream;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Iterable<Point>{
    private int count; // количество строк в таблице
    private double[] xValues; // таблица со значениями x
    private double[] yValues; // таблица со значениями y

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        this.xValues = Arrays.copyOf(xValues, xValues.length); // используем copyOf, чтобы массивы нельзя было поменять за пределами класса
        this.yValues = Arrays.copyOf(yValues, yValues.length);

        this.count = xValues.length;
    }
    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) { // конструктор, заполняющий массивы с помощью дискретизации фунции source
        if (xFrom > xTo) { // меняем местами xFrom и xTo, если xFrom > xTo
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        this.count = count;
        this.xValues = new double[count];
        this.yValues = new double[count];

        double fragmentRange = (xTo-xFrom)/(count-1); // шаг с которым идет дискретизация
        double fragmentVal = xFrom;
        for (int i = 0; i < count; i++){ // заполнение массивов
            this.xValues[i] = fragmentVal;
            this.yValues[i] = source.apply(fragmentVal);
            fragmentVal += fragmentRange;
        }

    }

    @Override
    public String toString() { // вид: {(x1,y1)(x2,y2)...(xn,yn)}
        String array = "";
        for (int i = 0; i < count; i++){
            array += '(' + String.valueOf(xValues[i]) + ',' + String.valueOf(yValues[i]) + ')';
        }
        return '{' + array + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass() || this.count != ((ArrayTabulatedFunction) obj).count) {
            return false; // если не совпадает класс или количество элементов, то объекты не равны
        }
        boolean ans = true;
        int i = 0;
        ArrayTabulatedFunction objArray = (ArrayTabulatedFunction) obj;
        while (ans) {
            if (this.xValues[i] != objArray.xValues[i] || this.yValues[i] != objArray.yValues[i]) {
                ans = false; // если нашли не равные элементы, выходим из цикла, возвращаем false
            }
            i++;
            if (i == this.count) return ans; // возвращаем ans, когда прошли все элементы
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
    public double getX(int index) { // получение x по его индексу
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of range");
        }
        return xValues[index];
    }

    @Override
    public double getY(int index) { // получение y по его индексу
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of range");
        }
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) { // установка значения value на заданный индекс по y
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of range");
        }
        yValues[index] = value;
    }

    @Override
    public double leftBound() { // возвращает значение левой границы x
        return xValues[0];
    }

    @Override
    public double rightBound() { // возвращает значение правой границы x
        return xValues[count - 1];
    }

    @Override
    public int indexOfX(double x) { // поиск заданного x в массиве
        for (int i = 0; i < count; i++){
            if (xValues[i] == x){
                return i; // возвращает индекс найденного x
            }
        }
        return -1; // возвращает -1, если x не найден
    }
    @Override
    public int indexOfY(double y) { // поиск заданного y в массиве
        for (int i = 0; i < count; i++){
            if (yValues[i] == y){
                return i; // возвращает индекс найденного y
            }
        }
        return -1; // возвращает -1, если y не найден
    }

    @Override
    protected int floorIndexOfX(double x) { // возвращает верхнюю границу x
        for (int i = 0; i < count; i++){
             if (x < xValues[i]) {
                 return i;
             }
         }
        return count;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]); // ссылаемся на интерполяцию, ставя в значения самый левый интервал
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, xValues[count-2], xValues[count-1], yValues[count-2], yValues[count-1]); // ссылаемся на интерполяцию, ставя в значения самый правый интервал
    }

    @Override
    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return super.interpolate(x, leftX, rightX, leftY, rightY);
    }
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < count;
            }
            @Override
            public Point next() {
                if (hasNext()) {
                    return new Point(xValues[i], yValues[i++]);
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
