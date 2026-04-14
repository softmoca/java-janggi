package janggi.domain.moveRule;

import janggi.domain.BoardView;
import janggi.domain.palace.Palaces;
import janggi.domain.vo.Position;

public class KingMoveRule implements MoveRule {

    @Override
    public boolean canMove(Position from, Position to, BoardView board, Palaces palaces) {
        if (!palaces.isInsideAnyPalace(from) || !palaces.isInsideAnyPalace(to)) {
            return false;
        }
        return isStraightOneStep(from, to) || palaces.canMoveDiagonally(from, to);
    }

    private boolean isStraightOneStep(Position from, Position to) {
        int rowDis = Math.abs(to.getRow() - from.getRow());
        int colDis = Math.abs(to.getCol() - from.getCol());
        return (rowDis + colDis) == 1;
    }
}
