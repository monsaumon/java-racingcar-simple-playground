import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RaceTest {
    @DisplayName("이동 횟수와 2차원 숫자 배열을 받아 이동한 뒤 우승 자동차를 출력한다.")
    @ParameterizedTest
    @MethodSource("testRaceSource")
    public void testRace(final String[] names, final int numberOfMove, final int[][] numbers,
                         final List<String> expected) {
        // given
        Race race = new Race(names);

        // when
        List<String> actual = race.race(numberOfMove, numbers);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testRaceSource() {
        return Stream.of(
                Arguments.arguments(new String[]{"aa", "bb", "cc"}, 4,
                        new int[][]{{4, 4, 4, 4}, {3, 3, 3, 3}, {3, 3, 4, 4}}, List.of("aa")),
                Arguments.arguments(new String[]{"aa", "bb", "cc"}, 2, new int[][]{{5, 4}, {8, 9}, {2, 6}},
                        List.of("aa", "bb")),
                Arguments.arguments(new String[]{"aa", "bb"}, 3, new int[][]{{3, 3, 3}, {6, 2, 6}}, List.of("bb"))
        );
    }

    @DisplayName("중복된 이름을 입력하면 NameRedundancyException을 throw한다.")
    @Test
    public void testRaceThrowsNameRedundancyException() {
        // when & then
        assertThatThrownBy(() -> new Race(new String[]{"aa", "bb", "aa"})).isInstanceOf(NameRedundancyException.class);
    }

    @DisplayName("numbers 배열 크기가 맞지 않으면 WrongArrayLengthException을 throw한다.")
    @Test
    public void testRaceThrowsWrongArrayLengthException() {
        // given
        Race race = new Race("aa", "bb", "cc");

        // when & then
        assertAll(
                () -> assertThatThrownBy(() -> race.race(3, new int[][]{{3, 3, 3}, {4, 4, 4}})).isInstanceOf(
                        WrongArrayLengthException.class),
                () -> assertThatThrownBy(() -> race.race(3, new int[][]{{3, 3, 3}, {4, 4, 4}, {2, 2}})).isInstanceOf(
                        WrongArrayLengthException.class),
                () -> assertThatThrownBy(() -> race.race(3, new int[][]{{2, 2}, {3, 3}, {4, 4}})).isInstanceOf(
                        WrongArrayLengthException.class)
        );
    }
}
