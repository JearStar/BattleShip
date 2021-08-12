package player;


import model.Board;
import model.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CPUMedium extends CPU {

    List<Position> urgentPositionsToVisit;

    public CPUMedium(int size) {
        super(size);
        urgentPositionsToVisit = new ArrayList<>();
    }

    @Override
    public Position getNextMove() {
        if (!urgentPositionsToVisit.isEmpty()) {
            Position shot = urgentPositionsToVisit.get(urgentPositionsToVisit.size() - 1);
            urgentPositionsToVisit.remove(shot);
            return shot;
        } else if (hitTargetFirstPositionInList()) {
            Position shot = posToVisit.get(0);
            addSurroundingCellsToUrgent(shot);
            posToVisit.remove(shot);
            return shot;
        } else {
            Position shot = posToVisit.get(0);
            posToVisit.remove(shot);
            return shot;
        }
    }



    public boolean hitTargetFirstPositionInList() {
        Position target = posToVisit.get(0);
        return (!enemyBoardActual.getBoard()[target.getY()][target.getX()].equals(Board.OPEN_SQUARE));
    }

    public void addSurroundingCellsToUrgent(Position position) {
        List<Position> result = new ArrayList<>();
        Position up;
        Position down;
        Position left;
        Position right;

        if (position.getY() != 0) {
            up = new Position(position.getX(), position.getY() - 1);
            result.add(up);
        }

        if (position.getY() != playerBoard.getBoard().length - 1) {
            down = new Position(position.getX(), position.getY() + 1);
            result.add(down);
        }

        if (position.getX() != 0) {
            left = new Position(position.getX() - 1, position.getY());
            result.add(left);
        }

        if (position.getX() != playerBoard.getBoard().length - 1) {
            right = new Position(position.getX() + 1, position.getY());
            result.add(right);
        }
        for (Position p : prevPositions) {
            if (result.contains(p)) {
                result.remove(p);
            }
        }

        for (Position p : result) {
            if (posToVisit.contains(p)) {
                posToVisit.remove(p);
            }
        }
        Collections.shuffle(result);
        urgentPositionsToVisit.addAll(result);
    }
}
