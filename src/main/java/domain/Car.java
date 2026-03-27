package domain;

import java.util.ArrayList;
import java.util.List;

public record Car(String name) {
    private String checkName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름이 비어있을 수 없습니다.");
        }
        if (name.length() > 5) {
            throw new IllegalArgumentException("이름이 5자를 넘을 수 없습니다.");
        }
        return name;
    }

    public Car(final String name) {
        this.name = checkName(name);
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
