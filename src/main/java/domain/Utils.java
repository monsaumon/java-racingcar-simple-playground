package domain;

import java.util.Arrays;
import java.util.List;

public class Utils {
    public static String validateName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름이 비어있을 수 없습니다.");
        }
        if (name.length() > 5) {
            throw new IllegalArgumentException("이름이 5자를 넘을 수 없습니다.");
        }
        return name;
    }

    public static <T> void checkRedundancy(final T[] array) {
        if (Arrays.stream(array).distinct().count() != array.length) {
            throw new RedundantValueException();
        }
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

    public static void checkSizeOf2DArray(final int[][] array, final int length1, final int length2) {
        if (array.length != length1) {
            throw new WrongArrayLengthException();
        }
        if (length1 == 0 && length2 != 0) {
            throw new WrongArrayLengthException();
        }
        for (int[] subArray : array) {
            checkSizeOfArray(subArray, length2);
        }
    }
}
