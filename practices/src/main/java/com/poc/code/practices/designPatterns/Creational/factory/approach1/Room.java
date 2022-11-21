package com.poc.code.practices.designPatterns.Creational.factory.approach1;

import java.util.List;

public abstract class Room {
    private Wall wall1, wall2, wall3, wall4;
    private List<Door> doors;

    public Room(Wall wall1, Wall wall2, Wall wall3, Wall wall4, List<Door> doors) {
        this.wall1 = wall1;
        this.wall2 = wall2;
        this.wall3 = wall3;
        this.wall4 = wall4;
        this.doors = doors;
    }
}
