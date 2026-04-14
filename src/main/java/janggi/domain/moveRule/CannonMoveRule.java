package janggi.domain.moveRule;

import janggi.domain.BoardView;
import janggi.domain.palace.Palaces;
import janggi.domain.piece.Cannon;
import janggi.domain.piece.Piece;
import janggi.domain.vo.Position;
import java.util.Optional;

public class CannonMoveRule implements MoveRule {

    @Override
    public boolean canMove(Position from, Position to, BoardView board, Palaces palaces) {
        if (isStraightLine(from, to)) {
            return canMoveStraight(from, to, board);
        }
        if (palaces.isDiagonalPath(from, to)) {
            return canMovePalaceDiagonal(from, to, board, palaces);
        }
        return false;
    }

    private boolean canMovePalaceDiagonal(Position from, Position to, BoardView board, Palaces palaces) {
        Position midpoint = palaces.diagonalMidpoint(from, to);
        if (midpoint == null) {
            return false;
        }

        Optional<Piece> bridgePiece = board.findByPosition(midpoint);
        if (bridgePiece.isEmpty()) {
            return false;
        }

        return !isCannon(bridgePiece.get())
                && !isCannonAt(board, to);
    }

    private boolean canMoveStraight(Position from, Position to, BoardView board) {
        int fromRow = from.getRow();
        int fromCol = from.getCol();
        int toRow = to.getRow();
        int toCol = to.getCol();

        int jumpedPieceCount = countPiecesBetween(board, fromRow, fromCol, toRow, toCol);
        if (jumpedPieceCount != 1) {
            return false;
        }

        Optional<Piece> bridgePiece = findBridgePiece(board, fromRow, fromCol, toRow, toCol);
        return bridgePiece.isPresent()
                && !isCannon(bridgePiece.get())
                && !isCannonAt(board, to);
    }

    private boolean isStraightLine(Position from, Position to) {
        return from.getRow() == to.getRow() || from.getCol() == to.getCol();
    }

    private int countPiecesBetween(BoardView board, int fromRow, int fromCol, int toRow, int toCol) {
        int count = 0;

        if (fromRow == toRow) {
            int start = Math.min(fromCol, toCol);
            int end = Math.max(fromCol, toCol);

            for (int col = start + 1; col < end; col++) {
                if (!board.isEmptyPosition(new Position(fromRow, col))) {
                    count++;
                }
            }
            return count;
        }

        int start = Math.min(fromRow, toRow);
        int end = Math.max(fromRow, toRow);

        for (int row = start + 1; row < end; row++) {
            if (!board.isEmptyPosition(new Position(row, fromCol))) {
                count++;
            }
        }
        return count;
    }

    private Optional<Piece> findBridgePiece(BoardView board, int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow == toRow) {
            int start = Math.min(fromCol, toCol);
            int end = Math.max(fromCol, toCol);

            for (int col = start + 1; col < end; col++) {
                Position position = new Position(fromRow, col);
                Optional<Piece> piece = board.findByPosition(position);
                if (piece.isPresent()) {
                    return piece;
                }
            }
            return Optional.empty();
        }

        int start = Math.min(fromRow, toRow);
        int end = Math.max(fromRow, toRow);

        for (int row = start + 1; row < end; row++) {
            Position position = new Position(row, fromCol);
            Optional<Piece> piece = board.findByPosition(position);
            if (piece.isPresent()) {
                return piece;
            }
        }
        return Optional.empty();
    }

    private boolean isCannonAt(BoardView board, Position position) {
        return board.findByPosition(position)
                .map(this::isCannon)
                .orElse(false);
    }

    private boolean isCannon(Piece piece) {
        return piece instanceof Cannon;
    }
}
