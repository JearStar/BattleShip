package model;

public class Position {
    int xCoord;
    int yCoord;

    public Position(int x, int y) {
        this.xCoord = x;
        this.yCoord = y;
    }

    public void setX(int x) {
        this.xCoord = x;
    }

    public void setY(int y) {
        this.yCoord = y;
    }

    public int getX() {
        return this.xCoord;
    }

    public int getY() {
        return this.yCoord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (xCoord != position.xCoord) return false;
        return yCoord == position.yCoord;
    }

    @Override
    public int hashCode() {
        int result = xCoord;
        result = 31 * result + yCoord;
        return result;
    }
}
