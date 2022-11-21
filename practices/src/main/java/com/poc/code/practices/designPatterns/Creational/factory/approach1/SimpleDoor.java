package com.poc.code.practices.designPatterns.Creational.factory.approach1;

import java.util.Objects;

public class SimpleDoor implements Door{
    int room1;
    int room2;

    public SimpleDoor(int room1, int room2) {
        this.room1 = room1;
        this.room2 = room2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleDoor that = (SimpleDoor) o;
        return room1 == that.room1 && room2 == that.room2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(room1, room2);
    }

    @Override
    public String toString() {
        return "SimpleDoor{" +
                "room1=" + room1 +
                ", room2=" + room2 +
                '}';
    }
}
