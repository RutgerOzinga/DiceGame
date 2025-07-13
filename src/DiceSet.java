import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiceSet {
    private final List<Die> dice;

    public DiceSet(int count, int sides) {
        this.dice = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            dice.add(new Die(sides));
        }

    }

    public void rollAll() {
        for (Die die : dice) {
            die.roll();
        }
    }

    public void holdDie(int diceIndex) {
        if (diceIndex >= 0 && diceIndex < dice.size()) {
            dice.get(diceIndex).hold();
        }
    }

    public void holdDie(List<Integer> indexes) {
        for (int index : indexes) {
            holdDie(index);  // reuse the single-die hold logic
        }
    }

    public void releaseDie(int diceIndex) {
        if (diceIndex >= 0 && diceIndex < dice.size()) {
            dice.get(diceIndex).release();
        }
    }

    public void releaseAll() {
        for (Die die : dice) {
            die.release();
        }
    }

    public List<Integer> getValues() {
        return dice.stream()
                .map(Die::getValue)
                .collect(Collectors.toList());
    }

    public void printDice() {
        for (int i = 0; i < dice.size(); i++) {
            System.out.println("Die " + (i + 1) + ": " + dice.get(i).getValue());
        }
    }

    public int countHeld() {
        int heldDie = 0;
        for (Die die : this.dice) {
            if (die.isHeld()) {
                heldDie++;
            }
        }
        return heldDie;
    }


}
