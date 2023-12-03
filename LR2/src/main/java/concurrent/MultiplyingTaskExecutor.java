package concurrent;

import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.UnitFunction;

import java.util.*;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction linkListFunc = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 100);
        List<Thread> threadList = new ArrayList<>();
        Deque<MultiplyingTask> tasks = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            MultiplyingTask task = new MultiplyingTask(linkListFunc);
            tasks.add(task);
            threadList.add(new Thread(task));
        }

        for (Thread t : threadList) {
                t.start();
        }
        while(!tasks.isEmpty()) {
            tasks.removeIf(multiplyingTask -> multiplyingTask.isCompleted);
        }

        System.out.println(linkListFunc);
    }
}
