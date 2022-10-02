package com.poc.code.practices.design.TaskScheduler;

public class InstantTask extends Task {
    public InstantTask(TaskInfo taskInfo, Runnable runnable, long scheduledTime) {
        super(taskInfo, runnable, scheduledTime);
    }
}
