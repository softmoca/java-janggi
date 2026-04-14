package janggi.domain.palace;

import janggi.domain.vo.Position;
import java.util.List;
import java.util.Optional;

public class Palaces {

    private final List<Palace> palaces;

    public Palaces(List<Palace> palaces) {
        this.palaces = List.copyOf(palaces);
    }

    public static Palaces standard() {
        return new Palaces(List.of(new HanPalace(), new ChoPalace()));
    }

    public boolean isInsideAnyPalace(Position position) {
        return palaces.stream().anyMatch(palace -> palace.contains(position));
    }

    public boolean canMoveDiagonally(Position from, Position to) {
        return findContaining(from)
                .map(palace -> palace.canMoveDiagonally(from, to))
                .orElse(false);
    }

    public boolean isDiagonalPath(Position from, Position to) {
        return findContaining(from)
                .map(palace -> palace.isDiagonalPath(from, to))
                .orElse(false);
    }

    public Position diagonalMidpoint(Position from, Position to) {
        return findContaining(from)
                .map(palace -> palace.diagonalMidpoint(from, to))
                .orElse(null);
    }

    private Optional<Palace> findContaining(Position position) {
        return palaces.stream()
                .filter(palace -> palace.contains(position))
                .findFirst();
    }
}
