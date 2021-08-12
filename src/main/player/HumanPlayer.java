package player;

import model.Position;
import model.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {
    Scanner scanner = new Scanner(System.in);

    public HumanPlayer(int size) {
        super(size);
    }

    @Override
    public Position getNextMove() {
        while (true) {
            System.out.println("Please enter x coordinate of your shot ");
            int x = scanner.nextInt();
            System.out.println("Please enter y coordinate of your shot ");
            int y = scanner.nextInt();

            if (!isCoordinateOnBoard(x, y)) {
                System.out.println("That coordinate is not on the board!");
            } else if (alreadyMadeMove(x, y)) {
                System.out.println("You already shot here!");
            } else {
                Position move = new Position(x, y);
                return move;
            }
        }
    }

    @Override
    public void takeTurn() {
        printPlayerBoard();
        printEnemyBoard();
        markShot(getNextMove());
        printEnemyBoard();

    }


    @Override
    public void placeShip(Boolean type, List<Ship> ships) {
        if (type == Player.RANDOM_PLACEMENT) {
            doRandomPlacement(ships);
        } else {
            doManualPlacement(ships);
        }
    }



    public boolean doRandomPlacement(List<Ship> ships) {
        playerBoard.removeAllShips();
        for (Ship s : ships) {
            placeShipRandomly(s, new ArrayList<>(), new ArrayList<>());
        }
        for (Ship s : ships) {
            s.setShipCells();
        }
        while (true) {
            System.out.println(playerBoard.boardToString());
            System.out.println("Are you 'ok' with your placement? Or would you like 'redo'?");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("ok")) {
                return true;
            } else if (choice.equals("redo")) {
                return doRandomPlacement(ships);
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }
    public boolean doManualPlacement(List<Ship> ships) {
        playerBoard.removeAllShips();
        for (Ship s : ships) {
            while (true) {
                System.out.println("Please enter x coordinate to place your " + s.getSize() + "-unit ship");
                int x = scanner.nextInt();
                System.out.println("Please enter y coordinate to place your " + s.getSize() + "-unit ship");
                int y = scanner.nextInt();
                s.setPosition(new Position(x, y));
                if (playerBoard.addShip(s)) {
                    break;
                } else {
                    System.out.println("Please try again");
                }
            }
        }
        while (true) {
            System.out.println(playerBoard.boardToString());
            System.out.println("Are you 'ok' with your placement? Or would you like 'redo'?");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("ok")) {
                return true;
            } else if (choice.equals("redo")) {
                return doManualPlacement(ships);
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    //EFFECTS: returns true if coordinate is a valid coordinate on the board, false otherwise
    public boolean isCoordinateOnBoard(int x, int y) {
        return (x >= 0 && x < playerBoard.getBoard().length && y >= 0 && y < playerBoard.getBoard().length);
    }

    //EFFECTS: returns true if move was already made previously, false otherwise
    public boolean alreadyMadeMove(int x, int y) {
        Position pos = new Position(x, y);
        return prevPositions.contains(pos);
    }
}
