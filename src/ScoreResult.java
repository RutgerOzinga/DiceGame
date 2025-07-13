public class ScoreResult {
    private final ScoreCategory category;
    private final int dieFace;
    private final int dieCount;
    private final int score;

    public ScoreResult(ScoreCategory category, int dieFace, int dieCount, int score) {
        this.category = category;
        this.dieFace = dieFace;
        this.dieCount = dieCount;
        this.score = score;

    }

    public ScoreCategory getCategory() {
        return category;
    }

    public int getDieFace() {
        return dieFace;
    }

    public int getDieCount() {
        return dieCount;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return category + " (face=" + dieFace + ", count=" + dieCount + ") = " + score;
    }
}
