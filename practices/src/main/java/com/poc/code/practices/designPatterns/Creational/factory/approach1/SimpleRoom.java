package com.poc.code.practices.designPatterns.Creational.factory.approach1;

import java.util.List;

public class SimpleRoom extends Room{
    public SimpleRoom(Wall wall1, Wall wall2, Wall wall3, Wall wall4, List<Door> doors) {
        super(wall1, wall2, wall3, wall4, doors);
    }
}
