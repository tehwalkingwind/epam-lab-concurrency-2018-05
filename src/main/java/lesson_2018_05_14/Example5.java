package lesson_2018_05_14;

public class Example5 {

    // shared state
    // visibility problem
    private static volatile long value = 0;
    private static final Object LOCK = new Object();

    // monitor
    // mark word                 <- 1 машинное слово
    // 01 |  hashCode | age | 0  <- unlocked
    // 00 | mark_pointer         <- locked
    // 01 |  .  . .         | 1
    // 10
    // 11                        <- помечен для сборки


    // class pointer             <- 2 машинное слово
    // array length              <- 3 машинное слово

    public static void main(String[] args) throws InterruptedException {
        Runnable counter = new Runnable() {

            @Override
            public void run() {
                for (long i = 0; i < 1_000_000L; ++i) {
                    // Критическая секция
                        // гонка потоков
                        // race condition
                        // read-modify-store

                        ++value;
                        // LOAD
                        // INC
                        // STORE
                }
            }
        };
        Thread counter1 = new Thread(counter);
        Thread counter2 = new Thread(counter);

        counter1.start();
        counter2.start();

        counter1.join();
        counter2.join();

        System.out.println(value);
    }


    //    THREAD_1                  VALUE                  THREAD_2
    //                                0
    //      0          <- LOAD        0        LOAD ->        0
    //      1            INC          0          INC          1
    //      1           STORE ->      1
    //      1                         1        <- STORE       1




    //    THREAD_1                  VALUE                  THREAD_2
    //     *             * <- monitor 0  monitor ->
    //      0          <- LOAD        0
    //      1            INC          0
    //      1           STORE ->      1
    //      1           monitor -> *  1
    //                                1  monitor -> *
    //                                1     LOAD ->           1
    //                                1      INC              2
    //                                2    <- STORE           2
    //                                   * <- monitor
}
