package com.poc.code.design.TaskScheduler;

public abstract class Task {
    private TaskInfo taskInfo;
    private Runnable command;
    private long scheduledTime;

    public Task(TaskInfo taskInfo, Runnable command, long scheduledTime) {
        this.command = command;
        this.scheduledTime = scheduledTime;
        this.taskInfo = taskInfo;
    }

    public Runnable getCommand() {
        return command;
    }

    public void setCommand(Runnable command) {
        this.command = command;
    }

    public long getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(long scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public TaskInfo getTaskInfo() {
        return taskInfo;
    }
}
