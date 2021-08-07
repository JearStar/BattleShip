package player;


import model.Position;

import java.util.ArrayList;
import java.util.List;

public class CPUMedium extends CPU {

    List<Position> urgentPositionsToVisit;

    public CPUMedium(int size) {
        super(size);
        urgentPositionsToVisit = new ArrayList<>();
    }

    @Override
    public Position getNextMove() {
        return null;
    }
}
