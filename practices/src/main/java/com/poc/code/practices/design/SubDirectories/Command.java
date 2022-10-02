package com.poc.code.practices.design.SubDirectories;

public enum Command {
    DIR("dir"),
    MKDIR("mkdir"),
    CD("cd"),
    UP("up");

    public String getValue() {
        return value;
    }

    private String value;

    Command(String value) {
        this.value = value;

    }


}
