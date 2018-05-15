package lesson_2018_05_15;

import java.util.concurrent.TimeUnit;

public class Example7 {

    // dead-lock
    public static void main(String[] args) throws InterruptedException {

        // A    B


        // T1   T2


        // T1 > A
        // T2 > B

        // T1(A) > B
        // T2(B) > A

        Object a = new Object();
        Object b = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (b) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        synchronized (a) {
                            TimeUnit.SECONDS.sleep(1);
                            System.out.println("Yahooo - 1");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        TimeUnit.MILLISECONDS.sleep(500);

        synchronized (a) {
            try {
                TimeUnit.SECONDS.sleep(1);
                synchronized (b) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Yahooo - main");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
