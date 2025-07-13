import java.util.Random;

public class Die {
    private int sides;
    private static Random RANDOM = new Random();
    private int value;
    private boolean held = false;

    public Die(int sides) {
        if (sides < 2) {
            throw new IllegalArgumentException("Die must have at least 2 sides");
        }
        this.sides = sides;
    }

    public void roll() {
        if (!held) value = RANDOM.nextInt(sides) + 1;
    }

    public void hold() {
        held = true;
    }

    public void release() {
        held = false;
    }

    public int getSides() {
        return sides;
    }

    public boolean isHeld() {
        return held;
    }

    public int getValue() {
        return value;
    }
}
