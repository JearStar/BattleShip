import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.CPUEasy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CPUEasyTest {
    CPUEasy cpuEasy;
    @BeforeEach
    void setup() {
        cpuEasy = new CPUEasy(10);
    }

    @Test
    void testGenerateMoves() {
        assertEquals(cpuEasy.getPosToVisit().size(), 100);
    }

    @Test
    void testGetNextMove() {
        for (int i = 0; i < 100; i++) {
            assertFalse(cpuEasy.getPrevPositions().contains(cpuEasy.getNextMove()));
        }

    }
}
