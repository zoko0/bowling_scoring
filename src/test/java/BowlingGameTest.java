import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class BowlingGameTest {

    BowlingGame game;

    @Before
    public void setUp(){
        this.game = new BowlingGame();
    }

    @Test
    public void bowlingGameHasZeroScoreInitially() {
        assertEquals(0, game.calculateScore());
    }

}