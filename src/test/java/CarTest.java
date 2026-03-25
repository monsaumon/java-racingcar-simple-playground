import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {
    @DisplayName("move의 입력이 4 이상이면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,false", "1,false", "2,false", "3,false",
            "4,true", "5,true", "6,true", "7,true", "8,true", "9,true"})
    public void testMove(int n, boolean expected) {
        // given
        Car car = new Car("asdf");

        // when
        final boolean actual = car.move(n);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("getName 함수는 name을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "qwe", "yeonu"})
    public void testGetName(String expected) {
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
    public void testInvalidName(String name) {
        // when & then
        assertThatThrownBy(() -> new Car(name)).isInstanceOf(IllegalArgumentException.class);
    }
}
