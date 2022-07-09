package com.poc.code.psds.stack;

import java.util.List;
import java.util.Stack;

/*
636. Exclusive Time of Functions
Medium

1046

1793

Add to List

Share
On a single-threaded CPU, we execute a program containing n functions. Each function has a unique ID between 0 and n-1.

Function calls are stored in a call stack: when a function call starts, its ID is pushed onto the stack, and when a function call ends,
its ID is popped off the stack. The function whose ID is at the top of the stack is the current function being executed. Each time a
function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.

You are given a list logs, where logs[i] represents the ith log message formatted as a string "{function_id}:{"start" | "end"}:{timestamp}".
For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3, and "1:end:2" means a function
call with function ID 1 ended at the end of timestamp 2. Note that a function can be called multiple times, possibly recursively.

A function's exclusive time is the sum of execution times for all function calls in the program. For example, if a function is called twice,
one call executing for 2 time units and another call executing for 1 time unit, the exclusive time is 2 + 1 = 3.

Return the exclusive time of each function in an array, where the value at the ith index represents the exclusive time for the function
with ID i.
 */

public class ExclusiveTimeOfFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        Function[] func = new Function[logs.size()];
        int index = 0;
        for (String log : logs) {
            String[] l = log.split(":");
            func[index++] = new Function(Integer.valueOf(l[0]), l[1], Integer.valueOf(l[2]));
        }

        int[] exTime = new int[n];
        Stack<Function> stk = new Stack();
        int lastExecutionEndTime = 0;
        for (Function fn : func) {
            if ("start".equals(fn.type)) {
                //calculate time for which last job in stack had run
                if (stk.size() > 0) {
                    Function runningFn = stk.peek();
                    if (fn.time - lastExecutionEndTime > 0) {
                        exTime[runningFn.id] += fn.time - lastExecutionEndTime;
                    }
                    lastExecutionEndTime = fn.time;
                }
                stk.push(fn);
            } else {
                Function startFn = stk.pop();
                exTime[startFn.id] += fn.time - lastExecutionEndTime + 1;
                lastExecutionEndTime = fn.time + 1;
            }
        }
        return exTime;
    }

    class Function {
        public int id;
        public String type;
        public int time;

        public Function(int id, String type, int time) {
            this.id = id;
            this.type = type;
            this.time = time;
        }
    }
}
