package ca.utoronto.utm.othello.model;

public class MoveVisitor implements Visitor {

	private int row;
	private int col;
	private char player;

	public MoveVisitor(int r, int c) {
		this.row = r;
		this.col = c;
	}

	public MoveVisitor(int r, int c, char player) {
		this.row = r;
		this.col = c;
		this.player = player;
	}

	/**
	 * A method that visits the Othello Game and checks if a move can be made and
	 * makes the move if possible
	 * 
	 * @return whether a move was successfully made
	 */
	public Boolean visit(Othello othello) {
		char currentTurn = othello.getWhosTurn();
		if (othello.board.move(row, col, othello.getWhosTurn())) {
			char allowedMove = othello.board.hasMove();
			if (allowedMove != OthelloBoard.BOTH)
				currentTurn = allowedMove;
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * A method that visits the OthelloBoard and makes a move at a position and
	 * modifies the board based on the move
	 * 
	 * @return whether a move was successfully made
	 */
	public Boolean visit(OthelloBoard board) {
		if (board.board[row][col] != OthelloBoard.EMPTY)
			return false;

		int numChangedTotal = 0;

		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				if (drow == 0 && dcol == 0)
					continue;
				int numChanged = flip(row + drow, col + dcol, drow, dcol, player, board);
				if (numChanged >= 0)
					numChangedTotal += numChanged;
			}
		}
		if (numChangedTotal > 0) {
			board.board[row][col] = player;
			return true;
		}
		return false;
	}

	/**
	 * flip all other player tokens to player, starting at (row,col) in direction
	 * (drow, dcol). Example: If (drow,dcol)=(0,1) and player==O then XXXO will
	 * result in a flip to OOOO
	 * 
	 * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow   the row direction, in {-1,0,1}
	 * @param dcol   the col direction, in {-1,0,1}
	 * @param player Either OthelloBoard.P1 or OthelloBoard.P2, the target token to
	 *               flip to.
	 * @return the number of other player tokens actually flipped, -1 if this is not
	 *         a valid move in this one direction, that is, EMPTY or the end of the
	 *         board is reached before seeing a player token.
	 */
	private int flip(int row, int col, int drow, int dcol, char player, OthelloBoard board) {
		if (0 > row || row >= board.dim || 0 > col || col >= board.dim)
			return -1;
		if (board.board[row][col] == board.EMPTY)
			return -1;
		if (board.board[row][col] == player)
			return 0;
		if (board.board[row][col] == board.otherPlayer(player)) {
			int numChanged = flip(row + drow, col + dcol, drow, dcol, player, board);
			if (numChanged >= 0) {
				board.board[row][col] = player;
				return numChanged + 1;
			} else {
				return numChanged;
			}
		}
		return -1; // Should not get here!
	}
}
