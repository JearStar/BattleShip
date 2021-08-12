import model.Board;
import model.Position;
import model.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTest {

    Ship testShip1;
    Ship testShip2;

    @BeforeEach
    void setup() {
        testShip1 = new Ship(0, 0, Board.RIGHT_ORIENTATION, 4);
        testShip2 = new Ship(1, 1, Board.DOWN_ORIENTATION, 5);
    }

    @Test
    void testConstructorAndFields() {
        assertEquals(testShip1.getPosition(), new Position(0, 0));
        assertTrue(testShip1.getOrientation());
        assertEquals(4, testShip1.getSize());
        assertFalse(testShip1.isSunken());
        assertTrue(testShip1.getHits().isEmpty());
    }

    @Test
    void testSetShipCells() {
        List<Position> shipCells = new ArrayList<>();
        shipCells.add(new Position(0, 0));
        shipCells.add(new Position(1, 0));
        shipCells.add(new Position(2, 0));
        shipCells.add(new Position(3, 0));
        assertEquals(shipCells, testShip1.getShipCells());
    }

    @Test
    void testHitShip() {
        testShip1.hitShip(new Position(0, 0));
        testShip1.hitShip(new Position(1, 1));
        assertEquals(testShip1.getHits().size(), 1);
        assertEquals(testShip1.getHits().get(0), new Position(0, 0));

        testShip1.hitShip(new Position(1, 0));
        testShip1.hitShip(new Position(2, 0));
        testShip1.hitShip(new Position(3, 0));

        assertEquals(testShip1.getHits().size(), 4);
        assertTrue(testShip1.isSunken());
    }

    @Test
    void testSetOrientation() {
        testShip1.setOrientation(Board.RIGHT_ORIENTATION);
        assertEquals(testShip1.getOrientation(), Board.RIGHT_ORIENTATION);
        testShip1.setOrientation(Board.DOWN_ORIENTATION);
        assertEquals(testShip1.getOrientation(), Board.DOWN_ORIENTATION);
    }

    @Test
    void testToggleOrientation() {
        assertEquals(testShip1.getOrientation(), Board.RIGHT_ORIENTATION);
        testShip1.switchOrientation();
        assertEquals(testShip1.getOrientation(), Board.DOWN_ORIENTATION);
        testShip1.switchOrientation();
        assertEquals(testShip1.getOrientation(), Board.RIGHT_ORIENTATION);
    }

    @Test
    void testSetPosition() {
        Position testPos1 = new Position(0, 0);
        Position testPos2 = new Position(5, 5);
        assertEquals(testShip1.getPosition(), testPos1);
        testShip1.setPosition(testPos2);
        assertEquals(testShip1.getPosition(), testPos2);
    }
}
