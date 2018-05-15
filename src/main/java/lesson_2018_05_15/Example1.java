package lesson_2018_05_15;

import java.util.List;

public class Example1 {

    // race condition

    // T1
    // T2
    // T3

    // list = {"abc"}
    // check and action

    // T3
    private static void remove(List<String> list) {
        list.remove(0);
    }

    // T1, T2
    private static void removeIfNotEmpty(List<String> list) {
        // T1, T2
        synchronized (list) {
            // T1
            if (!list.isEmpty()) {
                // T1
                String firstElement = list.remove(0);
                System.out.println(firstElement);
            }
        }
    }
}
