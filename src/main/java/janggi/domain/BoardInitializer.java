package janggi.domain;

import janggi.domain.piece.Piece;
import janggi.domain.piece.PieceFactory;
import janggi.domain.piece.Team;
import janggi.domain.vo.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardInitializer {

    private static final List<Integer> SOLDIER_COLS = List.of(0, 2, 4, 6, 8);
    private static final List<Integer> CANNON_COLS = List.of(1, 7);
    private static final List<Integer> TANK_COLS = List.of(0, 8);
    private static final List<Integer> HORSE_COLS = List.of(1, 7);
    private static final List<Integer> ELEPHANT_COLS = List.of(2, 6);
    private static final List<Integer> ADVISOR_COLS = List.of(3, 5);
    private static final int KING_COL = 4;

    private static final int HAN_BACK_ROW = 0;
    private static final int HAN_KING_ROW = 1;
    private static final int HAN_CANNON_ROW = 2;
    private static final int HAN_SOLDIER_ROW = 3;

    private static final int CHO_SOLDIER_ROW = 6;
    private static final int CHO_CANNON_ROW = 7;
    private static final int CHO_KING_ROW = 8;
    private static final int CHO_BACK_ROW = 9;

    public static Map<Position, Piece> createInitialPieces() {
        Map<Position, Piece> pieces = new HashMap<>();

        placeBackRank(pieces, HAN_BACK_ROW, Team.HAN);
        placeBackRank(pieces, CHO_BACK_ROW, Team.CHO);

        pieces.put(new Position(HAN_KING_ROW, KING_COL), PieceFactory.king(Team.HAN));
        pieces.put(new Position(CHO_KING_ROW, KING_COL), PieceFactory.king(Team.CHO));

        placeCannons(pieces, HAN_CANNON_ROW, Team.HAN);
        placeCannons(pieces, CHO_CANNON_ROW, Team.CHO);

        placeSoldiers(pieces, HAN_SOLDIER_ROW, Team.HAN);
        placeSoldiers(pieces, CHO_SOLDIER_ROW, Team.CHO);

        return pieces;
    }

    private static void placeBackRank(Map<Position, Piece> pieces, int row, Team team) {
        for (int col : TANK_COLS) {
            pieces.put(new Position(row, col), PieceFactory.tank(team));
        }
        for (int col : HORSE_COLS) {
            pieces.put(new Position(row, col), PieceFactory.horse(team));
        }
        for (int col : ELEPHANT_COLS) {
            pieces.put(new Position(row, col), PieceFactory.elephant(team));
        }
        for (int col : ADVISOR_COLS) {
            pieces.put(new Position(row, col), PieceFactory.advisor(team));
        }
    }

    private static void placeCannons(Map<Position, Piece> pieces, int row, Team team) {
        for (int col : CANNON_COLS) {
            pieces.put(new Position(row, col), PieceFactory.cannon(team));
        }
    }

    private static void placeSoldiers(Map<Position, Piece> pieces, int row, Team team) {
        for (int col : SOLDIER_COLS) {
            pieces.put(new Position(row, col), PieceFactory.soldier(team));
        }
    }
}
