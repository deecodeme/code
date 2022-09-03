package com.poc.code.design.SubDirectories;

import java.util.Arrays;

public class CommandParser{
    private CommandService commandService;

    public CommandParser() {
        Directory root = new Directory("root", null);
        this.commandService = new CommandService(root);
    }

    public void parseCommand(String... args) {
        if (args.length == 0 || args.length > 2) {
            System.out.println("Invalid Arguments");
        } else if (args.length == 2) {
            execute(args[0], args[1]);
        } else {
            execute(args[0], null);
        }
    }

    public void execute(String command, String arg) {
        if (!isAValidCommand(command)) {
            return;
        }

        if (Command.CD.getValue().equals(command)) {
            commandService.chageDirectory(command);
        }

        if (Command.DIR.getValue().equals(command)) {
            commandService.Dir();
        }

        if (Command.MKDIR.getValue().equals(command)) {
            commandService.makeDirectory(arg);
        }
        if (Command.UP.getValue().equals(command)) {
            commandService.up();
        }
    }

    private boolean isAValidCommand(String command) {
        return Arrays.stream(Command.values()).anyMatch(e -> e.equals(command));
    }


}
