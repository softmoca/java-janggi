package janggi.domain;

import static org.assertj.core.api.Assertions.assertThat;

import janggi.domain.piece.Advisor;
import janggi.domain.piece.Cannon;
import janggi.domain.piece.Elephant;
import janggi.domain.piece.Horse;
import janggi.domain.piece.King;
import janggi.domain.piece.Piece;
import janggi.domain.piece.Soldier;
import janggi.domain.piece.Tank;
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
        assertTeamPieceCount(Team.HAN, Soldier.class, 5);
        assertTeamPieceCount(Team.HAN, Cannon.class, 2);
        assertTeamPieceCount(Team.HAN, King.class, 1);
        assertTeamPieceCount(Team.HAN, Tank.class, 2);
        assertTeamPieceCount(Team.HAN, Horse.class, 2);
        assertTeamPieceCount(Team.HAN, Elephant.class, 2);
        assertTeamPieceCount(Team.HAN, Advisor.class, 2);
    }

    @Test
    void 초나라_기물_개수가_올바르다() {
        assertTeamPieceCount(Team.CHO, Soldier.class, 5);
        assertTeamPieceCount(Team.CHO, Cannon.class, 2);
        assertTeamPieceCount(Team.CHO, King.class, 1);
        assertTeamPieceCount(Team.CHO, Tank.class, 2);
        assertTeamPieceCount(Team.CHO, Horse.class, 2);
        assertTeamPieceCount(Team.CHO, Elephant.class, 2);
        assertTeamPieceCount(Team.CHO, Advisor.class, 2);
    }

    private void assertTeamPieceCount(Team team, Class<? extends Piece> pieceClass, int expected) {
        long count = pieces.values().stream()
                .filter(piece -> piece.findTeam() == team)
                .filter(pieceClass::isInstance)
                .count();

        assertThat(count).isEqualTo(expected);
    }
}
