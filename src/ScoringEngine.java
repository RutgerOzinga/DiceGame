import java.util.List;

public interface ScoringEngine {
    int calculate(ScoreCategory category, List<Integer> dice);
}
@FunctionalInterface
interface ScoringFunction {
    int score(List<Integer> dice, ScoringRule rule);
}