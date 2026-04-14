package janggi.domain.moveRule;

import static org.assertj.core.api.Assertions.assertThat;

import janggi.domain.Board;
import janggi.domain.palace.Palaces;
import janggi.domain.piece.PieceFactory;
import janggi.domain.piece.Team;
import janggi.domain.vo.Position;
import java.util.Map;
import org.junit.jupiter.api.Test;


class CannonMoveRuleTest {

    private Board board = Board.empty();
    private final Palaces palaces = Palaces.standard();
    private final MoveRule moveRule = new CannonMoveRule();

    @Test
    void 정확히_하나의_기물을_넘어이동할수_있다() {
        Board board = Board.of(Map.of(
                new Position(0, 1), PieceFactory.soldier(Team.HAN)
        ));

        assertThat(moveRule.canMove(new Position(0, 0), new Position(0, 6), board, palaces)).isTrue();
    }

    @Test
    void 넘을기물이_없으면_이동할수없다() {
        assertThat(moveRule.canMove(new Position(0, 0), new Position(0, 5), board, palaces)).isFalse();
    }

    @Test
    void 경로에_기물이_2개_이상이면_이동할수없다() {
        Board board = Board.of(Map.of(
                new Position(0, 2), PieceFactory.soldier(Team.HAN),
                new Position(0, 3), PieceFactory.soldier(Team.HAN)
        ));

        assertThat(moveRule.canMove(new Position(0, 0), new Position(0, 6), board, palaces)).isFalse();
    }

    @Test
    void 포를_포다리로_사용할수없다() {
        Board board = Board.of(Map.of(
                new Position(5, 2), PieceFactory.cannon(Team.HAN)
        ));
        assertThat(moveRule.canMove(new Position(5, 0), new Position(5, 6), board, palaces)).isFalse();
    }

    @Test
    void 포를잡을_수_없다() {
        Board board = Board.of(Map.of(
                new Position(0, 2), PieceFactory.soldier(Team.HAN),
                new Position(0, 6), PieceFactory.cannon(Team.HAN)
        ));

        assertThat(moveRule.canMove(new Position(0, 0), new Position(0, 6), board, palaces)).isFalse();
    }

    // 궁성 대각선
    @Test
    void 궁성_대각선_2칸_이동시_중간에_기물_1개_넘으면_이동_가능() {
        Board board = Board.of(Map.of(
                new Position(1, 4), PieceFactory.soldier(Team.HAN)
        ));
        assertThat(moveRule.canMove(
                new Position(0, 3), new Position(2, 5), board, palaces)).isTrue();
    }

    @Test
    void 초_궁성에서도_대각선_2칸_이동_가능() {
        Board board = Board.of(Map.of(
                new Position(8, 4), PieceFactory.soldier(Team.CHO)
        ));
        assertThat(moveRule.canMove(
                new Position(7, 3), new Position(9, 5), board, palaces)).isTrue();
    }

    @Test
        // 아래 테스트들 PR
    void 궁성_대각선_2칸_이동시_중간에_기물_없으면_이동_불가() {
        assertThat(moveRule.canMove(
                new Position(0, 3), new Position(2, 5), board, palaces)).isFalse();
    }

    @Test
    void 궁성_대각선_2칸_이동시_다리가_포이면_이동_불가() {
        Board board = Board.of(Map.of(
                new Position(1, 4), PieceFactory.cannon(Team.CHO)
        ));
        assertThat(moveRule.canMove(
                new Position(0, 3), new Position(2, 5), board, palaces)).isFalse();
    }

    @Test
    void 궁성_대각선_2칸_이동시_도착칸에_포가_있으면_이동_불가() {
        Board board = Board.of(Map.of(
                new Position(1, 4), PieceFactory.soldier(Team.HAN),
                new Position(2, 5), PieceFactory.cannon(Team.CHO)
        ));
        assertThat(moveRule.canMove(
                new Position(0, 3), new Position(2, 5), board, palaces)).isFalse();
    }

    @Test
    void 궁성_대각선_1칸은_넘을_기물이_없으므로_이동_불가() {
        assertThat(moveRule.canMove(
                new Position(1, 4), new Position(0, 3), board, palaces)).isFalse();
    }


}
