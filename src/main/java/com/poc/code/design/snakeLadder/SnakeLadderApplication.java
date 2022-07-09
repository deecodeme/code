package com.poc.code.design.snakeLadder;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class SnakeLadderApplication {
    public static void main(String[] args) {
        Game game = configureLocally();
        game.start();
    }

    private static Game configureLocally() {
        GameBoard gameBoard = new GameBoardImpl(100);
        gameBoard.addSnake(62, 5);
        gameBoard.addSnake(33, 6);
        gameBoard.addSnake(49, 9);
        gameBoard.addSnake(88, 16);
        gameBoard.addSnake(41, 20);
        gameBoard.addSnake(56, 53);
        gameBoard.addSnake(98, 64);
        gameBoard.addSnake(93, 73);
        gameBoard.addSnake(95, 75);

        gameBoard.addStair(2, 37);
        gameBoard.addStair(27, 46);
        gameBoard.addStair(10, 32);
        gameBoard.addStair(51, 68);
        gameBoard.addStair(61, 79);
        gameBoard.addStair(65, 84);
        gameBoard.addStair(71, 91);
        gameBoard.addStair(81, 100);

        Dice dice = new DiceImpl();
        List<Player> playerList = new ArrayList<>();
        playerList.add(new PlayerImpl("Gaurav"));
        playerList.add(new PlayerImpl("Sagar"));
        Game game = GameFactory.createAGame(gameBoard, dice, playerList);
        return game;
    }

    private static Game configureGameFromCommandLIne() {
        GameBoard gameBoard = new GameBoardImpl(100);
        Dice dice = new DiceImpl();
        List<Player> playerList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter number of snakes");
        int snakesCount = 0;
        if (scanner.hasNextInt()) {
            snakesCount = scanner.nextInt();
        }
        System.out.println("snakeCount: " + snakesCount);

        scanner = new Scanner(System.in);
        while (snakesCount >= 0 && scanner.hasNextLine()) {
            System.out.println("Enter snake below");
            String input = scanner.nextLine();
            String[] snakePos = input.split(" ");
            if (snakePos.length != 2) {
                continue;
            }
            try {
                Integer head = Integer.parseInt(snakePos[0]);
                Integer tail = Integer.parseInt(snakePos[1]);
                gameBoard.addSnake(head, tail);
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid snake format");
                continue;
            }
            snakesCount--;
        }

        System.out.println("Please enter number of stairs");
        scanner = new Scanner(System.in);
        int stairsCount = 0;
        if (scanner.hasNextInt()) {
            stairsCount = scanner.nextInt();
        }

        scanner = new Scanner(System.in);
        while (stairsCount > 0 && scanner.hasNextLine()) {
            System.out.println("Please enter stair");
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input == null || input.length() == 0) {
                    System.out.println("Please enter a valid stair format");
                    continue;
                }
                try {
                    String[] stairPos = input.split(" ");
                    Integer bottom = Integer.parseInt(stairPos[0]);
                    Integer top = Integer.parseInt(stairPos[1]);
                    gameBoard.addStair(bottom, top);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid stair format");
                    continue;
                }
            }
            stairsCount--;
        }

        System.out.println("Please enter number of players");
        scanner = new Scanner(System.in);
        int playersCount = 0;
        if (scanner.hasNextInt()) {
            playersCount = scanner.nextInt();
        }
        scanner = new Scanner(System.in);
        while (playersCount > 0 && scanner.hasNextLine()) {
            String player = scanner.nextLine();
            if (player != null) {
                playerList.add(new PlayerImpl(player));
            } else {
                continue;
            }
            playersCount--;
        }

        Game game = GameFactory.createAGame(gameBoard, dice, playerList);
        return game;
    }
}
