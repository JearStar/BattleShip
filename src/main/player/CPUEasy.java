package player;


import model.Position;

public class CPUEasy extends CPU {

    public CPUEasy(int size) {
        super(size);
    }

    @Override
    public Position getNextMove() {
        return this.posToVisit.get(posToVisit.size() - 1);
    }

}
