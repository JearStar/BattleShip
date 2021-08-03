package player;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    List<Position> prevPositions;

    public Player() {
        prevPositions = new ArrayList<>();
    }

    public abstract Position getNextMove();
}
