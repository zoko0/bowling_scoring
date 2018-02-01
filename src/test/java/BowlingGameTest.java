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
    public void rollReturnsException(){
        game.roll(11);
    }

    @Test
    public void bowlingGameHasZeroScoreInitially(){
        game.roll(0);
        assertEquals(0, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterOneRoll(){
        game.roll(5);
        assertEquals(5, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterTwoRolls(){
        rollQueue = new int[]{5, 4};
        rollMany(rollQueue);
        assertEquals(9, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterFiveRolls(){
        rollQueue = new int[]{5, 4, 4, 4, 4};
        rollMany(rollQueue);
        assertEquals(21, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterSpare(){
        rollQueue = new int[]{5, 5, 4, 4, 4};
        rollMany(rollQueue);
        assertEquals(26, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterStrike(){
        rollQueue = new int[]{10, 1, 1};
        rollMany(rollQueue);
        assertEquals(14, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterThrewStrikesInTheRow(){
        rollQueue = new int[]{10, 10, 10, 1, 1};
        rollMany(rollQueue);
        assertEquals(32 + 20 + 11 + 2, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterSpareInLastRound(){
        rollQueue = new int[]{1,2,3,4,1,2,3,4,9,0,1,2,3,4,1,2,3,4,9,1,1};
        int expected = 0;
        for (int aRollQueue : rollQueue){
            expected += aRollQueue;
        }
        expected += 1;

        rollMany(rollQueue);
        assertEquals(expected, game.calculateScore());

    }

    @Test
    public void bowlingGameScoreAfterStrikeInLastRound(){
        rollQueue = new int[]{1,2,3,4,1,2,3,4,9,0,1,2,3,4,1,2,3,4,10,1};
        int expected = 0;
        for (int aRollQueue : rollQueue){
            expected += aRollQueue;
        }
        expected += 1;

        rollMany(rollQueue);
        assertEquals(expected, game.calculateScore());
    }

    @Test
    public void bowlingGamePerfectScore(){
        rollQueue = new int[]{10,10,10,10,10,10,10,10,10,10,10};

        rollMany(rollQueue);
        assertEquals(300, game.calculateScore());
    }

    private void rollMany(int[] rollQueue){
        for (int knockedPins : rollQueue) {
            game.roll(knockedPins);
        }
    }


}