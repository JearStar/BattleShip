import model.Board;
import model.Ship;
import org.junit.jupiter.api.BeforeEach;

public class ShipTest {

    Ship testShip1;
    Ship testShip2;

    @BeforeEach
    void setup() {
        testShip1 = new Ship(0, 0, Board.RIGHT_ORIENTATION, 4);
        testShip2 = new Ship(1, 1, Board.DOWN_ORIENTATION, 5);
    }
}
