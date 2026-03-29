package domain;

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
    @DisplayName("이동 횟수와 2차원 숫자 배열을 받아 이동 내역을 반환한다.")
    @ParameterizedTest
    @MethodSource
    public void testGetMoveHistoryFromEachCar(final String[] names, final int moveCount, final int[][] numbers,
                                              final int[][] expected) {
        // given
        final Race race = new Race(names);

        // when
        final int[][] actual = race.getMoveHistoryFromEachCar(moveCount, numbers);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testGetMoveHistoryFromEachCar() {
        return Stream.of(
                Arguments.arguments(new String[]{"aa", "bb", "cc"}, 4,
                        new int[][]{{4, 4, 4, 4}, {3, 3, 3, 3}, {3, 3, 4, 4}},
                        new int[][]{{1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 1, 1}}),
                Arguments.arguments(new String[]{"aa", "bb", "cc"}, 2, new int[][]{{5, 4}, {8, 9}, {2, 6}},
                        new int[][]{{1, 1}, {1, 1}, {0, 1}}),
                Arguments.arguments(new String[]{"aa", "bb"}, 3, new int[][]{{3, 3, 3}, {6, 2, 6}},
                        new int[][]{{0, 0, 0}, {1, 0, 1}})
        );
    }

    @DisplayName("이동 내역을 받으면 우승자를 반환한다.")
    @ParameterizedTest
    @MethodSource
    public void testGetWinners(final int[][] moveHistory, final List<String> expected) {
        // given
        final Race race = new Race("asdf", "sdfg", "dfgh");

        // when
        final List<String> actual = race.getWinners(moveHistory);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testGetWinners() {
        return Stream.of(
                Arguments.arguments(new int[][]{{1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 1, 1}}, List.of("asdf")),
                Arguments.arguments(new int[][]{{1, 1}, {1, 1}, {0, 1}}, List.of("asdf", "sdfg")),
                Arguments.arguments(new int[][]{{0, 0, 0}, {1, 0, 1}, {1, 0, 0}}, List.of("sdfg"))

        );
    }

    @DisplayName("getCarNames는 Car들의 이름을 반환한다.")
    @ParameterizedTest
    @MethodSource
    public void testGetCarNames(final String[] expected) {
        // given
        final Race race = new Race(expected);

        // when
        final String[] actual = race.getCarNames();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testGetCarNames() {
        return Stream.of(
                Arguments.arguments((Object) new String[]{"aaaa", "bbb", "cc"}),
                Arguments.arguments((Object) new String[]{"asdf", "qwefe", "fds", "as"})
        );
    }
}
