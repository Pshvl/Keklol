package functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private Node head;
    private int count;
    private static class Node{
        public Node next, prev;
        public double x,y;
        Node(double x, double y) {
            this.x = x;
            this.y = y;
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
    public double getX(int index){return getNode(index).x;}
    public double getY(int index){return getNode(index).y;}
    public void setY(int index, double val){getNode(index).y = val;}
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
    public int floorIndexOfX(double x){
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

}
