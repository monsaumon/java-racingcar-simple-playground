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
    @CsvSource(value = {"0,0", "1,0", "2,0", "3,0",
            "4,1", "5,1", "6,1", "7,1", "8,1", "9,1"})
    public void testMoveIfGreaterThanFour(final int n, final int expected) {
        // given
        Car car = new Car("asdf");

        // when
        final int actual = car.moveIfGreaterThanFour(n);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("move의 입력이 int 배열이라면 각 원소마다 4 이상이면 움직인 후 이동 여부 배열을 반환한다.")
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
