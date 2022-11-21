package com.poc.code.practices.designPatterns.Creational.factory.approach1;

public class BombedMazeGame extends MazeGame {
    @Override
    Wall createWall(int n) {
        return new BombedWall();
    }

    @Override
    Door createDoor(int room1, int room2) {
        return new BombedDoor(room1, room2, true);
    }

    @Override
    Room createRoom(Wall w1, Wall w2, Wall w3, Wall w4) {
        return new RoomWithBom(w1, w2, w3, w4, null, true);
    }
}
