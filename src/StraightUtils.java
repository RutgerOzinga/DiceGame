import java.util.List;
public class StraightUtils {
    public static int longestStraight(List<Integer> dice) {
        List<Integer> sorted = dice.stream()
                .distinct()
                .sorted()
                .toList();
        int streak = 1;

        for (int i = 1; i < sorted.size(); i++) {
            if (sorted.get(i) - sorted.get(i - 1) == 1) {
                streak++;
            } else streak = 1;
        }
        return streak;
    }
}
