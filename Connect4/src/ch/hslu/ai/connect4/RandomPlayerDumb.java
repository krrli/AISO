package ch.hslu.ai.connect4;

import ch.hslu.ai.connect4.Player;

/**
 * Created by chmoma01 on 24.04.18.
 */
public class RandomPlayerDumb extends Player {

    public RandomPlayerDumb(String name) {
        super(name);
    }

    @Override
    public int play(char[][] board) {
        int column = -1;
        do {
            column = (int)(Math.random() * board.length);
        } while(board[column][0] != '-');
        return column;
    }
}
