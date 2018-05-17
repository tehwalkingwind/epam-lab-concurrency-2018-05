package lesson_2018_05_17;

import java.util.concurrent.TimeUnit;

public class Example1 {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (object) {
                        int val = 0;
                        for (int i = 0; i < 2_100_000_000; ++i) {
                            ++val;
                        }
                        System.out.println("Hello from thread-0: " + val);
                    }
                }
            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);

        thread.suspend();

        TimeUnit.SECONDS.sleep(3);

        thread.resume();
    }
}
