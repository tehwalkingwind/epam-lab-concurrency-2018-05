package lesson_2018_05_14;

import java.util.concurrent.TimeUnit;

public class Example4 {

    enum State {
        RUNNING,
        PAUSED,
        STOPPED
    }

    private static volatile State state;
    private static int value = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable counterTask = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    switch (state) {
                        case RUNNING:
                            ++value;
                            break;

                        case PAUSED:
                            break;

                        case STOPPED:
                            return;
                    }
                }
            }
        };
        Thread counterThread = new Thread(counterTask, "Counter");

        state = State.RUNNING;
        counterThread.start();

        System.out.println(value);
        TimeUnit.MILLISECONDS.sleep(500);
        state = State.PAUSED;
        System.out.println(value);

        TimeUnit.SECONDS.sleep(5);
        state = State.STOPPED;

        TimeUnit.SECONDS.sleep(1);
        System.out.println(state);
        System.out.println(counterThread.getState());
        System.out.println(value);
    }
}
