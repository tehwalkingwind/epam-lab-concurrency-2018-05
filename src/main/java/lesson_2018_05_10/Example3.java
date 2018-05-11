package lesson_2018_05_10;

import java.util.concurrent.TimeUnit;

public class Example3 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello from Example3: " + Thread.currentThread());

        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from ? " + Thread.currentThread() + " id " + Thread.currentThread().getId());
                int counter = 0;
                while (true) {
                    counter = printCurrentCounter(counter);
                }

            }

            private int printCurrentCounter(int counter) {
                System.out.println(counter++);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return counter;
            }
        };

        Thread counter1 = new Thread(task, "Counter-1");
        counter1.start();

        Thread counter2 = new Thread(task, "Counter-2");
        counter2.start();


        TimeUnit.SECONDS.sleep(10);

        System.out.println("Main end");
    }
}
