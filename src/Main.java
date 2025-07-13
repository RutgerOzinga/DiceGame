
public class Main {
    public static void main(String[] args) {
        int playerStartDiceNum = 6;

        DiceSet dice = new DiceSet(6, 6);
        dice.rollAll();
        System.out.println(dice.getValues());
        KCDScoringEngine scoreEngine = new KCDScoringEngine();
        for (ScoreCategory category : scoreEngine.getRules().keySet()) {
            int score = scoreEngine.calculate(category, dice.getValues());
            if (score > 0) {
                System.out.println(category + " gives a score of " + score);
            }
        }

    }

}