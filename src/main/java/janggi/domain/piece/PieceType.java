package janggi.domain.piece;

public enum PieceType {
    TANK,
    HORSE,
    ELEPHANT,
    ADVISOR,
    KING,
    CANNON,
    SOLDIER;
    

    public Piece createPiece(Team team) {
        if (this == TANK) {
            return new Tank(team);
        }
        if (this == HORSE) {
            return new Horse(team);
        }
        if (this == ELEPHANT) {
            return new Elephant(team);
        }
        if (this == ADVISOR) {
            return new Advisor(team);
        }
        if (this == KING) {
            return new King(team);
        }
        if (this == CANNON) {
            return new Cannon(team);
        }
        if (this == SOLDIER) {
            return new Soldier(team);
        }
        throw new IllegalArgumentException("알 수 없는 기물 타입입니다.");
    }
}
