package com.poc.code.practices.design.ConcurrencyAndMultiThreading.ExecutorService;

import java.util.Random;
import java.util.concurrent.*;

public class Executor {
    private static final int NUMBER_OF_THREADS = 10;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        for (int i = 0; i<20; i++){
            Runnable job = new MyRunnable(i);
            executorService.execute(job);
            MyCallable callableJob = new MyCallable();
            Future future = executorService.submit(callableJob);
            try{
                System.out.println("Future : "+future.get());
            }catch (InterruptedException | ExecutionException e){
                System.out.println("Exception: "+ e);
            }
        }

        executorService.shutdown();
        while (!executorService.isTerminated()){
            System.out.println("Termination in progress.....");
        }
        System.out.println("Executor service terminated....");

    }
}

class MyRunnable implements Runnable{
    private int i;

    public MyRunnable(int i){
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("Number: " +this.i + " Thread: " +Thread.currentThread().getName());
    }
}

class MyCallable implements Callable{
    @Override
    public Object call() throws Exception {
        Random random = new Random();

        return random.nextInt();

    }
}




