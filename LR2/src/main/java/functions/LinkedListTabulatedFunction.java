package functions;
 class Node{
     public Node next, prev;
     public double x,y;
     Node(double x, double y) {
         this.x = x;
         this.y = y;
     }
 }
public class LinkedListTabulatedFunction {
    private Node head;
    private int count;
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
}
