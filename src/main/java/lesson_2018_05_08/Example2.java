package lesson_2018_05_08;

import lombok.SneakyThrows;

import java.io.IOException;

public class Example2 {

    public static void main(String[] args) {
        Example2 ref1 = new Example2();
        Example2 ref2 = new Example2();

//        System.out.println(ref1.hashCode());
//        System.out.println(System.identityHashCode(ref1));
//
//        System.out.println(ref2.hashCode());
//        System.out.println(System.identityHashCode(ref2));


        throwCheckedException();
    }

    public static void throwCheckedException() {
        throwCheckedException(new IOException());
    }

    private static <T extends Throwable> void throwCheckedException(Throwable ex) throws T {
        anotherMethod(ex);
        throw (T)ex;
    }

    private static void anotherMethod(Throwable obj) {
        obj.fillInStackTrace();
    }


    @Override
    public int hashCode() {
        return 1000;
    }
}
