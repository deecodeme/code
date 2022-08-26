package com.poc.code.design.TaskScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskSchedulerApplication {
    public static void main(String[] args) {
        WorkerService workerService = new WorkerServiceImpl(10);
        TaskScheduler taskScheduler = new TaskSchedulerImpl(workerService);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(taskScheduler::start);
        Runnable task1 = () -> System.out.println("Task1 in progress");
        Runnable task2 = () -> System.out.println("Task2 in progress");
        taskScheduler.scheduleTask(new TaskInfo("task1"), task1, new ScheduleInfo(1000, 1000, 10));
        taskScheduler.scheduleTask(new TaskInfo("task2"), task2, new ScheduleInfo(2000, 500, 5));
    }
}
