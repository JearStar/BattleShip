package ui;

import model.Board;
import model.Ship;
import player.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BattleShipGame {
    public static final int GRID_SIZE = 10;
    Player playerOne;
    Player playerTwo;
    Scanner scanner = new Scanner(System.in);
    List<Ship> listOfShips;
    List<Ship> listOfShips2;

    public BattleShipGame() {
        runApp();
    }

    public void runApp() {
        while (true) {
            System.out.println("Welcome to BattleShip. To begin playing, please enter 'play'. To quit the game, " +
                    "please enter 'quit'.");
            String choice = scanner.nextLine();
            if (choice.toLowerCase().equals("play")) {
                selectMode();
            } else if (choice.toLowerCase().equals("quit")) {
                break;
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    public void selectMode() {
        playerOne = new HumanPlayer(GRID_SIZE);
        while (true) {
            System.out.println("Please enter 'easy' or 'medium' to play against a CPU opponent of that difficulty. Please" +
                    " enter 'human' to play against another person.");
            String choice = scanner.nextLine();
            if (choice.equals("easy")) {
                playerTwo = new CPUEasy(GRID_SIZE);
                startGame();
                break;
            } else if (choice.equals("medium")) {
                playerTwo = new CPUMedium(GRID_SIZE);
                startGame();
                break;
            } else if (choice.equals("human")) {
                playerTwo = new HumanPlayer(GRID_SIZE);
                startGame();
                break;
            } else if (choice.equals("back")) {
                break;
            } else {
                System.out.println("Selection not valid...");
            }

        }
    }


    public void startGame() {
        playerOne.setEnemyBoardActual(playerTwo.getPlayerBoard());
        playerTwo.setEnemyBoardActual(playerOne.getPlayerBoard());
        shipPlacement();
        while (!isGameOver()) {
            while (true) {
                System.out.println("It is Player 1's turn, press enter to continue");
                String command = scanner.nextLine();
                if (command.equals("")) {
                    break;
                }
            }
            playerOne.takeTurn();

            while (true) {
                System.out.println("Press enter to continue.");
                String command = scanner.nextLine();
                if (command.equals("")) {
                    break;
                }
            }
            clearScreen();
            while (true) {
                System.out.println("It is Player 2's turn, press enter to continue");
                String command = scanner.nextLine();
                if (command.equals("")) {
                    break;
                }
            }

            playerTwo.takeTurn();
            while (true) {
                System.out.println("Press enter to continue.");
                String command = scanner.nextLine();
                if (command.equals("")) {
                    break;
                }
            }
            clearScreen();
        }
        System.out.println("The winner is " + getWinner() + "! Have a nice day!");
    }

    public String getWinner() {
        for (Ship s : listOfShips) {
            if (!s.isSunken()) {
                return "Player 1";
            }
        }
        return "Player 2";
    }

    public void shipPlacement() {
        listOfShips = new ArrayList<>();
        Ship carrier = new Ship(0, 0, Board.RIGHT_ORIENTATION, 5);
        Ship battleship = new Ship(0, 0, Board.RIGHT_ORIENTATION, 4);
        Ship cruiser = new Ship(0, 0, Board.RIGHT_ORIENTATION, 3);
        Ship submarine = new Ship(0, 0, Board.RIGHT_ORIENTATION, 2);
        Ship destroyer = new Ship(0, 0, Board.RIGHT_ORIENTATION, 1);
        listOfShips.add(carrier);
        listOfShips.add(battleship);
        listOfShips.add(cruiser);
        listOfShips.add(submarine);
        listOfShips.add(destroyer);

        listOfShips2 = new ArrayList<>(listOfShips);


        while (true) {
            System.out.println("Would Player 1 like to place the ships 'randomly' or 'manually'?");
            String type = scanner.nextLine().toLowerCase();
            if (type.equals("randomly")) {
                playerOne.placeShip(Player.RANDOM_PLACEMENT, listOfShips);
                break;
            } else if (type.equals("manually")) {
                playerOne.placeShip(Player.MANUAL_PLACEMENT, listOfShips);
                break;
            } else {
                System.out.println("Selection not valid...");
            }
        }

        while (true) {
            System.out.println("Press enter to continue.");
            String command = scanner.nextLine();
            if (command.equals("")) {
                break;
            }
        }
        clearScreen();

        if (playerTwo instanceof CPU) {
            playerTwo.placeShip(Player.RANDOM_PLACEMENT, listOfShips2);
        } else {
            System.out.println("Would Player 2 like to place the ships 'randomly' or 'manually'?");
            String typeP2 = scanner.nextLine().toLowerCase();
            while (true) {
                if (typeP2.equals("randomly")) {
                    playerTwo.placeShip(Player.RANDOM_PLACEMENT, listOfShips2);
                    break;
                } else if (typeP2.equals("manually")) {
                    playerTwo.placeShip(Player.MANUAL_PLACEMENT, listOfShips2);
                    break;
                } else {
                    System.out.println("Selection not valid...");
                }
            }
        }


    }

    public boolean isGameOver() {
        for (Ship s : listOfShips) {
            if (!s.isSunken()) {
                return false;
            }
        }

        for (Ship s : listOfShips2) {
            if (!s.isSunken()) {
                return false;
            }
        }
        return true;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
