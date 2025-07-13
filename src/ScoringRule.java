import java.util.List;
import java.util.function.BiFunction;
import java.util.function.ToIntFunction;

public class ScoringRule {
    private final ScoreCategory category;
    private final int minCount;
    private final int maxCount;
    private final ScoringFunction scoringFunction;

    public ScoringRule(ScoreCategory category, int minCount, int maxCount, ScoringFunction scoringFunction) {
        this.category = category;
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.scoringFunction = scoringFunction;
    }

    public boolean matchesCount(int actualCount) {
        return actualCount >= minCount && (maxCount == 0 || actualCount <= maxCount);
    }

    public ScoreCategory getCategory() {
        return category;
    }

    public int getMinCount() {
        return minCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public ScoringFunction getScoringFunction() {
        return scoringFunction;
    }
}