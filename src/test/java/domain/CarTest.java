package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class CarTest {
    @DisplayName("입력이 4 이상이면 1, 아니면 0을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,0", "3,0", "4,1", "9,1"})
    public void testMoveIfGreaterThan(final int n, final int expected) {
        // given
        Car car = new Car("asdf");

        // when
        final int actual = car.moveIfGreaterThan(n);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("입력 배열의 각 원소마다 4 이상이면 움직이고, 이동 내역을 반환한다.")
    @ParameterizedTest
    @MethodSource
    public void testGenerateMoveHistory(final int[] numbers, final int[] expected) {
        // given
        Car car = new Car("asdf");

        // when
        final int[] actual = car.generateMoveHistory(numbers);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testGenerateMoveHistory() {
        return Stream.of(
                Arguments.arguments(new int[]{0, 3, 4, 9}, new int[]{0, 0, 1, 1}),
                Arguments.arguments(new int[]{4, 4, 4, 4}, new int[]{1, 1, 1, 1}),
                Arguments.arguments(new int[]{3, 3, 3, 3}, new int[]{0, 0, 0, 0})
        );
    }
}
