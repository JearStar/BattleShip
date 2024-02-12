package player;


import model.Position;

public class CPUEasy extends CPU {

    public CPUEasy(int size) {
        super(size);
    }

    @Override
    public Position getNextMove() {
        Position ret = posToVisit.get(posToVisit.size() - 1);
        posToVisit.remove(posToVisit.size() - 1);
        return ret;
    }



}
