package lesson_2018_05_11;

import java.util.concurrent.TimeUnit;

public class Example4 {

    public static void main(String[] args) throws InterruptedException {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                long val = 0;
                while (!Thread.currentThread().isInterrupted()) {
                    ++val;
                    Thread.yield();
                }
                System.out.println(Thread.currentThread().getName() + ": " + val);
            }
        };

        Thread[] counters = new Thread[Runtime.getRuntime().availableProcessors() + 2];
        for (int i = 0; i < counters.length; ++i) {
            Thread counter = new Thread(task, "Counter-" + i);
            counter.setPriority(i < 2 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY);
            counters[i] = counter;
        }

        for (int i = 0; i < counters.length; ++i) {
            counters[i].start();
        }

        TimeUnit.MILLISECONDS.sleep(500);

        for (int i = 0; i < counters.length; ++i) {
            counters[i].interrupt();
        }
    }
}
