package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static domain.Utils.*;

public class Race {
    private final List<Car> cars;

    public Race(final String... names) {
        if (hasRedundancy(names)) {
            throw new NameRedundancyException();
        }
        cars = new ArrayList<>();
        for (final String name : names) {
            cars.add(new Car(name));
        }
    }

    public int[][] getMoveHistory(final int moveCount, final int[][] numbers) {
        checkSizeOf2DArray(numbers, cars.size(), moveCount);
        final int[][] moveHistory = new int[cars.size()][moveCount];
        for (int i = 0; i < cars.size(); i++) {
            moveHistory[i] = cars.get(i).move(numbers[i]);
        }
        return moveHistory;
    }

    public List<String> getWinners(final int[][] moveHistory) {
        final List<String> winners = new ArrayList<>();
        final int[] movedDistance = new int[moveHistory.length];
        for (int i = 0; i < moveHistory.length; i++) {
            movedDistance[i] = Arrays.stream(moveHistory[i]).sum();
        }
        final int maxDistance = Arrays.stream(movedDistance).max().orElseThrow(NoSuchElementException::new);
        for (int i = 0; i < moveHistory.length; i++) {
            conditionalAdd(winners, movedDistance[i] == maxDistance, cars.get(i).name());
        }
        return winners;
    }

    public String[] getCarNames() {
        final List<String> carNames = new ArrayList<>();
        for (Car car : cars) {
            carNames.add(car.name());
        }
        return carNames.toArray(String[]::new);
    }
}
