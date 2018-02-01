import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class BowlingGameTest {

    private BowlingGame game;
    private int[] rollQueue;

    @Before
    public void setUp(){
        this.game = new BowlingGame();
    }

    @Test(expected = Exception.class)
    public void rollReturnsExceptionIfMoreThenTen(){
        game.roll(11);
    }

    @Test(expected = Exception.class)
    public void rollReturnsExceptionIfLessThenTen(){
        game.roll(-1);
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
        game.roll(5);
        game.roll(4);

        assertEquals(9, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterSpare(){
        rollMany(2, 5);
        rollMany(3, 4);
        assertEquals(26, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterStrike(){
        game.roll(10);
        rollMany(2, 1);
        assertEquals(14, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterThreeStrikesInTheRow(){
        rollMany(3, 10);
        rollMany(2, 1);

        assertEquals(32 + 20 + 11 + 2, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterSpareInLastRound(){
        rollMany(18, 1);
        game.roll(9);
        game.roll(1);
        game.roll(1);


        assertEquals(18+9+1+1, game.calculateScore());
    }

    @Test
    public void bowlingGameScoreAfterStrikeInLastRound(){
        rollMany(18, 1);
        game.roll(10);
        game.roll(1);

        assertEquals(18+ 10 + 1, game.calculateScore());
    }

    @Test
    public void bowlingGamePerfectScore(){
        rollMany(12, 10);
        assertEquals(300, game.calculateScore());
    }

    @Test(expected = Exception.class)
    public void bowlingGameOutOfBoundError(){
        rollMany(13, 10);
    }

    private void rollMany(int howManyRolls,int pinsInEachThrow){
        for (int i=0; i < howManyRolls; i++) {
            game.roll(pinsInEachThrow);
        }
    }


}