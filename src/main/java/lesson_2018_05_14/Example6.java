package lesson_2018_05_14;

public class Example6 {

    private static class Counter extends Thread {

        @Override
        public void run() {
            synchronized (this) {
                for (long i = 0; i < 1_000_000L; ++i) {
                    ++value;
                }
            }
        }

        public static void method() {
            synchronized (Counter.class) {

            }
        }
    }

    private static volatile long value = 0;

    public static void main(String[] args) throws InterruptedException {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();

        counter1.start();
        counter2.start();

        counter1.join();
        counter2.join();

        System.out.println(value);
    }
}
