package ui;

import model.Board;
import model.Ship;
import player.CPUEasy;
import player.CPUMedium;
import player.HumanPlayer;
import player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BattleShipGame {
    public static final int GRID_SIZE = 10;
    Player you;
    Player opponent;
    Scanner scanner = new Scanner(System.in);

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
        you = new HumanPlayer(GRID_SIZE);
        while (true) {
            System.out.println("Please enter 'easy' or 'medium' to play against a CPU opponent of that difficulty. Please" +
                    " enter 'human' to play against another person.");
            String choice = scanner.nextLine();
            if (choice.equals("easy")) {
                opponent = new CPUEasy(GRID_SIZE);
                startGame();
                break;
            } else if (choice.equals("medium")) {
                opponent = new CPUMedium(GRID_SIZE);
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

    }

    public void shipPlacement() {
        List<Ship> listOfShips = new ArrayList<>();
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

        List<Ship> listOfShips2 = new ArrayList<>(listOfShips);



    }

    public boolean isGameOver() {
        return false;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
