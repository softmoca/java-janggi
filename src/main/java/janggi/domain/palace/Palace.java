package janggi.domain.palace;

import janggi.domain.vo.Position;

public abstract class Palace {

    private static final int MIN_COL = 3;
    private static final int MAX_COL = 5;

    public boolean contains(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        return isRowInside(row) && col >= MIN_COL && col <= MAX_COL;
    }

    public boolean canMoveDiagonally(Position from, Position to) {
        if (!contains(from) || !contains(to)) {
            return false;
        }
        if (!isOneStepDiagonal(from, to)) {
            return false;
        }
        return involvesCenter(from, to);
    }

    public boolean isDiagonalPath(Position from, Position to) {
        if (!contains(from) || !contains(to)) {
            return false;
        }
        return canMoveDiagonally(from, to) || isTwoStepDiagonal(from, to);
    }

    public Position diagonalMidpoint(Position from, Position to) {
        if (!isTwoStepDiagonal(from, to)) {
            return null;
        }
        int midRow = (from.getRow() + to.getRow()) / 2;
        int midCol = (from.getCol() + to.getCol()) / 2;
        return new Position(midRow, midCol);
    }

    private boolean isOneStepDiagonal(Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        return rowDiff == 1 && colDiff == 1;
    }

    private boolean isTwoStepDiagonal(Position from, Position to) {
        if (!contains(from) || !contains(to)) {
            return false;
        }
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        if (rowDiff != 2 || colDiff != 2) {
            return false;
        }
        int midRow = (from.getRow() + to.getRow()) / 2;
        int midCol = (from.getCol() + to.getCol()) / 2;
        Position midpoint = new Position(midRow, midCol);
        return midpoint.equals(center());
    }

    private boolean involvesCenter(Position from, Position to) {
        return from.equals(center()) || to.equals(center());
    }

    protected abstract Position center();

    protected abstract boolean isRowInside(int row);
}
