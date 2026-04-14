package janggi.domain.palace;

import static org.assertj.core.api.Assertions.assertThat;

import janggi.domain.vo.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChoPalaceTest {

    private final Palace palace = new ChoPalace();

    @ParameterizedTest
    @CsvSource({
            "7, 3", "7, 4", "7, 5",
            "8, 3", "8, 4", "8, 5",
            "9, 3", "9, 4", "9, 5"
    })
    void 초_궁성_내부_좌표를_판별한다(int row, int col) {
        assertThat(palace.contains(new Position(row, col))).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "0, 3", "1, 4", "2, 5", "6, 4"
    })
    void 초_궁성_외부_좌표를_판별한다(int row, int col) {
        assertThat(palace.contains(new Position(row, col))).isFalse();
    }

    @Test
    void 초_궁성_중앙에서_꼭짓점으로_대각선_이동_가능() {
        assertThat(palace.canMoveDiagonally(
                new Position(8, 4), new Position(7, 3))).isTrue();
    }

    @Test
    void 초_궁성_2칸_대각선의_중간은_궁성_중앙() {
        Position midpoint = palace.diagonalMidpoint(
                new Position(7, 3), new Position(9, 5));
        assertThat(midpoint).isEqualTo(new Position(8, 4));
    }
}
