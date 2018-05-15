package lesson_2018_05_15;

public class Example2 {

    private static volatile Long value = 0L;

    public static void main(String[] args) throws InterruptedException {
        Thread inc = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100_000; ++i) {
                    synchronized (value) {
                        ++value;
                    }
                }
            }
        });
        inc.start();


        Thread dec = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100_000; ++i) {
                    synchronized (value) {
                        --value;
                    }
                }
            }
        });
        dec.start();

        inc.join();
        dec.join();

        System.out.println(value);
    }
}
