package janggi.domain.palace;

import janggi.domain.vo.Position;

public class HanPalace extends Palace {

    private static final int MIN_ROW = 0;
    private static final int MAX_ROW = 2;
    private static final Position CENTER = new Position(1, 4);

    @Override
    protected Position center() {
        return CENTER;
    }

    @Override
    protected boolean isRowInside(int row) {
        return row >= MIN_ROW && row <= MAX_ROW;
    }
}
