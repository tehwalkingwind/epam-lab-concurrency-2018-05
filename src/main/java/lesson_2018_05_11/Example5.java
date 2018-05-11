package lesson_2018_05_11;

import java.util.concurrent.TimeUnit;

public class Example5 {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup(Thread.currentThread().getThreadGroup(), "Sub-group");

        Runnable task = new Runnable() {
            @Override
            public void run() {
                long val = 0;
                for (int i = 0; i < Integer.MAX_VALUE; ++i) {
                    ++val;
                }
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println(val);

                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread counter1 = new Thread(threadGroup, task, "Counter-1");
        counter1.start();
        counter1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {

            }
        });

        TimeUnit.MILLISECONDS.sleep(20);
        counter1.interrupt();
    }

    private static class MyThreadGroup extends ThreadGroup {

        public MyThreadGroup(String name) {
            super(name);
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            super.uncaughtException(t, e);
        }
    }
}
