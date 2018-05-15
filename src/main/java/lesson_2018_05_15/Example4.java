package lesson_2018_05_15;

public class Example4 {

    private static class Counter {

        private volatile long value = 0;
        private final Object lock = new Object();

        void inc() {
            synchronized (lock) {
                ++value;
            }
        }

        void dec() {
            synchronized (lock) {
                --value;
            }
        }

        public long getValue() {
            return value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (counter) {
                    try {
                        Thread.currentThread().join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        Thread inc = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100_000; ++i) {
                    counter.inc();
                }
            }
        });
        inc.start();


        Thread dec = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100_000; ++i) {
                    counter.dec();
                }
            }
        });
        dec.start();

        inc.join();
        dec.join();

        System.out.println(counter.getValue());
    }
}
