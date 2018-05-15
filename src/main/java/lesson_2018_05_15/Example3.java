package lesson_2018_05_15;

public class Example3 {

    public static void main(String[] args) {
        Integer a = 121;
        Integer b = 123;
        System.out.println(b == (a += 2));


        synchronized (Integer.valueOf(10)) {

        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Integer.valueOf(10)) {

                }
            }
        }).start();
    }
}
