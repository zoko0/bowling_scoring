public class BowlingGame {
    private static final int rounds = 10;
    private static final int maxPins = 10;
    private int[][] rolledInRound;
    private int currentRound= 0;
    private boolean isFirstRollInRound = true;

    //konstruktor
    public BowlingGame(){
        this.rolledInRound = new int[rounds][2];
    }

    private int mapRollInRoundToArray( boolean whichRollInRound){
        if (whichRollInRound)
            return 0;
        else
            return 1;
    }

    public void roll(int pins) throws Exception {
        //System.out.println(pins);

        if (pins > maxPins)
            throw new Exception("Too large number of knocked pins");
        else {
            if (isFirstRollInRound){
                //checkIfStrike

                //this.rolledInRound[currentRound][1] = 0;
            } else{
                //checkIfSpare

            }
            rolledInRound[currentRound++][mapRollInRoundToArray(isFirstRollInRound)] = pins;
        }
    }

    public int calculateScore() {
        int finalScore = 0;

        for (int i = 0; i < rounds; i++){
             System.out.println("Rolled in round " + i + " in firts roll is "
                     + rolledInRound[i][0] + " and " +  rolledInRound[i][1]);

             finalScore += rolledInRound[i][0] + rolledInRound[i][1];

        }

        return finalScore;
    }

}
