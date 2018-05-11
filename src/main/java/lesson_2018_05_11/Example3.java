package lesson_2018_05_11;

import java.util.concurrent.TimeUnit;

public class Example3 {

    public static void main(String[] args) throws InterruptedException {
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

        Thread counter1 = new Thread(task, "Counter-1");
        counter1.start();

        TimeUnit.MILLISECONDS.sleep(20);
        counter1.interrupt();
    }
}
