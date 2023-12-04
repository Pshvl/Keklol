package concurrent;

import functions.ConstantFunction;
import functions.LinkedListTabulatedFunction;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 1000, 1000);

        Thread readthread = new Thread(new ReadTask(function));
        Thread writethread = new Thread(new WriteTask(function, 0.5));

        readthread.start();
        writethread.start();
    }

}
