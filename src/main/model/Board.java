package model;

public class Board {
    String[][] board;
    public static boolean RIGHT_ORIENTATION = true;
    public static boolean DOWN_ORIENTATION = false;
    public static String OPEN_SQUARE = "#";


    public Board(int size) {
        this.board = new String[size][size];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = OPEN_SQUARE;
            }
        }
    }

    //EFFECTS: the 2D String array
    public String[][] getBoard() {
        return this.board;
    }

    //EFFECTS: prints board to the console
    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //MODIFIES: this.board
    //EFFECTS: places starting at given x and y and lays it out to the right (true) or downwards (false) depending on
    //         orientation. Returns a boolean to indicate if placement successful.
    public boolean addShip(Ship ship) {
        if (!isPositionOnBoard(ship.getPosition().getX(), ship.getPosition().getY()) ||
                (ship.getOrientation() == RIGHT_ORIENTATION && (ship.getPosition().getX() + ship.getSize() > board.length)) ||
                (ship.getOrientation() == DOWN_ORIENTATION && (ship.getPosition().getY() + ship.getSize() > board.length))) {
            System.out.println("That placement is out of bounds");
            return false;

        } else if (shipInTheWay(ship)) {
            System.out.println("There is a ship in the way of this placement");
            return false;

        } else if (ship.getOrientation() == RIGHT_ORIENTATION) {
            for (int i = ship.getPosition().getX(); i < ship.getPosition().getX() + ship.getSize(); i++) {
                board[ship.getPosition().getY()][i] = String.valueOf(ship.getSize());
            }
            System.out.println("ui.main.Tests.model.Ship placement successful");
        } else if (ship.getOrientation() == DOWN_ORIENTATION) {
            for (int i = ship.getPosition().getY(); i < ship.getPosition().getY() + ship.getSize(); i++) {
                board[i][ship.getPosition().getX()] = String.valueOf(ship.getSize());
            }
            System.out.println("ui.main.Tests.model.Ship placement successful");
        }
        return true;
    }


    //EFFECTS: returns true if there is a ship in the way of a ship placement
    private boolean shipInTheWay(Ship ship) {
        if (ship.getOrientation() == RIGHT_ORIENTATION) {
            for (int i = ship.getPosition().getY(); i < ship.getPosition().getX() + ship.getSize(); i++) {
                if (!board[ship.getPosition().getY()][i].equals(OPEN_SQUARE)) {
                    return true;
                }
            }
        } else {
            for (int i = ship.getPosition().getY(); i < ship.getPosition().getY() + ship.getSize(); i++) {
                if (!board[i][ship.getPosition().getX()].equals(OPEN_SQUARE)) {
                    return true;
                }
            }
        }
        return false;
    }

    //MODIFIES: this.board
    //EFFECTS: removes given ship off the board
    public void removeShip(Ship ship) {
        if (ship.getOrientation() == RIGHT_ORIENTATION) {
            for (int i = ship.getPosition().getX(); i < ship.getPosition().getX() + ship.getSize(); i++) {
                board[ship.getPosition().getY()][i] = OPEN_SQUARE;
            }
        } else {
            for (int i = ship.getPosition().getY(); i < ship.getPosition().getY() + ship.getSize(); i++) {
                board[i][ship.getPosition().getX()] = OPEN_SQUARE;
            }
        }
        System.out.println("ui.main.Tests.model.Ship removed successfully");
    }

    //EFFECTS: returns true if position is on board, false otherwise
    public boolean isPositionOnBoard(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board.length;
    }
}
