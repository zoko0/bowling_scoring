import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingGameTest {

    @Test
    public void bowlingGameHasZeroScoreInitially() {
        BowlingGame game = new BowlingGame();
        assertEquals(0, game.calculateScore());
    }
}