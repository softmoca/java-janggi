package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    void 차의_점수는_13이다() {
        assertThat(PieceFactory.tank(Team.HAN).score()).isEqualTo(13);
    }

    @Test
    void 포의_점수는_7이다() {
        assertThat(PieceFactory.cannon(Team.HAN).score()).isEqualTo(7);
    }

    @Test
    void 마의_점수는_5이다() {
        assertThat(PieceFactory.horse(Team.HAN).score()).isEqualTo(5);
    }

    @Test
    void 상의_점수는_3이다() {
        assertThat(PieceFactory.elephant(Team.HAN).score()).isEqualTo(3);
    }

    @Test
    void 사의_점수는_3이다() {
        assertThat(PieceFactory.advisor(Team.HAN).score()).isEqualTo(3);
    }

    @Test
    void 졸의_점수는_2이다() {
        assertThat(PieceFactory.soldier(Team.HAN).score()).isEqualTo(2);
    }

    @Test
    void 장의_점수는_0이다() {
        assertThat(PieceFactory.king(Team.HAN).score()).isEqualTo(0);
    }

    @Test
    void 각_기물은_자신의_타입을_알고_있다() {
        assertThat(PieceFactory.tank(Team.HAN).pieceType()).isEqualTo(PieceType.TANK);
        assertThat(PieceFactory.horse(Team.HAN).pieceType()).isEqualTo(PieceType.HORSE);
        assertThat(PieceFactory.elephant(Team.HAN).pieceType()).isEqualTo(PieceType.ELEPHANT);
        assertThat(PieceFactory.advisor(Team.HAN).pieceType()).isEqualTo(PieceType.ADVISOR);
        assertThat(PieceFactory.king(Team.HAN).pieceType()).isEqualTo(PieceType.KING);
        assertThat(PieceFactory.cannon(Team.HAN).pieceType()).isEqualTo(PieceType.CANNON);
        assertThat(PieceFactory.soldier(Team.HAN).pieceType()).isEqualTo(PieceType.SOLDIER);
    }
}
