package com.poc.code.practices.design.SubDirectories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory {
    private String name;
    private Directory parent;
    private String pwd;
    private Map<String, Directory> subDir;

    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        subDir = new HashMap<>();
        if (parent == null) {
            this.pwd = "/";
        } else {
            this.pwd = parent.getPwd() + name + "/";
        }
    }

    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public String getPwd() {
        return pwd;
    }


    public List<String> getSubDir() {
        List<String> lis = new ArrayList<>();
        System.out.println("len: " + subDir.values().size());
        for (Directory dir : subDir.values()) {
            lis.add(dir.name);
        }
        lis.sort(String::compareToIgnoreCase);
        return lis;
    }

    public String addSubDir(String dir) {
        String error = null;
        if (subDir.containsKey(dir)) {
            error = new String("Subdirectories already exists");
        } else {
            Directory d = new Directory(dir, this);
            this.subDir.put(name, d);
        }
        return error;
    }

    public Directory getSubDirectory(String name) {
        Directory d = null;
        boolean doesExist = this.subDir.values().stream().anyMatch(e -> e.equals(name));
        if (doesExist) {
            d = subDir.get(name);
        }
        return d;
    }
}

