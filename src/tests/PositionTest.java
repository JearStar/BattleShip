import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    Position testPosition;

    @BeforeEach
    void setup() {
        testPosition = new Position(0, 0);
    }

    @Test
    void testSettersAndGetters() {
        assertEquals(testPosition.getX(), 0);
        assertEquals(testPosition.getY(), 0);
        testPosition.setX(1);
        testPosition.setY(2);
        assertEquals(testPosition.getX(), 1);
        assertEquals(testPosition.getY(), 2);
    }

    @Test
    void testTwoPositionsAreTheSame() {
        Position testPosition2 = new Position(0, 0);
        assertEquals(testPosition, testPosition2);
        assertEquals(testPosition.hashCode(), testPosition2.hashCode());
    }
}
