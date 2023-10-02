package myfirstpackage;
public class MySecondClass {
	private int val1;
	private int val2;

	public void SetVAL1(int n) {
		val1 = n;
	}
	public void SetVAL2(int n) {
		val2 = n;
	}
	public int GetVAL1() {return val1;}
	public int GetVAL2() {return val2;}
	
        public MySecondClass() {
		this.val1 = 1;
		this.val2 = 1;
	}

	public int div() {return (val1/val2);}
}