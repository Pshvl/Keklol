package functions;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import exceptions.InterpolationException;
public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements TabulatedFunction{
    private Node head;
    private int count;
     static class Node{
        public Node next, prev;
        public double x,y;
        Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
        //1.должен возвращать текстовое описание точек

        public String toString() {
            StringBuilder stroka = new StringBuilder();
            stroka.append("(").append(x).append("; ").append(y).append(")");
            return stroka.toString();
        }

        //
        public boolean equals(Object o) {
            if (this == o) return true;  //когда переданный в метод объект также является табулированной функцией
            // и её набор точек в точности совпадает с набором точек функции, у которой вызывается метод
            return ((o != null) && (o.getClass() == this.getClass())
                    && (x == ((LinkedListTabulatedFunction.Node)o).x)
                    && (y == ((LinkedListTabulatedFunction.Node)o).y));
            //Если переданный в метод объект является экземпляром класса LinkedListTabulatedFunction. Node,
            // время работы метода должно быть сокращено за счёт прямого обращения к элементам состояния переданного в метод объекта.
        }

        public int hashCode() {//должен возвращать объект-копию для объекта IdentityFunction.
            int result = 31 * Double.hashCode(x);
            result = 31 * result + Double.hashCode(y);
            return result;
        }
        public Object clone() {//должен возвращать объект-копию для объекта табулированной функции
            Node clone = new Node(x, y);
            clone.prev = this.prev;
            clone.next = this.next;
            return clone;
        }
    }
private void addNode (double x, double y){
    Node newNode = new Node(x,y);
    if (head==null){
        head=newNode;
        newNode.next = newNode;
        newNode.prev = newNode;
    }
    else{
        Node last = head.prev;
        last.next = newNode;
        newNode.prev = last;
        head.prev = newNode;
        newNode.next = head;
    }
        count ++;

}
    public LinkedListTabulatedFunction(double[] xValues,double[] yValues){
         checkLengthIsTheSame(xValues, yValues);
         checkSorted(xValues);
        for (int i = 0; i < xValues.length; ++i) {addNode(xValues[i], yValues[i]);}

    }
    public LinkedListTabulatedFunction(MathFunction source,double xFrom, double xTo, int count){
        if (xFrom > xTo) {
            double c = xFrom;
            xFrom = xTo;
            xTo = c;
        }

        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            double x = xFrom + i * step;
            addNode(x, source.apply(x));
        }



    }
    public int getCount(){return count;}
    public double leftBound(){return head.x;}
    public double rightBound(){return head.prev.x;}


    private Node getNode(int index){
        Node Search=head;
        for (int i = 0; i < index; i++) {
            Search = Search.next;
        }
        return (Search);
    }
    public double getX(int index)
    {
        if (index < 0 || index >= count) {
        throw new IllegalArgumentException("Index is out of bounds");}
        return getNode(index).x;}
    public double getY(int index)
    {if (index < 0 || index >= count) {
        throw new IllegalArgumentException("Index is out of bounds");
    }
        return getNode(index).y;}
    public void setY(int index, double val)
    {if (index < 0 || index >= count) {
        throw new IllegalArgumentException("Index is out of bounds");
    }
        getNode(index).y = val;}
    public int indexOfX(double x)
    {for (int i = 0; i < count; i++)
    {
        if (getX(i) == x) {
            return i;
        }
    }
        return -1;}
    public int indexOfY(double y){
        for (int i = 0; i < count; i++)
    {
        if (getY(i) == y) {
            return i;
        }
    }
        return -1;}
    public int floorIndexOfX(double x)
    {
        if (x < leftBound()) {
            throw new IllegalArgumentException("Index is out of left bound");
        }
        if (x > rightBound()) {
            return getCount() - 2;
        }
        for (int i = 0; i < count; i++){
        if (x < getX(i)) {
            return i;
        }
    }
        return count;}

    public double extrapolateLeft(double x) {
        if (getCount() < 2) {
            return getY(0);
        }
        double x1 = getX(0);
        double y1 = getY(0);
        double x2 = getX(1);
        double y2 = getY(1);
        return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
    }

    protected double extrapolateRight(double x) {
        if (count < 2) {
            return (head.y);
        } else {
            double leftX = getX(count - 2);
            double rightX = getX(count - 1);
            double leftY = getY(count - 2);
            double rightY = getY(count - 1);

            return interpolate(x, leftX, rightX, leftY, rightY);
        }
    }
//2.должен возвращать напечатанный массив хранящийся внутри.
public String toString() {
    StringBuilder str1 = new StringBuilder(); // создаем объект StringBuilder для построения строки
    Node current = head;
    for (int i = 0; i < count; i++) {
        String node = current.toString();
        str1.append(node).append(", ");
        current = current.next;
    }
    str1.delete(str1.length() - 2, str1.length()); // удаляем последнюю запятую и пробел
    return str1.toString();
}

//2.должен возвращать истинное значение тогда и только тогда, когда
// переданный в метод объект также является объектом и точки в точности
// совпадают с точками объекта, у которого вызывается метод.
public boolean equals(Object o) {
    if (this == o) return true;
    Node node = head;  // Получаем первый узел списка
    if (o.getClass() == o.getClass() && count == ((LinkedListTabulatedFunction)o).getCount()) {
        Node other = ((LinkedListTabulatedFunction)o).getNode(0); // Получаем первый узел другого списка
        // Сравниваем узлы списка
        do {
            if (!node.equals(other)) return false;
            node = node.next;
            other = other.next;
        } while (node != head);
        return true;
    }
    return false;
}

    public int hashCode() { //должен возвращать значение хэш-кода для объекта.
        int result = 0; // инициализируем переменную результатом
        for (Node temp = head; temp != head.prev; temp = temp.next) {

            result = result * 31 + temp.hashCode();
        }
        result = result * 31 + head.prev.hashCode(); // вычисляем хеш-код для последнего узла и добавляем его к результату
        return result;
    }
    public Object clone() {//должен возвращать объект-копию для объекта.
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        int i = 0;
        for (Node temp = head; temp != head.prev; temp = temp.next) {
            xValues[i] = temp.x;
            yValues[i] = temp.y;
            i++;
        }
        xValues[count - 1] = head.prev.x; // добавляем координату x последнего узла в массив
        yValues[count - 1] = head.prev.y; // добавляем координату y последнего узла в массив
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private Node node = head;

            @Override
            public boolean hasNext() { return node != null; }

            @Override
            public Point next() {
                if(hasNext()) {
                    Point point = new Point(node.x, node.y);
                    node = node.next;
                    if (node == head) node = null;
                    return point;
                }
                else throw new NoSuchElementException();
            }
        };
    }
}

