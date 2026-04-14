package janggi.domain.palace;

import janggi.domain.vo.Position;

public class ChoPalace extends Palace {

    private static final int MIN_ROW = 7;
    private static final int MAX_ROW = 9;
    private static final Position CENTER = new Position(8, 4);

    @Override
    protected Position center() {
        return CENTER;
    }

    @Override
    protected boolean isRowInside(int row) {
        return row >= MIN_ROW && row <= MAX_ROW;
    }
}
