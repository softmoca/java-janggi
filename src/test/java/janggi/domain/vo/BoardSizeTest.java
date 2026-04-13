package janggi.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoardSizeTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "9, 8",
            "1, 2"
    })
    void 보드_범위_내부의_좌표는_포함된다(int row, int col) {
        assertThat(BoardSize.JANGGI.contains(new Position(row, col))).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 0",
            "10, 0",
            "0, -1",
            "0, 9"
    })
    void 보드_범위_밖의_좌표는_포함되지_않는다(int row, int col) {
        assertThat(BoardSize.JANGGI.contains(new Position(row, col))).isFalse();
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 0",
            "10, 0",
            "0, -1",
            "0, 9"
    })
    void 범위_밖_좌표_검증시_예외가_발생한다(int row, int col) {
        assertThatThrownBy(() -> BoardSize.JANGGI.validateContains(new Position(row, col)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위 밖");
    }

    @Test
    void 보드_크기가_0_이하면_예외가_발생한다() {
        assertThatThrownBy(() -> new BoardSize(0, 9))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new BoardSize(10, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
