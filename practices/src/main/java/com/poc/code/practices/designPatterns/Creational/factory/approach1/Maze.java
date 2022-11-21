package com.poc.code.practices.designPatterns.Creational.factory.approach1;

import java.util.List;

public class Maze {
    private List<Room> rooms;
    private List<Door> walls;

    public Maze(List<Room> rooms, List<Door> walls) {
        this.rooms = rooms;
        this.walls = walls;
    }
}
