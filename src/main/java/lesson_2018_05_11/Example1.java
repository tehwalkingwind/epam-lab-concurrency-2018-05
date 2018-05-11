package lesson_2018_05_11;

import java.util.concurrent.TimeUnit;

public class Example1 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello from Example3: " + Thread.currentThread());

        Thread counter1 = new Counter();
        counter1.start();

        Thread counter2 = new Counter();
        counter2.start();

         Thread counter3 = new Thread(new Counter());
         counter3.start();


        TimeUnit.SECONDS.sleep(10);

        System.out.println("Main end");
    }

    private static class Counter extends Thread {

        private int field;

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
    }
}
