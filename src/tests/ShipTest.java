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
}
