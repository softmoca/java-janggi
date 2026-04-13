package janggi.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionTest {

    @Test
    void 좌표_값이_같으면_동등한_객체로_판단한다() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(1, 2);

        assertThat(position1).isEqualTo(position2);
    }

    @Test
    void 좌표_값이_다르면_다른_객체로_판단한다() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(2, 1);

        assertThat(position1).isNotEqualTo(position2);
    }

    @ParameterizedTest
    @CsvSource({
            "-5, -10",
            "100, 200",
            "0, 0"
    })
    void 좌표는_범위_검증_없이_어떤_값이든_표현할_수_있다(int row, int col) {
        Position position = new Position(row, col);

        assertThat(position.getRow()).isEqualTo(row);
        assertThat(position.getCol()).isEqualTo(col);
    }
}
