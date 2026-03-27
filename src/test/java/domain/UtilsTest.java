package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static domain.Utils.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class UtilsTest {
    @DisplayName("이름이 비어있지 않고 5자 이내면 그대로 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "abc12", "ab 12"})
    public void testValidateName_ValidName(final String expected) {
        // when & then
        assertAll(
                () -> assertThatCode(() -> validateName(expected)).doesNotThrowAnyException(),
                () -> assertThat(validateName(expected)).isEqualTo(expected)
        );
    }


    @DisplayName("이름이 비어있으면 IllegalArgumentException을 throw한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n"})
    public void testValidateName_EmptyName(final String name) {
        // when & then
        assertThatThrownBy(() -> validateName(name)).isInstanceOf(IllegalArgumentException.class).hasMessage("이름이 비어있을 수 없습니다.");
    }

    @DisplayName("이름이 5자를 넘으면 IllegalArgumentException을 throw한다.")
    @ParameterizedTest
    @ValueSource(strings = {"abcdef", "ab  12", "qwerty"})
    public void testValidateName_NameLongerThan5Letters(final String name) {
        // when & then
        assertThatThrownBy(() -> validateName(name)).isInstanceOf(IllegalArgumentException.class).hasMessage("이름이 5자를 넘을 수 없습니다.");
    }

    @DisplayName("중복된 값이 없으면 아무 작업도 하지 않는다.")
    @ParameterizedTest
    @MethodSource("testCheckRedundancy_NoRedundantNameSource")
    public <T> void testCheckRedundancy_NoRedundantName(final T[] array) {
        // when & then
        assertThatCode(() -> checkRedundancy(array)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> testCheckRedundancy_NoRedundantNameSource() {
        return Stream.of(
                Arguments.arguments((Object) new String[]{"asdf", "asdff", "asdfg", "fd", ""}),
                Arguments.arguments((Object) new Integer[]{0, 123, -432, 1223}),
                Arguments.arguments((Object) new Character[]{'a', 'b', 'c', '\n', 'd'})
        );
    }

    @DisplayName("중복된 값이 존재하면 RedundantValueException을 throw한다.")
    @ParameterizedTest
    @MethodSource("testCheckRedundancy_RedundantNameSource")
    public <T> void testCheckRedundancy_RedundantName(final T[] array) {
        // when & then
        assertThatThrownBy(() -> checkRedundancy(array)).isInstanceOf(RedundantValueException.class);
    }

    private static Stream<Arguments> testCheckRedundancy_RedundantNameSource() {
        return Stream.of(
                Arguments.arguments((Object) new String[]{"asdf", "asdff", "asdf", "fd", ""}),
                Arguments.arguments((Object) new Integer[] {0, 123, -432, 123}),
                Arguments.arguments((Object) new Character[]{'a', 'b', 'c', '\n', '\n'})
        );
    }

    @DisplayName("condition이 true라면 list에 value를 추가한다.")
    @ParameterizedTest
    @MethodSource("testConditionalAdd_TrueConditionSource")
    public <T> void testConditionalAdd_TrueCondition(final List<T> actual, final T value, final List<T> expected) {
        // when
        conditionalAdd(actual, true, value);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testConditionalAdd_TrueConditionSource() {
        return Stream.of(
                Arguments.arguments(new ArrayList<>(List.of("aa", "bb", "cc")), "dd", List.of("aa", "bb", "cc", "dd")),
                Arguments.arguments(new ArrayList<>(List.of('a', 'b', 'c')), '\n', List.of('a', 'b', 'c', '\n')),
                Arguments.arguments(new ArrayList<>(List.of()), 1, List.of(1))
        );
    }

    @DisplayName("condition이 false라면 list에 value를 추가하지 않는다.")
    @ParameterizedTest
    @MethodSource("testConditionalAdd_FalseConditionSource")
    public <T> void testConditionalAdd_FalseCondition(final List<T> actual, final T value, final List<T> expected) {
        // when
        conditionalAdd(actual, false, value);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testConditionalAdd_FalseConditionSource() {
        return Stream.of(
                Arguments.arguments(new ArrayList<>(List.of("aa", "bb", "cc")), "dd", List.of("aa", "bb", "cc")),
                Arguments.arguments(new ArrayList<>(List.of('a', 'b', 'c')), '\n', List.of('a', 'b', 'c')),
                Arguments.arguments(new ArrayList<>(List.of()), 1, List.of())
        );
    }

    @DisplayName("size가 일치하면 아무 작업도 하지 않는다.")
    @ParameterizedTest
    @MethodSource("testCheckSizeOfArray_CorrectSizeSource")
    public void testCheckSizeOfArray_CorrectSize(final int[] array, final int length) {
        // when & then
        assertThatCode(() -> checkSizeOfArray(array, length)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> testCheckSizeOfArray_CorrectSizeSource() {
        return Stream.of(
                Arguments.arguments(new int[]{}, 0),
                Arguments.arguments(new int[]{1,2,3}, 3),
                Arguments.arguments(new int[]{1,2,3,4,5}, 5)
        );
    }

    @DisplayName("size가 일치하지 않으면 WrongArrayLengthException을 throw한다.")
    @ParameterizedTest
    @MethodSource("testCheckSizeOfArray_WrongSizeSource")
    public void testCheckSizeOfArray_WrongSize(final int[] array, final int length) {
        // when & then
        assertThatThrownBy(() -> checkSizeOfArray(array, length)).isInstanceOf(WrongArrayLengthException.class);
    }

    private static Stream<Arguments> testCheckSizeOfArray_WrongSizeSource() {
        return Stream.of(
                Arguments.arguments(new int[]{}, 1),
                Arguments.arguments(new int[]{1,2,3}, 0),
                Arguments.arguments(new int[]{1,2,3,4,5}, 4)
        );
    }

    @DisplayName("size가 일치하면 아무 작업도 하지 않는다.")
    @ParameterizedTest
    @MethodSource("testCheckSizeOf2DArray_CorrectSizeSource")
    public void testCheckSizeOf2DArray_CorrectSize(final int[][] array, final int length1, final int length2) {
        // when & then
        assertThatCode(() -> checkSizeOf2DArray(array, length1, length2)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> testCheckSizeOf2DArray_CorrectSizeSource() {
        return Stream.of(
                Arguments.arguments(new int[][]{}, 0, 0),
                Arguments.arguments(new int[][]{{},{},{}}, 3, 0),
                Arguments.arguments(new int[][]{{1,2},{2,3},{3,4}}, 3, 2)
        );
    }

    @DisplayName("size가 일치하지 않으면 WrongArrayLengthException을 throw한다.")
    @ParameterizedTest
    @MethodSource("testCheckSizeOf2DArray_WrongSizeSource")
    public void testCheckSizeOf2DArray_WrongSize(final int[][] array, final int length1, final int length2) {
        // when & then
        assertThatThrownBy(() -> checkSizeOf2DArray(array, length1, length2)).isInstanceOf(WrongArrayLengthException.class);
    }

    private static Stream<Arguments> testCheckSizeOf2DArray_WrongSizeSource() {
        return Stream.of(
                Arguments.arguments(new int[][]{}, 1, 1),
                Arguments.arguments(new int[][]{}, 0, 1),
                Arguments.arguments(new int[][]{{},{},{}}, 3, 1),
                Arguments.arguments(new int[][]{{},{},{}}, 0, 0),
                Arguments.arguments(new int[][]{{1,2},{2,3},{3,4}}, 3, 1),
                Arguments.arguments(new int[][]{{1,2},{2,3},{3,4}}, 2, 2)
        );
    }
}
