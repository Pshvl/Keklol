class MyFirstClass {
    public static void main(String[] s) {
        MySecondClass o = new MySecondClass(10, 5);
        System.out.println(o.div());
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                o.setVAL1(i);
                o.setVAL2(j);
                System.out.print(o.div());
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}

    class MySecondClass {
        private int val1;
        private int val2;

        public MySecondClass(int v1, int v2) {
            val1 = v1;
            val2 = v2;
        }

        public int getVAL1() {
            return val1;
        }

        public void setVAL1(int n) {
            val1 = n;
        }


        public int getVAL2() {
            return val2;
        }

        public void setVAL2(int n) {
            val2 = n;
        }

        public int div() {
            return (val1 / val2);
        }
    }
