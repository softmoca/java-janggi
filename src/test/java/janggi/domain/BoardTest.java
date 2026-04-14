package janggi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import janggi.domain.piece.Piece;
import janggi.domain.piece.PieceFactory;
import janggi.domain.piece.Team;
import janggi.domain.vo.Position;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private final Board board = new Board();

    @Test
    void 특정_좌표의_기물을_찾는다() {
        Position position = new Position(1, 4);

        Piece piece = board.findByPosition(position).orElseThrow();

        assertThat(piece.findTeam()).isEqualTo(Team.HAN);
        assertThat(piece).isEqualTo(PieceFactory.king(Team.HAN));
    }

    @Test
    void 빈_칸_조회시_Optional_empty를_반환한다() {
        Position position = new Position(4, 4);

        assertThat(board.findByPosition(position)).isEmpty();
    }

    @Test
    void 특정_좌표가_빈칸인지_확인한다() {
        Position position = new Position(1, 0);
        assertTrue(board.isEmptyPosition(position));
    }

    @Test
    void 이동시_잡힌_기물을_반환한다() {
        Board board = Board.of(Map.of(
                new Position(0, 0), PieceFactory.tank(Team.HAN),
                new Position(0, 3), PieceFactory.soldier(Team.CHO)
        ));

        Piece captured = board.move(
                new Position(0, 0), new Position(0, 3), Team.HAN);

        assertThat(captured).isEqualTo(PieceFactory.soldier(Team.CHO));
        assertThat(captured.findTeam()).isEqualTo(Team.CHO);
    }

    @Test
    void 빈칸으로_이동시_null을_반환한다() {
        Board board = Board.of(Map.of(
                new Position(0, 0), PieceFactory.tank(Team.HAN)
        ));

        Piece captured = board.move(
                new Position(0, 0), new Position(0, 3), Team.HAN);

        assertThat(captured).isNull();
    }

    @Test
    void 특정_진영의_점수를_계산한다() {
        Board board = Board.of(Map.of(
                new Position(0, 0), PieceFactory.tank(Team.HAN),
                new Position(3, 4), PieceFactory.soldier(Team.HAN),
                new Position(9, 0), PieceFactory.cannon(Team.CHO)
        ));

        assertThat(board.calculateScore(Team.HAN)).isEqualTo(15);
        assertThat(board.calculateScore(Team.CHO)).isEqualTo(7);
    }

    @Test
    void 이동_후_출발지점은_빈칸이_된다() {
        Board board = Board.of(Map.of(
                new Position(0, 0), PieceFactory.tank(Team.HAN)
        ));

        board.move(new Position(0, 0), new Position(0, 3), Team.HAN);

        assertThat(board.isEmptyPosition(new Position(0, 0))).isTrue();
        assertThat(board.isEmptyPosition(new Position(0, 3))).isFalse();
    }

    @Test
    void 전체_기물_조회시_빈칸은_포함되지_않는다() {
        Map<Position, Piece> pieces = board.findAllPieces();

        assertThat(pieces).hasSize(32);
    }
}
