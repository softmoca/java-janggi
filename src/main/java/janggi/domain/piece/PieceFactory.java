package janggi.domain.piece;

import janggi.domain.moveRule.AdvisorMoveRule;
import janggi.domain.moveRule.CannonMoveRule;
import janggi.domain.moveRule.ElephantMoveRule;
import janggi.domain.moveRule.HorseMoveRule;
import janggi.domain.moveRule.KingMoveRule;
import janggi.domain.moveRule.SoldierMoveRule;
import janggi.domain.moveRule.TankMoveRule;

public class PieceFactory {

    private PieceFactory() {
    }

    public static Piece tank(Team team) {
        return new Piece(team, PieceType.TANK, new TankMoveRule(), 13);
    }

    public static Piece horse(Team team) {
        return new Piece(team, PieceType.HORSE, new HorseMoveRule(), 5);
    }

    public static Piece elephant(Team team) {
        return new Piece(team, PieceType.ELEPHANT, new ElephantMoveRule(), 3);
    }

    public static Piece advisor(Team team) {
        return new Piece(team, PieceType.ADVISOR, new AdvisorMoveRule(), 3);
    }

    public static Piece king(Team team) {
        return new Piece(team, PieceType.KING, new KingMoveRule(), 0);
    }

    public static Piece cannon(Team team) {
        return new Piece(team, PieceType.CANNON, new CannonMoveRule(), 7);
    }

    public static Piece soldier(Team team) {
        return new Piece(team, PieceType.SOLDIER, new SoldierMoveRule(team), 2);
    }

    public static Piece of(PieceType type, Team team) {
        return switch (type) {
            case TANK -> tank(team);
            case HORSE -> horse(team);
            case ELEPHANT -> elephant(team);
            case ADVISOR -> advisor(team);
            case KING -> king(team);
            case CANNON -> cannon(team);
            case SOLDIER -> soldier(team);
        };
    }
}
