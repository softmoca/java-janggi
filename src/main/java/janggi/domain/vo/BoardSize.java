package janggi.domain.vo;

public class BoardSize {
    private static final int DEFAULT_ROW_COUNT = 10;
    private static final int DEFAULT_COL_COUNT = 9;

    public static final BoardSize JANGGI = new BoardSize(DEFAULT_ROW_COUNT, DEFAULT_COL_COUNT);

    private final int rowCount;
    private final int colCount;

    public BoardSize(int rowCount, int colCount) {
        if (rowCount <= 0 || colCount <= 0) {
            throw new IllegalArgumentException("보드 크기는 1 이상이어야 합니다.");
        }
        this.rowCount = rowCount;
        this.colCount = colCount;
    }

    public boolean contains(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        return row >= 0 && row < rowCount
                && col >= 0 && col < colCount;
    }

    public void validateContains(Position position) {
        if (!contains(position)) {
            throw new IllegalArgumentException(
                    "범위 밖의 좌표입니다: (" + position.getRow() + ", " + position.getCol() + ")");
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }
}
