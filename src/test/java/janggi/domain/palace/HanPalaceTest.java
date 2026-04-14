package janggi.domain.palace;

import static org.assertj.core.api.Assertions.assertThat;

import janggi.domain.vo.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HanPalaceTest {

    private final Palace palace = new HanPalace();

    @ParameterizedTest
    @CsvSource({
            "0, 3", "0, 4", "0, 5",
            "1, 3", "1, 4", "1, 5",
            "2, 3", "2, 4", "2, 5"
    })
    void 한_궁성_내부_좌표를_판별한다(int row, int col) {
        assertThat(palace.contains(new Position(row, col))).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0", "3, 4", "7, 4", "9, 5"
    })
    void 한_궁성_외부_좌표를_판별한다(int row, int col) {
        assertThat(palace.contains(new Position(row, col))).isFalse();
    }

    @Test
    void 한_궁성_중앙과_꼭짓점_사이의_대각선_이동_가능() {
        assertThat(palace.canMoveDiagonally(
                new Position(1, 4), new Position(0, 3))).isTrue();
        assertThat(palace.canMoveDiagonally(
                new Position(2, 5), new Position(1, 4))).isTrue();
    }

    @Test
    void 중앙을_경유하지_않는_대각선은_불가() {
        assertThat(palace.canMoveDiagonally(
                new Position(0, 3), new Position(1, 4))).isTrue();

        assertThat(palace.canMoveDiagonally(
                new Position(0, 4), new Position(1, 5))).isFalse();
    }

    @Test
    void _2칸_대각선은_한_칸_대각선이_아니다() {
        assertThat(palace.canMoveDiagonally(
                new Position(0, 3), new Position(2, 5))).isFalse();
    }

    @Test
    void 궁성_외부_좌표는_대각선_이동_불가() {
        assertThat(palace.canMoveDiagonally(
                new Position(4, 4), new Position(5, 5))).isFalse();
    }

    @Test
    void _2칸_대각선_경로는_중앙을_경유한다() {
        assertThat(palace.isDiagonalPath(
                new Position(0, 3), new Position(2, 5))).isTrue();

        Position midpoint = palace.diagonalMidpoint(
                new Position(0, 3), new Position(2, 5));
        assertThat(midpoint).isEqualTo(new Position(1, 4));
    }

    @Test
    void _1칸_대각선은_중간_경유지가_없다() {
        Position midpoint = palace.diagonalMidpoint(
                new Position(1, 4), new Position(0, 3));
        assertThat(midpoint).isNull();
    }
}
