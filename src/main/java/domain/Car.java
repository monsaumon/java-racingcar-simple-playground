package domain;

import java.util.ArrayList;
import java.util.List;

import static domain.Utils.validateName;

public record Car(String name) {
    private static final int MOVING_THRESHOLD = 4;

    public Car(final String name) {
        this.name = validateName(name);
    }

    public int moveIfGreaterThan(final int number) {
        return Boolean.compare(number >= MOVING_THRESHOLD, false);
    }

    public int[] generateMoveHistory(final int[] numbers) {
        final List<Integer> moveHistory = new ArrayList<>();
        for (int number : numbers) {
            moveHistory.add(moveIfGreaterThan(number));
        }
        return moveHistory.stream().mapToInt(i -> i).toArray();
    }
}
