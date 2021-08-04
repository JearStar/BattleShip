import model.Board;
import model.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Objects.deepEquals;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    Board testBoard;

    @BeforeEach
    void setup() {
        testBoard = new Board(10);
    }

    @Test
    void testGetBoardAndAsString() {
        String[][] board = {
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
        };

        assertTrue(deepEquals(board, testBoard.getBoard()));
        assertEquals("#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n", testBoard.boardToString());
    }

    @Test
    void testAddShipHorizontalSuccessful() {
        Ship ship = new Ship(0, 0, Board.RIGHT_ORIENTATION, 4);
        assertTrue(testBoard.addShip(ship));
        assertEquals("4  4  4  4  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n", testBoard.boardToString());
    }

    @Test
    void testAddShipVerticalSuccessful() {
        Ship ship = new Ship(0, 0, Board.DOWN_ORIENTATION, 4);
        assertTrue(testBoard.addShip(ship));
        assertEquals("4  #  #  #  #  #  #  #  #  #  \n" +
                "4  #  #  #  #  #  #  #  #  #  \n" +
                "4  #  #  #  #  #  #  #  #  #  \n" +
                "4  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n", testBoard.boardToString());
    }

    @Test
    void testAddShipFailOutOfBounds() {
        Ship ship1 = new Ship(-1, 0, Board.RIGHT_ORIENTATION, 4);
        Ship ship2 = new Ship(0, -1, Board.RIGHT_ORIENTATION, 4);
        Ship ship3 = new Ship(testBoard.getBoard().length, 0, Board.RIGHT_ORIENTATION, 4);
        Ship ship4 = new Ship(0, testBoard.getBoard().length, Board.RIGHT_ORIENTATION, 4);
        Ship ship5 = new Ship(testBoard.getBoard().length, testBoard.getBoard().length, Board.RIGHT_ORIENTATION, 4);
        assertFalse(testBoard.addShip(ship1));
        assertFalse(testBoard.addShip(ship2));
        assertFalse(testBoard.addShip(ship3));
        assertFalse(testBoard.addShip(ship4));
        assertFalse(testBoard.addShip(ship5));
        assertEquals("#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n", testBoard.boardToString());
    }

    @Test
    void testAddShipFailSizeGoesOutOfBounds() {
        Ship ship1 = new Ship(testBoard.getBoard().length - 1, 0, Board.RIGHT_ORIENTATION, 1);
        assertTrue(testBoard.addShip(ship1));
        Ship ship2 = new Ship(testBoard.getBoard().length - 1, 1, Board.RIGHT_ORIENTATION, 2);
        assertFalse(testBoard.addShip(ship2));
        assertEquals("#  #  #  #  #  #  #  #  #  1  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n", testBoard.boardToString());
        Ship ship3 = new Ship(0, testBoard.getBoard().length - 1, Board.DOWN_ORIENTATION, 1);
        assertTrue(testBoard.addShip(ship3));
        Ship ship4 = new Ship(1, testBoard.getBoard().length - 1, Board.DOWN_ORIENTATION, 2);
        assertFalse(testBoard.addShip(ship4));
        assertEquals("#  #  #  #  #  #  #  #  #  1  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "1  #  #  #  #  #  #  #  #  #  \n", testBoard.boardToString());
    }

    @Test
    void testAddShipFailShipInTheWay() {
        Ship ship1 = new Ship(5, 5, Board.RIGHT_ORIENTATION, 4);
        //Add same ship twice
        assertTrue(testBoard.addShip(ship1));
        assertFalse(testBoard.addShip(ship1));
        Ship ship2 = new Ship(2, 5, Board.RIGHT_ORIENTATION, 4);
        assertFalse(testBoard.addShip(ship2));
        Ship ship3 = new Ship(5, 2, Board.DOWN_ORIENTATION, 4);
        assertFalse(testBoard.addShip(ship3));
        Ship ship4 = new Ship(4, 5, Board.DOWN_ORIENTATION, 4);
        assertTrue(testBoard.addShip(ship4));
        Ship ship5 = new Ship(4, 2, Board.DOWN_ORIENTATION, 4);
        assertFalse(testBoard.addShip(ship5));
        Ship ship6 = new Ship(4, 1, Board.DOWN_ORIENTATION, 4);
        assertFalse(testBoard.addShip(ship6));
        Ship ship7 = new Ship(0, 5, Board.RIGHT_ORIENTATION, 4);
        assertTrue(testBoard.addShip(ship7));
        assertTrue(testBoard.getShipsOnBoard().contains(ship1));
        assertFalse(testBoard.getShipsOnBoard().contains(ship2));
        assertFalse(testBoard.getShipsOnBoard().contains(ship3));
        assertTrue(testBoard.getShipsOnBoard().contains(ship4));
        assertFalse(testBoard.getShipsOnBoard().contains(ship5));
        assertFalse(testBoard.getShipsOnBoard().contains(ship6));
        assertTrue(testBoard.getShipsOnBoard().contains(ship7));
        assertEquals("#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "4  4  4  4  4  4  4  4  4  #  \n" +
                "#  #  #  #  4  #  #  #  #  #  \n" +
                "#  #  #  #  4  #  #  #  #  #  \n" +
                "#  #  #  #  4  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n", testBoard.boardToString());
    }

    @Test
    void testRemoveShipNotThere() {
        Ship ship = new Ship(0, 0, Board.RIGHT_ORIENTATION, 4);
        assertFalse(testBoard.removeShip(ship));
    }

    @Test
    void testRemoveShipSuccessful() {
        Ship shipX = new Ship(0, 0, Board.RIGHT_ORIENTATION, 4);
        Ship shipY = new Ship(0, 1, Board.DOWN_ORIENTATION, 4);
        assertTrue(testBoard.addShip(shipX));
        assertTrue(testBoard.addShip(shipY));
        assertTrue(testBoard.getShipsOnBoard().contains(shipX));
        assertTrue(testBoard.getShipsOnBoard().contains(shipY));
        assertTrue(testBoard.removeShip(shipX));
        assertTrue(testBoard.getShipsOnBoard().contains(shipY));
        assertTrue(testBoard.removeShip(shipY));
        assertTrue(testBoard.getShipsOnBoard().isEmpty());

        assertEquals("#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n" +
                "#  #  #  #  #  #  #  #  #  #  \n", testBoard.boardToString());
    }

}
