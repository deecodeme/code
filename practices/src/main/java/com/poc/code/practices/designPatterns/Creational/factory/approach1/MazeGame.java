package com.poc.code.practices.designPatterns.Creational.factory.approach1;

import java.util.List;

public abstract class MazeGame {
    abstract Wall createWall(int n);

    abstract Door createDoor(int room1, int room2);

    abstract Room createRoom(Wall w1, Wall w2, Wall w3, Wall w4);

    Maze createMaze() {
        Wall w11 = createWall(1);
        Wall w12 = createWall(2);
        Wall w13 = createWall(3);
        Wall w14 = createWall(4);

        Wall w21 = createWall(1);
        Wall w22 = createWall(2);
        Wall w23 = createWall(3);
        Wall w24 = createWall(4);

        Door door = createDoor(1, 2);
        Room room1 = createRoom(w11, w12, w13, w14);
        Room room2 = createRoom(w21, w22, w23, w24);
        return new Maze(List.of(room1, room2), List.of(door));
    }
}
