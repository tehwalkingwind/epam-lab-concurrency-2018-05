package lesson_2018_05_14;

import java.util.concurrent.TimeUnit;

public class Example2 {

    private static long value = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable counter = new Runnable() {

            @Override
            public void run() {
                for (long i = 0; i < 100_000_000_000L; ++i) {
                    ++value;
                }
            }
        };

        Thread counterThread = new Thread(counter);
        System.out.println(counterThread.getState());
        counterThread.start();

        while (counterThread.isAlive()) {
            TimeUnit.MILLISECONDS.sleep(20);
        }
        System.out.println("Main: " + value);
    }
}
