package com.poc.code.design.SubDirectories;

import java.util.List;

public class CommandService {
    private Directory currentDir;

    public CommandService(Directory currentDir) {
        this.currentDir = currentDir;
    }

    public void Dir() {
        System.out.println(currentDir.getPwd());
        List<String> subDir = currentDir.getSubDir();
        subDir.stream().forEach(e -> System.out.println(e));
    }

    public void makeDirectory(String name) {
        currentDir.addSubDir(name);
    }

    public void chageDirectory(String name) {
        Directory d = currentDir.getSubDirectory(name);
        if (d != null) {
            currentDir = d;
        } else {
            System.out.println("Cannot change subdirectory");
        }

    }

    public void up() {
        if (currentDir.getParent() != null) {
            currentDir = currentDir.getParent();
        }

    }

}
