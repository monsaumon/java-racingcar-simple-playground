import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {
    @DisplayName("move의 입력이 4 이상이면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,false", "1,false", "2,false", "3,false",
            "4,true", "5,true", "6,true", "7,true", "8,true", "9,true"})
    public void testMove(final int n, final boolean expected) {
        // given
        Car car = new Car("asdf");

        // when
        final boolean actual = car.move(n);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("move의 입력이 int 배열이라면 각 원소마다 4 이상이면 움직인 후 총 이동거리를 반환한다.")
    @ParameterizedTest
    @MethodSource("intArrayAndDistance")
    public void testMoveArray(final int[] numbers, final int expected) {
        // given
        Car car = new Car("asdf");

        // when
        final int actual = car.move(numbers);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> intArrayAndDistance() {
        return Stream.of(
                Arguments.arguments(new int[]{0, 3, 4, 9}, 2),
                Arguments.arguments(new int[]{4, 4, 4, 4}, 4),
                Arguments.arguments(new int[]{3, 3, 3, 3}, 0)
        );
    }

    @DisplayName("getName 함수는 name을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "qwe", "yeonu"})
    public void testGetName(final String expected) {
        // given
        Car car = new Car(expected);

        // when
        final String actual = car.name();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("이름이 비어있거나 5자를 넘으면 IllegalArgumentException을 throw한다.")
    @ParameterizedTest
    @CsvSource(value = {"''", "' '", "'\n'", "'asdfgh'"})
    public void testInvalidName(final String name) {
        // when & then
        assertThatThrownBy(() -> new Car(name)).isInstanceOf(IllegalArgumentException.class);
    }
}
