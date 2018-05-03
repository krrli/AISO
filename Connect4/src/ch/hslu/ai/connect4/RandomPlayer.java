package ch.hslu.ai.connect4;

import ch.hslu.ai.connect4.Player;

/**
 * A random computer player for illustration how to implement the Player interface.
 * Also, you may use this player to test your own implementation.
 * 
 * @author Marc Pouly
 */

public class RandomPlayer extends Player {
	
	/**
	 * Constructor:
	 * @param name The name of this computer player
	 */
	
	public RandomPlayer(String name) {
		super(name);
	}
	
    /**
     * The following method allows you to implement your own game intelligence.
     * At the moment, this is a dumb random number generator.
     * The method must return the column number where the computer player puts the next disc. 
     * board[i][j] = cell content at position (i,j), i = column, j = row
     * 
     * If board[i][j] = this.getSymbol(), the cell contains one of your discs
     * If board[i][j] = '-', the cell is empty
     * Otherwise, the cell contains one of your opponent's discs
     * @param board The current game board
     * @return The columns number where you want to put your disc
     */

	@Override
    public int play(char[][] board) {

		int column = -1;
		int aInARow = -1;
		int bInARow = -1;
		int aInAColumn = -1;
		int bInAColumn = -1;

		//First Move
		if (board[3][5] == '-') {
			return 3;
		}else if (board[3][5] == 'b' && board[3][4] == '-') {
			return 3;
		}

		//Second / Third Move
		if (board[2][5] == '-') {
			return 2;
		} else if (board[4][5] == '-') {
			return 4;
		} else if ((board[2][5] == 'a' && board[3][5] == 'a' && board[4][5] == 'a') && (board[1][5] == '-' || board[5][5] == '-')) {
			if(board[1][5] == '-') {
				return 1;
			} else {
				return 5;
			}
		} else if ((board[1][5] == 'a' && board[2][5] == 'a' && board[3][5] == 'a') && (board[0][5] == '-' || board[3][5] == '-')) {
			if(board[0][5] == '-') {
				return 0;
			} else {
				return 3;
			}
		} else if ((board[3][5] == 'a' && board[4][5] == 'a' && board[5][5] == 'a') && (board[2][5] == '-' || board[6][5] == '-')) {
			if (board[2][5] == '-') {
				return 2;
			} else {
				return 6;
			}
		}

		//Third Move
		if (board[2][5] == 'b' && board[3][5] == 'b' && board[1][5] == '-') {
			return 1;
		} else if (board[3][5] == 'b' && board[4][5] == 'b' && board[5][5] == '-') {
			return 5;
		}

		//Check for Columns of b's and a's
		for (int i = 0; i < 7; i++) {
			aInAColumn = 0;
			bInAColumn = 0;

			for (int j = 0; j < 6; j++) {

				if (board[i][j] == 'a') {
					aInAColumn++;
				} else if (board[i][j] == 'b') {
					bInAColumn++;
				}

				if(5 < j && j >= 1)
					if (bInAColumn >= 2 && board[i][j + 1] == 'b' && board[i][j] == 'b' && board[i][j - 1] == '-') {
							return i;
					} else if (aInAColumn >= 2 && board[i][j + 1] == 'a'&& board[i][j] == 'a' && board[i][j - 1] == '-') {
						return i;
					}

			}
		}

		//Check for Row's of b's and a's
		for (int i = 0; i < 6; i++) {
			aInARow = 0;
			bInARow = 0;

			for (int j = 0; j < 7; j++) {

				if (board[j][i] == 'a') {
					aInARow++;
				} else if (board[j][i] == 'b') {
					bInARow++;
				}


				if (j == 6) {
					if (bInARow >= 2 && board[j][i] == 'b' && board[j - 1][i] == 'b' && board[j - 2][i] == '-') {
						if (board[j - 2][i] == '-' ) {
							if(i < 5) {
								if(board[j - 2][i + 1] != '-') {
									return j - 2;
								}
							} else {
								if(board[j - 2][i] == '-') {
									return j - 2;
								}
							}
						}
					}
					if (aInARow >= 3 && board[j][i] == 'a'  && board[j - 1][i] == 'a' && board[j - 2][i] == 'a' && board[j - 3][i] == '-') {
						if (board[j - 3][i] == '-') {
							if(i < 5) {
								if(board[j - 3][i + 1] != '-') {
									return j - 3;
								}
							} else {
								if(board[j - 3][i] == '-') {
									return j - 3;
								}
							}
						}
					}
				}

				if (j < 6 && j >= 3) {
					if ((bInARow >= 2 && board[j][i] == 'b' && board[j - 1][i] == 'b') && (board[j + 1][i] == '-' || board[j - 2][i] == '-' || board[j - 3][i] == '-')) {
						if (board[j + 1][i] == '-') {
							if(i < 5) {
								if(board[j + 1][i + 1] != '-') {
									return j + 1;
								}
							} else {
								if(board[j + 1][i] == '-') {
									return j + 1;
								}
							}
						} else if (board[j - 2][i] == '-') {
							if(i < 5) {
								if(board[j - 2][i + 1] != '-') {
									return j - 2;
								}
							} else {
								if(board[j - 2][i] == '-') {
									return j - 2;
								}
							}
						} else if(board[j - 3][i] == '-') {
							if(i < 5) {
								if(board[j - 3][i + 1] != '-') {
									return j - 3;
								}
							} else {
								if(board[j - 3][i] == '-') {
									return j - 3;
								}
							}
						}
					} else if ((aInARow >= 3 && board[j][i] == 'a' && board[j - 1][i] == 'a' && board[j - 2][i] == 'a') && (board[j + 1][i] == '-' || board[j - 3][i] == '-')) {
						if (board[j + 1][i] == '-') {
							if(i < 5) {
								if(board[j + 1][i + 1] != '-') {
									return j + 1;
								}
							} else {
								if (board[j + 1][i] == '-') {
									return j + 1;
								}
							}
						} else if (board[j - 3][i] == '-') {
							if(i < 5) {
								if(board[j - 3][i + 1] != '-') {
									return j - 3;
								}
							} else {
								if(board[j - 3][i] == '-') {
									return j - 3;
								}
							}
						}
					}
				}

				if (j < 6 && j >= 2) {
					if ((bInARow >= 2 && board[j][i] == 'b' && board[j - 1][i] == 'b') && (board[j + 1][i] == '-' || board[j - 2][i] == '-')) {
						if (board[j + 1][i] == '-' && board[j + 1][i + 1] == '-') {
							return j + 1;
						} else if (board[j - 2][i] == '-') {
							if(i < 5) {
								if(board[j - 2][i + 1] != '-') {
									return j - 2;
								}
							} else {
								if(board[j - 2][i] == '-'){
									return j - 2;
								}
							}
						}
					} else if ((aInARow >= 2 && board[j][i] == 'a' && board[j - 1][i] == 'a') && (board[j + 1][i] == '-' || board[j - 2][i] == '-')) {
						if (board[j + 1][i] == '-') {
							if(i < 5) {
								if(board[j + 1][i + 1] != '-') {
									return j + 1;
								}
							} else {
								if(board[j + 1][i] == '-') {
									return j + 1;
								}
							}
						} else if (board[j - 2][i] == '-') {
							if(i < 5) {
								if(board[j - 2][i + 1] != '-') {
									return j - 2;
								}
							} else {
								if(board[j - 2][i] == '-') {
									return j - 2;
								}
							}
						}
					}
				}
			}
		}

		//Try stack up 4
		for (int i = 0; i < 7; i++) {
			aInAColumn = 0;
			bInAColumn = 0;

			for (int j = 0; j < 6; j++) {

				if (board[i][j] == 'a') {
					aInAColumn++;
				} else if (board[i][j] == 'b') {
					bInAColumn++;
				}

				if(5 < j && j >= 1)
					 if (aInAColumn >= 1 && board[i][j] == 'a' && board[i][j - 1] == '-') {
						return i;
					}

			}
		}


		do {
			column = (int) (Math.random() * board.length);
		} while (board[column][0] != '-');
		return column;

		//Lol
		/*
		if(board[0][0] == '-') {
			return 0;
		} else if(board[1][0] == '-') {
			return 1;
		} else if(board[2][0] == '-') {
			return 2;
		} else if(board[3][0] == '-') {
			return 3;
		} else if(board[4][0] == '-') {
			return 4;
		} else if(board[5][0] == '-') {
			return 5;
		} else {
			return 6;
		} */

	}

}
