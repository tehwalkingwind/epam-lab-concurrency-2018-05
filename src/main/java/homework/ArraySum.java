package homework;

import lombok.AllArgsConstructor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ArraySum {

    public static void main(String[] args) {

        ForkJoinPool fjPool = new ForkJoinPool();

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.println(fjPool.invoke(new ArraySumJob(array, 2))); //55
    }

    @AllArgsConstructor
    private static class ArraySumJob extends RecursiveTask<Integer> {

        int[] array;
        int from;
        int to;
        final int SOMEWHAT;

        public ArraySumJob(int[] array, int somewhat) {
            this.array = array;
            this.SOMEWHAT = somewhat;
            this.from = 0;
            this.to = array.length;
        }

        protected Integer compute() {
            setRawResult(0);
            if (from - to < SOMEWHAT) {
                for (int i = from; i < to; i++) {
                    setRawResult(array[i] + getRawResult());
                }
            } else {
                ArraySumJob left = new ArraySumJob(array, from, (to - from) / 2, SOMEWHAT);
                ArraySumJob right = new ArraySumJob(array, (to - from) / 2, array.length, SOMEWHAT);

                left.fork();
                setRawResult(left.join() + right.compute());
            }
            return getRawResult();
        }

    }

}
