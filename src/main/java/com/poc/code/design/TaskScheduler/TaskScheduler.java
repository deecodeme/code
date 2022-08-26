package com.poc.code.design.TaskScheduler;

public interface TaskScheduler {
    void start();

    Task runTask(Runnable task);

    RecurringTask scheduleTask(TaskInfo taskInfo, Runnable task, ScheduleInfo scheduleInfo);
}
