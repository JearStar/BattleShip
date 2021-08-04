package player;

import model.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CPU extends Player {
    List<Position> posToVisit;

    public CPU(int size) {
        super(size);
        posToVisit = new ArrayList<>();
    }

    public void generateFirstMoves() {
        for (int i = 0; i < super.playerBoard.getBoard().length; i++) {
            for (int j = 0; j < super.playerBoard.getBoard().length; j++) {
                posToVisit.add(new Position(i, j));
            }
        }
        Collections.shuffle(posToVisit);
    }
}
