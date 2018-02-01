public class BowlingGame {
    private static final int rounds = 11; // 10 + ekstra rzut
    private static final int maxPins = 10;
    private int[][] rolledInRound;
    private int currentRound= 0;
    private boolean isFirstRollInRound = true;

    //konstruktor
    public BowlingGame(){
        this.rolledInRound = new int[rounds][3];
    }

    public void roll(int pins) throws Exception {
        if (pins > maxPins)
            throw new Exception("Too large number of knocked pins");
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

        for (int i = 0; i < rounds; i++){
             System.out.println("Rolled in round " + i + " in first roll is "
                     + rolledInRound[i][0] + " and in second roll " +  rolledInRound[i][1]);

             finalScore += rolledInRound[i][0] + rolledInRound[i][1];

             if (checkIfSpare(i))
                 finalScore += rolledInRound[i+1][0];

             if (checkIfStrike(i) && checkIfTwoStrikesInRow(i))
                 finalScore += rolledInRound[i+1][0] + rolledInRound[i+2][0];

             if (checkIfStrike(i) && !checkIfTwoStrikesInRow(i))
                 finalScore += rolledInRound[i+1][0] + rolledInRound[i+1][1];
        }

        return finalScore;
    }

    private int mapRollInRoundToArray( boolean whichRollInRound){
        if (whichRollInRound)
            return 0;
        else
            return 1;
    }

    private boolean checkIfSpare(int round){
        return ((rolledInRound[round][0] + rolledInRound[round][1] == 10) && rolledInRound[round][0] != 10);
    }

    private boolean checkIfStrike(int round){
        return (rolledInRound[round][0] == 10 && rolledInRound[round][1] == 0);
    }

    private boolean checkIfTwoStrikesInRow(int round){
        return (checkIfStrike(round) && checkIfStrike(round+1));
    }

}
