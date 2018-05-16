package lesson_2018_05_16.philosophers;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class Waiter {

    private static final Table TABLE = new Table();
    private static volatile boolean dinnerStarted = false;

    public static void main(String[] args) throws InterruptedException {
        Thread waiter = Thread.currentThread();
        waiter.setName("Waiter");

        System.out.println("Creating philosophers");
        Philosopher mo = createPhilisopher("Mo");
        Philosopher lao = createPhilisopher("Lao");
        Philosopher kun = createPhilisopher("Kun");

        mo.inviteTo(TABLE);
        lao.inviteTo(TABLE);
        kun.inviteTo(TABLE);

        TimeUnit.MILLISECONDS.sleep(500);

        dinnerStarted = true;

        for (int i = 0; i < 10; ++i) {
            System.out.println("Do u need smth?");
            TimeUnit.SECONDS.sleep(2);
        }
    }

    private static Philosopher createPhilisopher(String name) {
        return new Philosopher(name) {

            @Override
            @SneakyThrows
            public void run() {
                while (!dinnerStarted);

                synchronized (TABLE.getStickWithLowestIndex(this)) {
                    TimeUnit.MILLISECONDS.sleep(500);
                    synchronized (TABLE.getStickWithHighestIndex(this)) {
                        TimeUnit.MILLISECONDS.sleep(500);
                        System.out.println(getName() + " start eating");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println(getName() + " end eating");
                    }
                }
            }
        };
    }
}
