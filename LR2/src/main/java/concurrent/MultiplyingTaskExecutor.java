package concurrent;

import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.UnitFunction;

import java.util.ArrayList;
import java.util.List;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction linkListFunc = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 10);
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Runnable task = new MultiplyingTask(linkListFunc);
            threadList.add(new Thread(task));
        }

        for (Thread t : threadList) {
                t.start();
        }

        Thread.sleep(1000);
        System.out.println(linkListFunc);
    }
}
