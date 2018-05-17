package lesson_2018_05_17;

import java.util.concurrent.TimeUnit;

public class Example3 {

    // x + y == 100
    private static class Storage {
        private volatile int x = 100;
        private volatile int y = 0;

        synchronized void fromXtoY() {
            --x;
            try {
                TimeUnit.MICROSECONDS.sleep(10);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            ++y;
        }

        public synchronized int getX() {
            return x;
        }

        public synchronized int getY() {
            return y;
        }

        public synchronized int[] getValues() {
            return new int[]{x, y};
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

        TimeUnit.MICROSECONDS.sleep(500);

        thread.stop();

        int[] values = storage.getValues();
        System.out.println(values[0]);
        System.out.println(values[1]);
    }
}

