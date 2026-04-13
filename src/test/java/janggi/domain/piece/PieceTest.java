package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    void 차의_점수는_13이다() {
        Piece piece = new Tank(Team.HAN);
        assertThat(piece.score()).isEqualTo(13);
    }

    @Test
    void 포의_점수는_7이다() {
        Piece piece = new Cannon(Team.HAN);
        assertThat(piece.score()).isEqualTo(7);
    }

    @Test
    void 마의_점수는_5이다() {
        Piece piece = new Horse(Team.HAN);
        assertThat(piece.score()).isEqualTo(5);
    }

    @Test
    void 상의_점수는_3이다() {
        Piece piece = new Elephant(Team.HAN);
        assertThat(piece.score()).isEqualTo(3);
    }

    @Test
    void 사의_점수는_3이다() {
        Piece piece = new Advisor(Team.HAN);
        assertThat(piece.score()).isEqualTo(3);
    }

    @Test
    void 졸의_점수는_2이다() {
        Piece piece = new Soldier(Team.HAN);
        assertThat(piece.score()).isEqualTo(2);
    }

    @Test
    void 장의_점수는_0이다() {
        Piece piece = new King(Team.HAN);
        assertThat(piece.score()).isEqualTo(0);
    }

    @Test
    void 빈칸의_점수는_0이다() {
        Piece piece = new EmptyPosition(Team.OTHER);
        assertThat(piece.score()).isEqualTo(0);
    }

    @Test
    void 각_기물은_자신의_타입을_알고_있다() {
        assertThat(new Tank(Team.HAN).pieceType()).isEqualTo(PieceType.TANK);
        assertThat(new Horse(Team.HAN).pieceType()).isEqualTo(PieceType.HORSE);
        assertThat(new Elephant(Team.HAN).pieceType()).isEqualTo(PieceType.ELEPHANT);
        assertThat(new Advisor(Team.HAN).pieceType()).isEqualTo(PieceType.ADVISOR);
        assertThat(new King(Team.HAN).pieceType()).isEqualTo(PieceType.KING);
        assertThat(new Cannon(Team.HAN).pieceType()).isEqualTo(PieceType.CANNON);
        assertThat(new Soldier(Team.HAN).pieceType()).isEqualTo(PieceType.SOLDIER);
    }


}
