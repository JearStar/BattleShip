package player;

import model.Board;
import model.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    List<Position> prevPositions;
    Board playerBoard;

    public Player(int size) {
        prevPositions = new ArrayList<>();
        playerBoard = new Board(size);
    }

    public abstract Position getNextMove();
}
