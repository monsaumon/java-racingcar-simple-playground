package domain;

import java.util.ArrayList;
import java.util.List;

import static domain.Utils.validateName;

public record Car(String name) {
    private static final int MOVING_THRESHOLD = 3;
    private static final int NUMBER_LOWER_BOUND = 0;
    private static final int NUMBER_UPPER_BOUND = 10;

    public Car(final String name) {
        this.name = validateName(name);
    }

    private void checkNumberBound(final int number) {
        if (number < NUMBER_LOWER_BOUND || number >= NUMBER_UPPER_BOUND) {
            throw new IllegalArgumentException("입력값이 범위를 벗어났습니다.");
        }
    }

    public int moveIfGreaterThan(final int number) {
        checkNumberBound(number);
        return Boolean.compare(number > MOVING_THRESHOLD, false);
    }

    public int[] generateMoveHistory(final int[] numbers) {
        final List<Integer> moveHistory = new ArrayList<>();
        for (int number : numbers) {
            moveHistory.add(moveIfGreaterThan(number));
        }
        return moveHistory.stream().mapToInt(i -> i).toArray();
    }
}
