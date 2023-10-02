package myfirstpackage;
public class MySecondClass {
private int a;
private int b;
    public void setFirst(int n){ 
    a=n; 
    };

    public void setSecond(int n){
    b=n; 
    };

    public MySecondClass(){
        this.a = 0;
        this.b = 0;
       }

    public int min() { 
          return(a<=b? a : b);
    }

}