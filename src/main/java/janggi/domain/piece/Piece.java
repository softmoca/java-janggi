package janggi.domain.piece;

import janggi.domain.BoardView;
import janggi.domain.moveRule.MoveRule;
import janggi.domain.palace.Palaces;
import janggi.domain.vo.Position;

public class Piece {

    private final Team team;
    private final PieceType type;
    private final MoveRule moveRule;
    private final int score;

    public Piece(Team team, PieceType type, MoveRule moveRule, int score) {
        this.team = team;
        this.type = type;
        this.moveRule = moveRule;
        this.score = score;
    }

    public boolean isSameTeam(Team otherTeam) {
        return team == otherTeam;
    }

    public Team findTeam() {
        return team;
    }

    public PieceType pieceType() {
        return type;
    }

    public int score() {
        return score;
    }

    public boolean canMove(Position from, Position to, BoardView board, Palaces palaces) {
        return moveRule.canMove(from, to, board, palaces);
    }

    public String display() {
        return team.findPrefix() + type.koreanName();
    }

    @Override
    public String toString() {
        return type.koreanName();
    }
}
