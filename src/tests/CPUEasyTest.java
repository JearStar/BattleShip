import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.CPUEasy;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
