package com.poc.code.practices.demo.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo {
    public interface Fibonacci {
        int result();
    }

    public static class FibonacciRecursiveTask extends RecursiveTask<Integer> implements Fibonacci {
        private static final int SYNC_COMPUTE_THRESHOLD = 20;
        private final int n;

        public FibonacciRecursiveTask(int n) {
            this.n = n;
        }

        @Override
        public int result() {
            return compute();
        }

        @Override
        protected Integer compute() {
            if (n <= SYNC_COMPUTE_THRESHOLD) {
                return computeSync(n);
            }
            FibonacciRecursiveTask f1 = new FibonacciRecursiveTask(n - 1);
            f1.fork();
            FibonacciRecursiveTask f2 = new FibonacciRecursiveTask(n - 2);
            return f2.compute() + f1.join();
        }

        private Integer computeSync(int n) {
            if (n <= 1) {
                return n;
            }
            return computeSync(n - 1) + computeSync(n - 2);
        }
    }

    public static class ForkJoinAdder extends RecursiveTask<Integer> {
        private static final int SYNC_PROCESSING_THRESHOLD = 10;
        private static final int FORK_LIMIT = 10;
        private final int[] data;

        public ForkJoinAdder(int[] data) {
            this.data = data;
        }

        @Override
        protected Integer compute() {
            if (this.data.length <= SYNC_PROCESSING_THRESHOLD) {
                return computeSync();
            } else {
                return ForkJoinTask.invokeAll(forkComputes()).stream().mapToInt(ForkJoinTask::join).sum();
            }
        }

        private int computeSync() {
            return Arrays.stream(data).sum();
        }

        private List<ForkJoinAdder> forkComputes() {
            List<ForkJoinAdder> forkJoinAdderList = new ArrayList<>();
            int start = 0;
            while (start < data.length) {
                forkJoinAdderList.add(new ForkJoinAdder(Arrays.copyOfRange(data, start, Math.min(start + FORK_LIMIT, data.length))));
                start += FORK_LIMIT;
            }
            return forkJoinAdderList;
        }
    }
}
