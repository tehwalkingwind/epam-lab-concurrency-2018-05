package lesson_2018_05_15;

import lombok.Synchronized;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class Example5 {

    private static class Counter {

        private volatile long value = 0;

        @Synchronized
        void inc() {
            ++value;
        }

        @Synchronized
        void dec() {
            --value;
        }

        synchronized static void myMethod() {

        }

        void unsafeDec() {
            --value;
        }

        public long getValue() {
            return value;
        }
    }

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        Counter counter = new Counter();

        Field $lock = Counter.class.getDeclaredField("$lock");
        $lock.setAccessible(true);
        Object objectLock = $lock.get(counter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objectLock) {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        counter.unsafeDec();
        counter.dec();
        System.out.println(counter.getValue());
    }
}
