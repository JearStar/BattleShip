package ui;

import model.Board;
import model.Position;
import model.Ship;

public class main {
    public static void main(String[] args) {


        Ship ship2 = new Ship(1, 2, Board.RIGHT_ORIENTATION, 7);
        Ship ship3 = new Ship(3, 5, Board.DOWN_ORIENTATION, 6);
        Board board = new Board(10);

        board.printBoard();
        board.addShip(ship2);
        board.printBoard();
        board.addShip(ship3);
        board.printBoard();


        ship2.hitShip(ship2.getShipCells().get(0));
        ship2.hitShip(new Position(1, 2));
        ship2.hitShip(new Position(6, 6));
        for (Position p : ship2.getHits()) {
            System.out.println("(" + p.getX() + ", " + p.getY() + ")");
        }
    }
}
