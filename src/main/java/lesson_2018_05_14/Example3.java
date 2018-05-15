package lesson_2018_05_14;

import java.util.concurrent.TimeUnit;

public class Example3 {

    private static long value = 0;

    public static void main(String[] args)  {
        Runnable counter = new Runnable() {

            @Override
            public void run() {
                for (long i = 0; i < 10_000_000_000L; ++i) {
                    ++value;
                }
            }
        };

        Thread counterThread = new Thread(counter);
        counterThread.setDaemon(true);
        System.out.println(counterThread.getState());
        counterThread.start();

        try {
            counterThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main: " + value);
    }
}
