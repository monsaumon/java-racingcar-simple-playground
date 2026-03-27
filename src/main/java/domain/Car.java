package domain;

import java.util.ArrayList;
import java.util.List;

import static domain.Utils.validateName;

public record Car(String name) {
    public Car(final String name) {
        this.name = validateName(name);
    }

    public int moveIfGreaterThanFour(final int number) {
        return Boolean.compare(number >= 4, false);
    }

    public int[] generateMoveHistory(final int[] numbers) {
        final List<Integer> moveHistory = new ArrayList<>();
        for (int number : numbers) {
            moveHistory.add(moveIfGreaterThanFour(number));
        }
        return moveHistory.stream().mapToInt(i -> i).toArray();
    }
}
