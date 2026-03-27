package domain;

import java.util.Arrays;
import java.util.List;

public class Utils {
    public static <T> boolean hasRedundancy(final T[] array) {
        return Arrays.stream(array).distinct().count() != array.length;
    }

    public static <T> void conditionalAdd(final List<T> list, final boolean condition, final T value) {
        if (condition) {
            list.add(value);
        }
    }

    public static void checkSizeOfArray(final int[] array, final int length) {
        if (array.length != length) {
            throw new WrongArrayLengthException();
        }
    }

    public static void checkSizeOf2DArray(final int[][] array, final int firstLength, final int secondLength) {
        if (array.length != firstLength) {
            throw new WrongArrayLengthException();
        }
        for (int[] subArray : array) {
            checkSizeOfArray(subArray, secondLength);
        }
    }
}
