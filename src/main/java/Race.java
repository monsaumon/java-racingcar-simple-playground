import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

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

    private static <T> boolean hasRedundancy(final T[] array) {
        return Arrays.stream(array).distinct().count() != array.length;
    }

    private static <T> void conditionalAdd(final List<T> list, final boolean condition, final T value) {
        if (condition) {
            list.add(value);
        }
    }

    private static void checkSizeOfArray(final int[] array, final int length) {
        if (array.length != length) {
            throw new WrongArrayLengthException();
        }
    }

    private static void checkSizeOf2DArray(final int[][] array, final int firstLength, final int secondLength) {
        if (array.length != firstLength) {
            throw new WrongArrayLengthException();
        }
        for (int[] subArray : array) {
            checkSizeOfArray(subArray, secondLength);
        }
    }

    private int[] getMovedDistances(final int moveCount, final int[][] numbers) {
        checkSizeOf2DArray(numbers, cars.size(), moveCount);
        final int[] movedDistances = new int[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            movedDistances[i] = cars.get(i).move(numbers[i]);
        }
        return movedDistances;
    }

    private List<String> getWinners(final int[] movedDistances) {
        final List<String> winners = new ArrayList<>();
        final int maxDistance = Arrays.stream(movedDistances).max().orElseThrow(NoSuchElementException::new);
        for (int i = 0; i < movedDistances.length; i++) {
            conditionalAdd(winners, movedDistances[i] == maxDistance, cars.get(i).name());
        }
        return winners;
    }

    public List<String> race(final int moveCount, final int[][] numbers) {
        final int[] movedDistances = getMovedDistances(moveCount, numbers);
        return getWinners(movedDistances);
    }

    public String[] getCarNames() {
        final List<String> carNames = new ArrayList<>();
        for (Car car : cars) {
            carNames.add(car.name());
        }
        return carNames.toArray(String[]::new);
    }
}