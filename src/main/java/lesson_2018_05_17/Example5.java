package lesson_2018_05_17;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Example5 {

    private static class SingleThreadExecutorSerivce {

        private final Queue<Runnable> tasks = new LinkedList<>();

        public SingleThreadExecutorSerivce() {
            new Thread(new Runnable() {
                @Override
                @SneakyThrows
                public void run() {
                    while (true) {
                        Runnable task = null;
                        synchronized (tasks) {
                            if (!tasks.isEmpty()) {
                                task = tasks.poll();
                            } else {
                                tasks.wait();
                            }
                        }
                        if (task != null) {
                            task.run();
                        }
                    }
                }
            }, "Worker").start();
        }

        void execute(Runnable runnable) {
            synchronized (tasks) {
                tasks.notifyAll();
                tasks.offer(runnable);
            }
        }
    }


    public static void main(String[] args) {
        SingleThreadExecutorSerivce serivce = new SingleThreadExecutorSerivce();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Before sleeping " + threadName);
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("After sleeping " + threadName);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        serivce.execute(task);
        serivce.execute(task);
        serivce.execute(task);
        serivce.execute(task);
    }
}
