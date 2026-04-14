package janggi.domain.palace;

import static org.assertj.core.api.Assertions.assertThat;

import janggi.domain.vo.Position;
import org.junit.jupiter.api.Test;

class PalacesTest {

    private final Palaces palaces = Palaces.standard();

    @Test
    void 두_궁성_중_어느_한쪽에라도_속하면_내부로_판별한다() {
        assertThat(palaces.isInsideAnyPalace(new Position(1, 4))).isTrue();
        assertThat(palaces.isInsideAnyPalace(new Position(8, 4))).isTrue();
        assertThat(palaces.isInsideAnyPalace(new Position(4, 4))).isFalse();
    }

    @Test
    void 같은_궁성_내에서는_대각선_이동이_판별된다() {
        assertThat(palaces.canMoveDiagonally(
                new Position(1, 4), new Position(0, 3))).isTrue();
        assertThat(palaces.canMoveDiagonally(
                new Position(8, 4), new Position(9, 5))).isTrue();
    }

    @Test
    void 다른_궁성_사이의_대각선은_불가() {
        assertThat(palaces.canMoveDiagonally(
                new Position(2, 5), new Position(7, 3))).isFalse();
    }

    @Test
    void 궁성_외부에서_출발하는_대각선은_불가() {
        assertThat(palaces.canMoveDiagonally(
                new Position(4, 4), new Position(5, 5))).isFalse();
    }
}
