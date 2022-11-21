package com.poc.code.practices.designPatterns.Creational.factory.approach1;

import java.util.List;

public class RoomWithBom extends Room{
    private boolean isBombed;

    public RoomWithBom(Wall wall1, Wall wall2, Wall wall3, Wall wall4, List<Door> doors, boolean isBombed) {
        super(wall1, wall2, wall3, wall4, doors);
        this.isBombed = isBombed;
    }
}
