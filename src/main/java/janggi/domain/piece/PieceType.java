package janggi.domain.piece;

public enum PieceType {
    TANK("차"),
    HORSE("마"),
    ELEPHANT("상"),
    ADVISOR("사"),
    KING("장"),
    CANNON("포"),
    SOLDIER("졸");

    private final String koreanName;

    PieceType(String koreanName) {
        this.koreanName = koreanName;
    }

    public String koreanName() {
        return koreanName;
    }
}
