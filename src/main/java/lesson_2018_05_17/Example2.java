package lesson_2018_05_17;

import java.util.concurrent.TimeUnit;

public class Example2 {

    // x + y == 100
    private static class Storage {
        private volatile int x = 100;
        private volatile int y = 0;

        synchronized void fromXtoY() {
            --x;
            ++y;
        }

        public synchronized int getX() {
            return x;
        }

        public synchronized int getY() {
            return y;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Storage storage = new Storage();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2_100_000_000; ++i) {
                    storage.fromXtoY();
                }
            }
        });
        thread.start();

        TimeUnit.MICROSECONDS.sleep(10);

        // x = 0
        // y = 100
        System.out.println(storage.getX()); // 0
        // x = -1
        // y = 101

        System.out.println(storage.getY()); // 101
    }
}

