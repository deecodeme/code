package com.poc.code.design.TaskScheduler;

public class RecurringTask extends Task {

    private ScheduleInfo scheduleInfo;

    public RecurringTask(TaskInfo taskInfo, Runnable runnable, ScheduleInfo scheduleInfo) {
        super(taskInfo, runnable, System.currentTimeMillis() + scheduleInfo.getWithDelay());
        this.scheduleInfo = scheduleInfo;
    }

    public ScheduleInfo getScheduleInfo() {
        return scheduleInfo;
    }

    public void setScheduleInfo(ScheduleInfo scheduleInfo) {
        this.scheduleInfo = scheduleInfo;
    }
}
