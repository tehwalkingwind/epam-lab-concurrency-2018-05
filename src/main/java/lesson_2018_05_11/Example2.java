package lesson_2018_05_11;

import java.util.concurrent.TimeUnit;

public class Example2 {

    public static void main(String[] args) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                long counter = 0;

                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(5);
                        counter++;
                    } catch (InterruptedException e) {
                        System.out.println("Counter-1: " + Thread.currentThread().isInterrupted());
                    }
                }

                System.out.println(counter);
            }
        };

        Thread counter1 = new Thread(task, "Counter-1");
        counter1.start();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        counter1.interrupt();
        System.out.println("Counter-1 in main: " + counter1.isInterrupted());
    }
}
