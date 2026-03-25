import java.util.Random;

public class RandomGenerator {
    private final Random random;

    public RandomGenerator() {
        this.random = new Random();
    }

    private int[] generateRandomArray(final int length) {
        final int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(10);
        }
        return array;
    }

    public int[][] generateRandom2DArray(final int firstLength, final int secondLength) {
        final int[][] array = new int[firstLength][secondLength];
        for (int i = 0; i < firstLength; i++) {
            array[i] = generateRandomArray(secondLength);
        }
        return array;
    }
}
