public record Car(String name) {
    private String checkName(String name) throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름이 비어있을 수 없습니다.");
        }
        if (name.length() > 5) {
            throw new IllegalArgumentException("이름이 5자를 넘을 수 없습니다.");
        }
        return name;
    }

    public Car(String name) {
        this.name = checkName(name);
    }

    public boolean move(int n) {
        return n >= 4;
    }
}
