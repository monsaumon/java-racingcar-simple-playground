package domain;

import java.util.Random;

public class RandomGenerator {
    private static final int RANDOM_INT_BOUND = 10;

    private final Random random;

    public RandomGenerator() {
        this.random = new Random();
    }

    private int[] generateRandomArray(final int length) {
        final int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(RANDOM_INT_BOUND);
        }
        return array;
    }

    public int[][] generateRandom2DArray(final int length1, final int length2) {
        final int[][] array = new int[length1][length2];
        for (int i = 0; i < length1; i++) {
            array[i] = generateRandomArray(length2);
        }
        return array;
    }
}
