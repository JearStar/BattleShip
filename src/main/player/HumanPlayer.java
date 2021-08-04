package player;

import model.Board;
import model.Position;

import java.util.Scanner;

public class HumanPlayer extends Player{
    Board enemyBoard;

    public HumanPlayer(int size) {
        super(size);
        enemyBoard = new Board(size);
    }

    @Override
    public Position getNextMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter x coordinate of your shot ");
        int x = scanner.nextInt();
        System.out.println("Please enter y coordinate of your shot ");
        int y = scanner.nextInt();
        return new Position(x, y);
    }
}
