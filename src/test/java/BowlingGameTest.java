import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class BowlingGameTest {

    private BowlingGame game;

    @Before
    public void setUp(){
        this.game = new BowlingGame();
    }

    @Test(expected = Exception.class)
    public void rollReturnsException() throws Exception {
        game.roll(11);
    }

    @Test
    public void bowlingGameHasZeroScoreInitially() throws Exception {
        game.roll(0);
        assertEquals(0, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterOneRoll() throws Exception {
        game.roll(5);
        assertEquals(5, game.calculateScore());
    }
}