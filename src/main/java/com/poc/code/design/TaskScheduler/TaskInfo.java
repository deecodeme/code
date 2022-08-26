package com.poc.code.design.TaskScheduler;

public class TaskInfo {
    private String name;

    public TaskInfo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TaskInfo{" +
                "name='" + name + '\'' +
                '}';
    }
}
