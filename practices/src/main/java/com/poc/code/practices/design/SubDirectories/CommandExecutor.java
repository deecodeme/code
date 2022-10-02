package com.poc.code.practices.design.SubDirectories;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CommandExecutor {
    private static int maxThreads = 4;
    public static void main(String args[]){
        Executor executor = Executors.newFixedThreadPool(maxThreads);
        CommandParser commandParser = new CommandParser();

        Thread t = new Thread(){
            @Override
            public void run() {
                commandParser.execute(Command.MKDIR.getValue(), "c1");
                commandParser.execute(Command.MKDIR.getValue(), "c2");
                commandParser.execute(Command.DIR.getValue(), null);
            }
        };
        executor.execute(t);

    }
}
