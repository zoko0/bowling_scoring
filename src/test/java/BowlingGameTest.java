import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class BowlingGameTest {

    private BowlingGame game;
    int[] rollQueue;

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

    @Test
    public void bowlingGameScoreAfterTwoRolls() throws Exception {
        rollQueue = new int[]{5, 4};
        theySeeMeRollinTheyHatin(rollQueue);
        assertEquals(9, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterFiveRolls() throws Exception {
        rollQueue = new int[]{5, 4, 4, 4, 4};
        theySeeMeRollinTheyHatin(rollQueue);
        assertEquals(21, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterSpare() throws Exception {
        rollQueue = new int[]{5, 5, 4, 4, 4};
        theySeeMeRollinTheyHatin(rollQueue);
        assertEquals(26, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterStrike() throws Exception {
        rollQueue = new int[]{10, 1, 1};
        theySeeMeRollinTheyHatin(rollQueue);
        assertEquals(14, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterThrewStrikesInTheRow() throws Exception {
        rollQueue = new int[]{10, 10, 10, 1, 1};
        theySeeMeRollinTheyHatin(rollQueue);
        assertEquals(32 + 20 + 11 + 2, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterSpareInLastRound() throws Exception {
        rollQueue = new int[]{1,2,3,4,1,2,3,4,9,0,1,2,3,4,1,2,3,4,9,1,1};
        int expected = 0;
        for (int aRollQueue : rollQueue){
            expected += aRollQueue;
        }
        expected += 1;

        theySeeMeRollinTheyHatin(rollQueue);
        assertEquals(expected, game.calculateScore());

    }

    @Test
    public void bowlingGameScoreAfterStrikeInLastRound() throws Exception {
        rollQueue = new int[]{1,2,3,4,1,2,3,4,9,0,1,2,3,4,1,2,3,4,10,1};
        int expected = 0;
        for (int aRollQueue : rollQueue){
            expected += aRollQueue;
        }
        expected += 1;

        theySeeMeRollinTheyHatin(rollQueue);
        assertEquals(expected, game.calculateScore());
    }

    private void theySeeMeRollinTheyHatin(int[] rollQueue) throws Exception {
        for (int aRollQueue : rollQueue) {
            game.roll(aRollQueue);
        }
    }


}