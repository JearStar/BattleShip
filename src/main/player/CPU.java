package player;

import model.Position;
import model.Ship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CPU extends Player {
    List<Position> posToVisit;

    public CPU(int size) {
        super(size);
        posToVisit = new ArrayList<>();
        generateFirstMoves();
    }

    public List<Position> getPosToVisit() {
        return this.posToVisit;
    }

    public void generateFirstMoves() {
        for (int i = 0; i < super.playerBoard.getBoard().length; i++) {
            for (int j = 0; j < super.playerBoard.getBoard().length; j++) {
                posToVisit.add(new Position(i, j));
            }
        }
        Collections.shuffle(posToVisit);
    }

    public void placeShip(Boolean type, List<Ship> ships) {
        for (Ship s : ships) {
            placeShipRandomly(s, new ArrayList<>(), new ArrayList<>());
        }
        for (Ship s : this.getPlayerBoard().getShipsOnBoard()) {
            s.setShipCells();
        }
    }

    public void takeTurn() {
//        System.out.println(this.getPlayerBoard().boardToString());
        markShot(getNextMove());
    }
}
