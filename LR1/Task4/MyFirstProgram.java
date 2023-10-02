import myfirstpackage.MySecondClass;

class MyFirstClass {
	public static void main(String args[]) {
		MySecondClass o = new MySecondClass();
		System.out.println(o.div());
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
			o.SetVAL1(i);
			o.SetVAL2(j);
			System.out.print(o.div());
			System.out.print(" ");
			}
		System.out.println();
		}
	}
}