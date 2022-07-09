package com.poc.code.psds.misc;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
https://leetcode.com/contest/weekly-contest-237/problems/single-threaded-cpu/
You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer array tasks,
where tasks[i] = [enqueueTimei, processingTimei] means that the i​​​​​​th​​​​ task will be available to process at enqueueTimei and will take processingTimei to finish processing.

You have a single-threaded CPU that can process at most one task at a time and will act in the following way:

If the CPU is idle and there are no available tasks to process, the CPU remains idle.
If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time.
If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
Once a task is started, the CPU will process the entire task without stopping.
The CPU can finish a task then start a new one instantly.
Return the order in which the CPU will process the tasks.

Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
Output: [0,2,3,1]
Explanation: The events go as follows:
- At time = 1, task 0 is available to process. Available tasks = {0}.
- Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
- At time = 2, task 1 is available to process. Available tasks = {1}.
- At time = 3, task 2 is available to process. Available tasks = {1, 2}.
- Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
- At time = 4, task 3 is available to process. Available tasks = {1, 3}.
- At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
- At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
- At time = 10, the CPU finishes task 1 and becomes idle.
 */
public class SingleThreadedCPU {
    public int[] getOrder(int[][] tasks) {
        int[] output = new int[tasks.length];
        int outputIndex = 0;
        Task[] taskArray = new Task[tasks.length];
        int taskIndex = 0;
        for (int[] t : tasks) {
            taskArray[taskIndex] = new Task(t[0], t[1], taskIndex);
            taskIndex++;
        }
        Arrays.sort(taskArray, (i, j) -> {
            return Integer.compare(i.enqTime, j.enqTime);
        });
        PriorityQueue<Task> pq = new PriorityQueue<Task>((t1, t2) -> t1.procTime == t2.procTime ?
                Integer.compare(t1.index, t2.index) :
                Integer.compare(t1.procTime, t2.procTime));
        int index = 0;
        int currTime = taskArray[index].enqTime;

        while (!pq.isEmpty() || index < taskArray.length) {
            //insert all the tasks till the current time
            while (index < taskArray.length && taskArray[index].enqTime <= currTime) {
                pq.add(taskArray[index]);
                index++;
            }

            if (!pq.isEmpty()) {
                //pick up one task to process
                // update the current time
                Task minTask = pq.poll();
                output[outputIndex++] = minTask.index;
                currTime += minTask.procTime;
            } else {
                if (index < taskArray.length) {
                    currTime = taskArray[index].enqTime;
                }
            }
        }
        return output;
    }

    private static class Task implements Comparable {
        public int enqTime;
        public int procTime;
        public int index;

        public Task(int enqTime, int procTime, int index) {
            this.enqTime = enqTime;
            this.procTime = procTime;
            this.index = index;
        }

        @Override
        public int compareTo(Object o) {
            return Integer.compare(this.procTime, Task.class.cast(o).procTime);
        }
    }
}
