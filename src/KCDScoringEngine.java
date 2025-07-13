import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KCDScoringEngine implements ScoringEngine {
    private final Map<ScoreCategory, ScoringRule> rules = Map.of(
            ScoreCategory.ONES, new ScoringRule(ScoreCategory.ONES, 1, 2, this::scoreOnes),
            ScoreCategory.FIVES, new ScoringRule(ScoreCategory.FIVES, 1, 2, this::scoreFives),
            ScoreCategory.N_OF_A_KIND, new ScoringRule(ScoreCategory.N_OF_A_KIND, 3, 0, this::scoreNOfAKind),
            ScoreCategory.PARTIAL_STRAIGHT, new ScoringRule(ScoreCategory.PARTIAL_STRAIGHT, 5, 5, this::scorePartialStraight),
            ScoreCategory.FULL_STRAIGHT, new ScoringRule(ScoreCategory.FULL_STRAIGHT, 6, 6, this::scoreFullStraight)
    );

    @Override
    public int calculate(ScoreCategory category, List<Integer> dice) {
        ScoringRule rule = rules.get(category);
        if (rule != null) {
            return rule.getScoringFunction().score(dice, rule);
        } else return 0;
    }

    private int scoreOnes(List<Integer> dice, ScoringRule rule) {
        return (int) dice.stream().filter(d -> d == 1).count() * 100;
    }

    private int scoreFives(List<Integer> dice, ScoringRule rule) {
        return (int) dice.stream().filter(d -> d == 5).count() * 50;
    }

    private int scoreNOfAKind(List<Integer> dice, ScoringRule rule) {
        Map<Integer, Long> counts = dice.stream()
                .collect(Collectors.groupingBy(d -> d, Collectors.counting()));
        return counts.entrySet().stream()
                .filter(e -> e.getValue() >= rule.getMinCount())
                .mapToInt(e -> {
                    int face = e.getKey();
                    int count = e.getValue().intValue();
                    if (face == 1) return 1000 * ((count + 1) - rule.getMinCount());
                    else {
                        return face * 100 * ((count + 1) - rule.getMinCount());
                    }
                }).sum();
    }

    private int scorePartialStraight(List<Integer> dice, ScoringRule rule) {
        int requiredLength = rule.getMinCount();
        int longest = StraightUtils.longestStraight(dice);
        int min = dice.stream().mapToInt(Integer::intValue).min().orElse(0);
        if (min - 1 == 0) {
            return (longest == requiredLength) ? 500 : 0; // if min - 1  = 0 it means the partial straight is 1,2,3,4,5
        } else {
            return (longest == requiredLength) ? 750 : 0;
        }

    }

    private int scoreFullStraight(List<Integer> dice, ScoringRule rule) {
        int requiredLength = rule.getMinCount();
        int longest = StraightUtils.longestStraight(dice);
        return (longest >= requiredLength) ? 1500 : 0;
    }

    public Map<ScoreCategory, ScoringRule> getRules() {
        return rules;
    }

}
