package com.poc.code.practices.design.TaskScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkerServiceImpl implements WorkerService {
    Logger log = Logger.getLogger(WorkerServiceImpl.class.getName());
    private ExecutorService executorService;

    public WorkerServiceImpl(int workers) {
        this.executorService = Executors.newFixedThreadPool(workers);
    }

    @Override
    public boolean execute(Task task) {
        try {
            this.executorService.execute(task.getCommand());
            log.info(String.format("Task executed %s", task.getTaskInfo().toString()));
        } catch (Exception e) {
            log.log(Level.SEVERE, String.format("Error while executing task: %s", task, toString()));
        }
        return true;
    }
}
