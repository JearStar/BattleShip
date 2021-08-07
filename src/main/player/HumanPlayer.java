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
                return new Position(x, y);
            }
        }
    }

    @Override
    public boolean placeShip(Boolean type, List<Ship> ships) {
        if (type == Player.RANDOM_PLACEMENT) {
            for (Ship s : ships) {
                if (!placeShipRandomly(s, new ArrayList<>(), new ArrayList<>())) {
                    return false;
                }
            }
        } else {
            return doManualPlacement(ships);
        }
        return true;
    }

    public boolean doManualPlacement(List<Ship> ships) {
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
        return true;
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
