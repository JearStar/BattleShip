package model;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    int size;
    Position position;
    boolean orientation;
    boolean sunken;
    List<Position> shipCells;
    List<Position> hits;

    public Ship(int x, int y, boolean orientation, int size) {
        position = new Position(x, y);
        this.orientation = orientation;
        this.size = size;
        sunken = false;
        shipCells = new ArrayList<>();
        hits = new ArrayList<>();
        setShipCells();
    }

    public void setShipCells() {
        List<Position> result = new ArrayList<>();
        if (orientation == Board.RIGHT_ORIENTATION) {
            for (int i = position.getX(); i < (position.getX() + size); i++) {
                result.add(new Position(i, position.getY()));
            }
        } else {
            for (int i = position.getY(); i < (position.getY() + size); i++) {
                result.add(new Position(position.getX(), i));
            }
        }
        shipCells = result;
    }

    //MODIFIES: this
    //EFFECTS: adds one of the ship's positions to hit list
    public void hitShip(Position position) {
        if (shipCells.contains(position)) {
            if (!hits.contains(position)) {
                hits.add(position);
            }
        }
        if (this.hits.size() == this.shipCells.size()) {
            sinkShip();
        }
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    public void switchOrientation() {
        if (this.orientation == Board.RIGHT_ORIENTATION) {
            this.orientation = Board.DOWN_ORIENTATION;
        } else {
            this.orientation = Board.RIGHT_ORIENTATION;
        }
    }

    public boolean isSunken() {
        return sunken;
    }

    //EFFECTS: returns hits list
    public List<Position> getHits() {
        return this.hits;
    }

    //EFFECTS: returns the ship's cells
    public List<Position> getShipCells() {
        return this.shipCells;
    }

    //EFFECTS: returns the ship's head position
    public Position getPosition() {
        return this.position;
    }

    //EFFECTS: returns orientation of this ship (right is true, down is false)
    public boolean getOrientation() {
        return this.orientation;
    }

    //EFFECTS: returns size of this ship
    public int getSize() {
        return this.size;
    }

    //EFFECTS: sets sunken to true
    public void sinkShip() {
        sunken = true;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
