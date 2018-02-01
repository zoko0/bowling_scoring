public class BowlingGame {
    private static final int ROUNDS = 13; // 10 + 2x ekstra rzut + puste miejce na policzenie dwoch strike'ow pod rzad
    private static final int MAX_PINS = 10;
    private int[][] rolledInRound;
    private int currentRound= 0;
    private boolean isFirstRollInRound = true;

    //konstruktor
    public BowlingGame(){
        this.rolledInRound = new int[ROUNDS][2];
    }

    public void roll(int pins) {
        if (pins > MAX_PINS || pins <0)
            throw new IllegalArgumentException("Illegal argument: Should be in range 0-10");
        else if (!isFirstRollInRound && (rolledInRound[currentRound][0] + pins > 10))
            throw new IllegalArgumentException("Illegal argument: Can't throw more than 10 in one round");
        else if (currentRound >= 12)
            throw new IllegalArgumentException("Illegal argument: More throws than possible");
        else {
            rolledInRound[currentRound][mapRollInRoundToArray(isFirstRollInRound)] = pins;

            //jeśli strike to zapisz w drugim rzucie kolejki -1 i ustaw "drugi rzut"  rzucony
            if (isFirstRollInRound && (pins == 10)){
                rolledInRound[currentRound][1] = 0;
                isFirstRollInRound = false;
            }

            if (!isFirstRollInRound)
                currentRound++;

            isFirstRollInRound = !(isFirstRollInRound);
        }
    }

    public int calculateScore() {
        int finalScore = 0;

        for (int i = 0; i < ROUNDS; i++){
             System.out.println("Rolled in round " + i + " in first roll is "
                     + rolledInRound[i][0] + " and in second roll " +  rolledInRound[i][1]);

             finalScore += rolledInRound[i][0] + rolledInRound[i][1];

             if (!isLastRound(i)) {
                 if (isSpare(i))
                     finalScore += rolledInRound[i + 1][0];

                 if (isStrike(i) && isTwoStrikesInTheRow(i))
                     finalScore += rolledInRound[i + 1][0] + rolledInRound[i + 2][0];

                 if (isStrike(i) && !isTwoStrikesInTheRow(i))
                     finalScore += rolledInRound[i + 1][0] + rolledInRound[i + 1][1];
             }


             System.out.println("Score in current round: " + finalScore);
        }

        return finalScore;
    }

    private int mapRollInRoundToArray( boolean whichRollInRound){
        if (whichRollInRound)
            return 0;
        else
            return 1;
    }

    private boolean isLastRound(int round){
        return (round >= 9);
    }

    private boolean isSpare(int round){
        return ((rolledInRound[round][0] + rolledInRound[round][1] == 10) && rolledInRound[round][0] != 10);
    }

    private boolean isStrike(int round){
        return (rolledInRound[round][0] == 10 && rolledInRound[round][1] == 0);
    }

    private boolean isTwoStrikesInTheRow(int round){
        return (isStrike(round) && isStrike(round+1));
    }

}
