package com.poc.code.ps.misc;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
https://leetcode.com/problems/design-snake-game/

 */
public class SnakeGame {
    private int width;
    private int height;
    private int score;

    private int foodIndex;

    private final int[][] food;
    private final LinkedList<Integer> snake;
    private final Map<Integer, Boolean> snakeLookup;

    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.score = 0;
        this.foodIndex = 0;
        this.food = food;
        this.snake = new LinkedList<Integer>();
        this.snakeLookup = new HashMap<>();
        this.snake.addFirst(0);
        snakeLookup.put(0, true);

    }

    public int move(String direction) {
        Map.Entry<Boolean, Integer> nextMove = getNextValidMove(direction);
        if (!nextMove.getKey()) {
            return -1;
        }
        if (isFood(nextMove.getValue())) {
            this.score++;
        } else {
            this.snakeLookup.remove(snake.getLast());
            this.snake.removeLast();
        }
        this.snakeLookup.put(nextMove.getValue(), true);
        this.snake.addFirst(nextMove.getValue());
        return this.score;
    }

    private Map.Entry<Boolean, Integer> getNextValidMove(String direction) {
        int iNext = this.snake.peek() / this.width;
        int jNext = this.snake.peek() % this.width;
        switch (direction) {
            case "L" -> jNext--;
            case "R" -> jNext++;
            case "U" -> iNext--;
            case "D" -> iNext++;
            default -> throw new IllegalArgumentException("Not a valid direction");
        }
        if (iNext < 0 || iNext >= this.height || jNext < 0 || jNext >= this.width) {
            return new AbstractMap.SimpleImmutableEntry<>(false, -1);
        }
        int nextMove = iNext * this.width + jNext;
        if (this.snakeLookup.containsKey(nextMove)) {
            return new AbstractMap.SimpleImmutableEntry<>(false, -1);
        }
        return new AbstractMap.SimpleImmutableEntry<>(true, nextMove);
    }

    private boolean isFood(Integer pos) {
        int i = pos / this.width;
        int j = pos % this.width;
        if (this.foodIndex < this.food.length && (this.food[this.foodIndex][0] == i && this.food[this.foodIndex][1] == j)) {
            this.foodIndex++;
            return true;
        }
        return false;
    }
}
