package janggi.domain;

import janggi.domain.palace.Palaces;
import janggi.domain.piece.Piece;
import janggi.domain.piece.Team;
import janggi.domain.vo.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Board implements BoardView {

    private final Map<Position, Piece> pieces;
    private final Palaces palaces;

    public Board() {
        this(BoardInitializer.createInitialPieces(), Palaces.standard());
    }

    private Board(Map<Position, Piece> pieces, Palaces palaces) {
        this.pieces = new HashMap<>(pieces);
        this.palaces = palaces;
    }

    public static Board empty() {
        return new Board(new HashMap<>(), Palaces.standard());
    }

    public static Board of(Map<Position, Piece> pieces) {
        return new Board(pieces, Palaces.standard());
    }

    @Override
    public Optional<Piece> findByPosition(Position position) {
        return Optional.ofNullable(pieces.get(position));
    }

    @Override
    public boolean isEmptyPosition(Position position) {
        return !pieces.containsKey(position);
    }

    public Palaces palaces() {
        return palaces;
    }

    public Piece move(Position from, Position to, Team currentTeam) {
        Piece fromPiece = pieces.get(from);
        Piece toPiece = pieces.get(to);

        validateCommonMove(currentTeam, fromPiece, toPiece);

        if (!fromPiece.canMove(from, to, this, palaces)) {
            throw new IllegalArgumentException("해당 기물의 이동 규칙에 맞지 않습니다.");
        }

        pieces.remove(from);
        pieces.put(to, fromPiece);

        return toPiece;
    }

    private void validateCommonMove(Team currentTeam, Piece fromPiece, Piece toPiece) {
        if (fromPiece == null) {
            throw new IllegalArgumentException("[ERROR] 선택하신 칸에 기물이 없습니다.");
        }

        if (!fromPiece.isSameTeam(currentTeam)) {
            throw new IllegalArgumentException("자신 진영의 기물을 선택해야합니다.");
        }

        if (toPiece != null && toPiece.isSameTeam(currentTeam)) {
            throw new IllegalArgumentException("이미 도착지점에 플레이어님의 진영 기물이 있습니다.");
        }
    }

    public int calculateScore(Team team) {
        return pieces.values().stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToInt(Piece::score)
                .sum();
    }

    public Map<Position, Piece> findAllPieces() {
        return new HashMap<>(pieces);
    }
}
