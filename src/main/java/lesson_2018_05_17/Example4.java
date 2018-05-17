package lesson_2018_05_17;

import java.util.concurrent.TimeUnit;

public class Example4 {

    private static class SimpleExecutorSerivce {

        void execute(Runnable runnable) {
            new Thread(runnable).start();
        }

    }

    public static void main(String[] args) {
        SimpleExecutorSerivce serivce = new SimpleExecutorSerivce();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Before sleeping " + threadName);
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("After sleeping " + threadName);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        
        serivce.execute(task);
        serivce.execute(task);
        serivce.execute(task);
        serivce.execute(task);
    }
}
