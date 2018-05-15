package lesson_2018_05_15;

import lombok.Synchronized;

import java.util.concurrent.TimeUnit;

public class Example6 {

    private static class Base {

        @Synchronized
        void method() throws InterruptedException {
            System.out.println("Enter method");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Leave method");
        }

        @Synchronized
        void method2() throws InterruptedException {
            System.out.println("Enter method2");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Leave method2");
        }
    }

    private static class Child extends Base {

        @Synchronized
        void method() throws InterruptedException {
            System.out.println("Enter sub-method");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Leave sub-method");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Base ref = new Child();

        ref.method();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ref.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
