package janggi.domain;

import static org.assertj.core.api.Assertions.assertThat;

import janggi.domain.piece.Piece;
import janggi.domain.piece.PieceType;
import janggi.domain.piece.Team;
import janggi.domain.vo.Position;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BoardInitializerTest {

    private final Map<Position, Piece> pieces = BoardInitializer.createInitialPieces();

    @Test
    void 초기_배치는_32개의_기물을_가진다() {
        assertThat(pieces).hasSize(32);
    }

    @Test
    void 한나라_기물_개수가_올바르다() {
        assertTeamPieceCount(Team.HAN, PieceType.SOLDIER, 5);
        assertTeamPieceCount(Team.HAN, PieceType.CANNON, 2);
        assertTeamPieceCount(Team.HAN, PieceType.KING, 1);
        assertTeamPieceCount(Team.HAN, PieceType.TANK, 2);
        assertTeamPieceCount(Team.HAN, PieceType.HORSE, 2);
        assertTeamPieceCount(Team.HAN, PieceType.ELEPHANT, 2);
        assertTeamPieceCount(Team.HAN, PieceType.ADVISOR, 2);
    }

    @Test
    void 초나라_기물_개수가_올바르다() {
        assertTeamPieceCount(Team.CHO, PieceType.SOLDIER, 5);
        assertTeamPieceCount(Team.CHO, PieceType.CANNON, 2);
        assertTeamPieceCount(Team.CHO, PieceType.KING, 1);
        assertTeamPieceCount(Team.CHO, PieceType.TANK, 2);
        assertTeamPieceCount(Team.CHO, PieceType.HORSE, 2);
        assertTeamPieceCount(Team.CHO, PieceType.ELEPHANT, 2);
        assertTeamPieceCount(Team.CHO, PieceType.ADVISOR, 2);
    }

    private void assertTeamPieceCount(Team team, PieceType type, int expected) {
        long count = pieces.values().stream()
                .filter(piece -> piece.findTeam() == team)
                .filter(piece -> piece.pieceType() == type)
                .count();

        assertThat(count).isEqualTo(expected);
    }
}
