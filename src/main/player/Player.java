package player;

import model.Board;
import model.Position;
import model.Ship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Player {
    public static boolean RANDOM_PLACEMENT = false;
    public static boolean MANUAL_PLACEMENT = true;
    List<Position> prevPositions;
    Board playerBoard;
    Board enemyBoard;

    public Player(int size) {
        prevPositions = new ArrayList<>();
        playerBoard = new Board(size);
        enemyBoard = new Board(size);
    }

    public abstract Position getNextMove();

    public Board getPlayerBoard() {
        return this.playerBoard;
    }

    public boolean placeShipRandomly(Ship ship, List<Position> posWorklist,
                                     List<Position> tried) {
        Random rand = new Random();
        if (tried.isEmpty()) {
            int x = rand.nextInt(playerBoard.getBoard().length);
            int y = rand.nextInt(playerBoard.getBoard().length);
            Position newPos = new Position(x, y);
            ship.setPosition(newPos);

            if (playerBoard.addShip(ship)) {
                return true;
            } else {
                tried.add(ship.getPosition());
                posWorklist.addAll(generateNextShipPlacement(ship.getPosition(), tried));
                placeShipRandomly(ship, posWorklist, tried);
            }
        } else if (!posWorklist.isEmpty()) {
            ship.setPosition(posWorklist.get(posWorklist.size() - 1));
            int whichOrientationToTryFirst = rand.nextInt(2);

            if (whichOrientationToTryFirst == 0) {
               ship.setOrientation(Board.RIGHT_ORIENTATION);
            } else {
                ship.setOrientation(Board.DOWN_ORIENTATION);
            }

            if (playerBoard.addShip(ship)) {
                return true;
            } else {
                ship.switchOrientation();
                if (playerBoard.addShip(ship)) {
                    return true;
                } else {
                    tried.add(ship.getPosition());
                    posWorklist.remove(posWorklist.size() - 1);
                    posWorklist.addAll(generateNextShipPlacement(ship.getPosition(), tried));
                    placeShipRandomly(ship, posWorklist, tried);
                }
            }
        } else {
            playerBoard.removeAllShips();
            placeShipRandomly(ship, new ArrayList<>(), new ArrayList<>());
        }
        return false;
    }

    public List<Position> generateNextShipPlacement(Position position, List<Position> tried) {
        int xCoord = position.getX();
        int yCoord = position.getY();
        int xPlusOne;
        int yPlusOne;
        int xMinusOne;
        int yMinusOne;
        if (xCoord == playerBoard.getBoard().length - 1) {
            xPlusOne = 0;
            xMinusOne = xCoord - 1;
        } else if (xCoord == 0) {
            xPlusOne = xCoord + 1;
            xMinusOne = playerBoard.getBoard().length - 1;
        } else {
            xPlusOne = xCoord + 1;
            xMinusOne = xCoord - 1;
        }

        if (yCoord == playerBoard.getBoard().length - 1) {
            yPlusOne = 0;
            yMinusOne = yCoord - 1;
        } else if (yCoord == 0) {
            yPlusOne = yCoord + 1;
            yMinusOne = playerBoard.getBoard().length - 1;
        } else {
            yPlusOne = yCoord + 1;
            yMinusOne = yCoord - 1;
        }

        List<Position> temp = new ArrayList<>();
        List<Position> result = new ArrayList<>();

        Position xPlusOnePos = new Position(xPlusOne, yCoord);
        Position xMinusOnePos = new Position(xMinusOne, yCoord);
        Position yPlusOnePos = new Position(xCoord, yPlusOne);
        Position yMinusOnePos = new Position(xCoord, yMinusOne);

        temp.add(xPlusOnePos);
        temp.add(xMinusOnePos);
        temp.add(yPlusOnePos);
        temp.add(yMinusOnePos);

        for (Position p : temp) {
            if (!tried.contains(p)) {
                result.add(p);
            }
        }
        Collections.shuffle(result);
        return result;
    }

    public abstract boolean placeShip(Boolean type, List<Ship> ships);
}
