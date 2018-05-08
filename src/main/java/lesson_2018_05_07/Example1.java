package lesson_2018_05_07;

public class Example1 {

    public static void main(String[] args) throws ClassNotFoundException {
        // 0xCAFEBABE
        Object ref = new MyClass();

        String val = new String("val");

        Class<?> aClass = Class.forName("lesson_2018_05_07.MyClass");

        MyClass realReference = (MyClass) ref;
        String notRealReference = (String) ref;

        System.out.println(realReference.getValue1());
        System.out.println(realReference.getValue2());
    }
}


class MyClass {

    // HEADER                      2 машинных слова (16 байт)
    private int value1 = 42;    // 4 байта
    private short value2 = -50; // 2 байта
    // OFFSET                      2 байта
                                // 24 байта

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }
}
