package com.poc.code.practices.designPatterns.Creational.factory.approach1;

public class SimpleMazeGame extends MazeGame {
    @Override
    Wall createWall(int n) {
        return new SimpleWall();
    }

    @Override
    Door createDoor(int room1, int room2) {
        return new SimpleDoor(room1, room2);
    }

    @Override
    Room createRoom(Wall w1, Wall w2, Wall w3, Wall w4) {
        return new SimpleRoom(w1, w2, w3, w4, null);
    }
}
