import java.util.ArrayList;
import java.util.List;

public record Car(String name) {
    private String checkName(final String name) throws IllegalArgumentException {
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

    public boolean move(final int n) {
        return n >= 4;
    }

    public int[] move(final int[] numbers) {
        final List<Integer> movedDistance = new ArrayList<>();
        for (int number : numbers) {
            movedDistance.add(Boolean.compare(number >= 4, false));
        }
        return movedDistance.stream().mapToInt(i -> i).toArray();
    }
}
