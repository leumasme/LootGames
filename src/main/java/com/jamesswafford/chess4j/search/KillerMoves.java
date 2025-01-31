package com.jamesswafford.chess4j.search;

import com.jamesswafford.chess4j.board.Move;

public class KillerMoves {

    private static final KillerMoves INSTANCE = new KillerMoves();

    private final int NUM_ENTRIES = 1000;
    private Move[] killer1;
    private Move[] killer2;

    private KillerMoves() {
        killer1 = new Move[NUM_ENTRIES];
        killer2 = new Move[NUM_ENTRIES];
    }

    public static KillerMoves getInstance() {
        return INSTANCE;
    }

    public void addKiller(int ply, Move killerMove) {
        assert (killerMove != null);
        assert (killerMove.captured() == null);

        if (!killerMove.equals(killer1[ply])) {
            if (!killerMove.equals(killer2[ply])) {
                killer2[ply] = killer1[ply];
                killer1[ply] = killerMove;
            } else {
                // swap them
                Move tmp = killer1[ply];
                killer1[ply] = killer2[ply];
                killer2[ply] = tmp;
            }
        }
    }

    public void clear() {
        for (int i = 0; i < NUM_ENTRIES; i++) {
            killer1[i] = null;
            killer2[i] = null;
        }
    }

    public Move getKiller1(int ply) {
        return killer1[ply];
    }

    public Move getKiller2(int ply) {
        return killer2[ply];
    }
}
