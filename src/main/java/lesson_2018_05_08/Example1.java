package lesson_2018_05_08;

public class Example1 {


    /*
    0)
        _


    1) BIPUSH 10
       10
       _


    2) ISTORE 1
       _


    3) BIPUSH 20
       20
       _


    4) ISTORE 2
       _


    5) ILOAD 1
       10
       _

    6) ILOAD 2
       20
       10
       _


    7) IADD
       30
       _


    8) ISTORE 3
       _

     */




    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int c = a + b;
    }
}
