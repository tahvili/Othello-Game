package ca.utoronto.utm.othello.model;

public class TokenVisitor implements Visitor {

	private int row;
	private int col;

	public TokenVisitor(int r, int c) {
		this.row = r;
		this.col = c;
	}

	@Override
	/**
	 * Visits the Othello Game and gets the token of the player at a specified
	 * position
	 * 
	 * @return the token present at specified position
	 */
	public Character visit(Othello othello) {
		return Character.valueOf(othello.board.get(this.row, this.col));
	}

	@Override
	/**
	 * Visits the OthelloBoard and gets the token of the player at a specified
	 * position if its a valid position
	 * 
	 * @return the token present at specified position
	 */
	public Object visit(OthelloBoard board) {
		if (0 <= row && row < board.dim && 0 <= col && col < board.dim) {
			return board.board[row][col];
		} else
			return OthelloBoard.EMPTY;
	}

}
