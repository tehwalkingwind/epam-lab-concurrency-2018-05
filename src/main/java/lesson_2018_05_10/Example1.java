package lesson_2018_05_10;

public class Example1 {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("In thread " + t + " was thrown " + e);
            }
        });
        method1();
    }

    public static void method1() {
        try {
            method2();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    public static void method2() {
        throw new RuntimeException("Hello from method2");
    }

}
