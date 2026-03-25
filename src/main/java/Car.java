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

    public int move(final int[] numbers) {
        int movedDistance = 0;
        for (int number : numbers) {
            movedDistance += Boolean.compare(number >= 4, false);
        }
        return movedDistance;
    }
}
