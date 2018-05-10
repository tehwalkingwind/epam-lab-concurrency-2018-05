package lesson_2018_05_10;

public class Example2 {

    public static void main(String[] args) {
        method(0);
    }

    public static void method(int i) {
        System.out.println(i);
        method(++i);
    }
}
