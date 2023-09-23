package com.poc.code.practices.design.TaskScheduler;

import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskSchedulerImpl implements TaskScheduler {
    private static Logger logger = Logger.getLogger(TaskSchedulerImpl.class.getName());
    private PriorityQueue<Task> priorityQueue;

    private WorkerService workerService;

    private final Lock lock = new ReentrantLock();

    private final Condition newTasksArrived = lock.newCondition();

    public TaskSchedulerImpl(WorkerService workerService) {
        this.workerService = workerService;
        this.priorityQueue = new PriorityQueue<>(Comparator.comparingLong(Task::getScheduledTime));
    }

    public void start() {
        logger.info("Task worker started");
        while (true) {
            Optional<Task> optionalTask = this.peekTask();
            if (optionalTask.isPresent()) {
                Task task = optionalTask.get();
                logger.info(String.format("Received task to execute: %s", optionalTask.get().getTaskInfo().toString()));
                this.workerService.execute(task);
                processTaskNextStep(task);
            }
        }
    }

    private Optional<Task> peekTask() {
        Optional<Task> task = Optional.empty();
        lock.lock();
        try {
            while (this.priorityQueue.isEmpty()) {
                newTasksArrived.await();
            }
            if (this.priorityQueue.peek().getScheduledTime() <= System.currentTimeMillis()) {
                task = Optional.of(this.priorityQueue.poll());
            }
        } catch (InterruptedException ex) {
            logger.log(Level.SEVERE, String.format("Error while scheduling task. Error: %s",
                ex.getMessage()));
        } finally {
            lock.unlock();
        }
        return task;
    }

    @Override
    public Task runTask(Runnable task) {
        return null;
    }

    @Override
    public RecurringTask scheduleTask(TaskInfo taskInfo, Runnable command, ScheduleInfo scheduleInfo) {
        RecurringTask recurringTask = new RecurringTask(taskInfo, command, scheduleInfo);
        ;
        addTaskToPriortyQueue(recurringTask);
        logger.info(String.format("Task scheduled: %s", taskInfo.toString()));
        return recurringTask;
    }

    private void addTaskToPriortyQueue(RecurringTask recurringTask) {
        try {
            this.lock.lock();
            this.priorityQueue.add(recurringTask);
            newTasksArrived.signalAll();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, String.format("Error while scheduling task. Error: %s, Task: %s",
                ex.getMessage(), recurringTask.toString()));
        } finally {
            this.lock.unlock();
        }
    }

    private void processTaskNextStep(Task task) {
        RecurringTask scheduledTask = (RecurringTask) task;
        long nextRunTime = System.currentTimeMillis() + ((RecurringTask) task).getScheduleInfo().getSchedulingInterval();
        scheduledTask.setScheduledTime(nextRunTime);
        this.addTaskToPriortyQueue(scheduledTask);
    }
}
