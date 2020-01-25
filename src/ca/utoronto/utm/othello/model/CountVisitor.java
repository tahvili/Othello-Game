package ca.utoronto.utm.othello.model;

public class CountVisitor implements Visitor {

	private char player;

	public CountVisitor(char p) {
		this.player = p;
	}

	/**
	 * A method that visits the Othello Game and gets the number of tokens present
	 * on the board for the specified player
	 * 
	 * @return the number of tokens for the specified player
	 */
	public Integer visit(Othello othello) {
		return othello.board.getCount(this.player);

	}

	/**
	 * A method that visits the OthelloBoard class, counts the number of token
	 * present on the current board for the specified player
	 * 
	 * @return the number of tokens for the specified player
	 */
	public Integer visit(OthelloBoard board) {
		int count = 0;
		for (int row = 0; row < board.dim; row++) {
			for (int col = 0; col < board.dim; col++) {
				if (board.board[row][col] == player)
					count++;
			}
		}
		return count;
	}
}
