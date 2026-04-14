package janggi.domain;

import janggi.domain.piece.Piece;
import janggi.domain.vo.Position;
import java.util.Optional;

public interface BoardView {

    Optional<Piece> findByPosition(Position position);

    boolean isEmptyPosition(Position position);
}
