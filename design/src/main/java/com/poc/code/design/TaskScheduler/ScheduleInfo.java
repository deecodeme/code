package com.poc.code.design.TaskScheduler;

public class ScheduleInfo {
    private long withDelay;
    private long schedulingInterval;
    private int maxRunCount;

    public ScheduleInfo(long withDelay, long schedulingInterval, int maxRunCount) {
        this.withDelay = withDelay;
        this.schedulingInterval = schedulingInterval;
        this.maxRunCount = maxRunCount;
    }

    public long getWithDelay() {
        return withDelay;
    }

    public void setWithDelay(long withDelay) {
        this.withDelay = withDelay;
    }

    public long getSchedulingInterval() {
        return schedulingInterval;
    }

    public void setSchedulingInterval(long schedulingInterval) {
        this.schedulingInterval = schedulingInterval;
    }

    public int getMaxRunCount() {
        return maxRunCount;
    }

    public void setMaxRunCount(int maxRunCount) {
        this.maxRunCount = maxRunCount;
    }
}
