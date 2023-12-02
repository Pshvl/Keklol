package concurrent;

import functions.TabulatedFunction;

public class MultiplyingTask implements Runnable {
    final private TabulatedFunction tabFunc;

    MultiplyingTask(TabulatedFunction tabFunc) {
        this.tabFunc = tabFunc;
    }

    public void run(){
        for(int i = 0; i < tabFunc.getCount(); i++){
            tabFunc.setY(i, tabFunc.getY(i)*2);
        }
        String curThread = Thread.currentThread().getName();
        System.out.println("Thread " + curThread + " has completed task");
    }
}
