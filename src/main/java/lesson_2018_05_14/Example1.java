package lesson_2018_05_14;

import java.util.concurrent.TimeUnit;

public class Example1 {

    public static void main(String[] args) throws InterruptedException {
        Thread anotherThread = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Hello from another thread");
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Finally in deamon-thread");
            }
        });
        anotherThread.setDaemon(true);
        anotherThread.start();

        TimeUnit.SECONDS.sleep(3);
        System.out.println("Main end");
    }
}
