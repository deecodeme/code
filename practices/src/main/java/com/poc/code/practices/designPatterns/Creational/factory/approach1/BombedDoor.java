package com.poc.code.practices.designPatterns.Creational.factory.approach1;

import java.util.Objects;

public class BombedDoor implements Door {
    private int room1;
    private int room2;
    private boolean isBombed;

    public BombedDoor(int room1, int room2, boolean isBombed) {
        this.room1 = room1;
        this.room2 = room2;
        this.isBombed = isBombed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BombedDoor that = (BombedDoor) o;
        return room1 == that.room1 && room2 == that.room2 && isBombed == that.isBombed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(room1, room2, isBombed);
    }

    @Override
    public String toString() {
        return "BombedDoor{" +
            "room1=" + room1 +
            ", room2=" + room2 +
            ", isBombed=" + isBombed +
            '}';
    }
}
