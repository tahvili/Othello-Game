package ca.utoronto.utm.othello.model;

/**
 * PlayerBetter chooses a corner if available,otherwise choose a side if
 * available, otherwise choose a space that maximizes the players presence in
 * the middle 4 by 4 square of the board,otherwise choose the location
 * maximizing the movers presence on the board.
 * 
 * @author TheNerds
 *
 */
public class PlayerBetter extends Player {
	public PlayerBetter(Othello othello, char player) {
		super(othello, player);
	}

	@Override
	/**
	 * A method to find the move to be made by the Better Player
	 * 
	 * @return A move to be made by the Better Player
	 */
	public Move getMove() {
		Othello othelloCopy = othello.copy();
		Move bestMove = new Move(3, 3); 
		int bestMoveCount = getMaxCount(othelloCopy, this.player);
		int i = 0; 
		boolean flag = false; //a variable to ensure the appropriate move is being returned
		int[][] arr = { { 0, 0 }, { 0, 7 }, { 7, 0 }, { 7, 7 } }; // a 2-D array consisting of the four corners of the board
		int[] r = { 0, 7 }; 
		for (int row = 0; row < 4; row++) { //checking if there's a possible move at the corners
			othelloCopy = othello.copy();
			if (othelloCopy.move(arr[row][i], arr[row][i + 1]) == true) {
				bestMove = new Move(arr[row][i], arr[row][i + 1]);
				flag = true;
				break;
			}
		}
		if (flag == false) { //checking for a possible moves on the top and bottom sides of the board
			for (int k = 0; k < r.length; k++) {
				for (int col = 0; col < Othello.DIMENSION; col++) {
					othelloCopy = othello.copy();
					if (othelloCopy.move(r[k], col)) {
						bestMove = new Move(r[k], col);
						flag = true;
						break;
					}
				}
			}
			if (flag == false) {
				for (int row = 0; row < Othello.DIMENSION; row++) { //checking for a possible moves on the right and left sides of the board
					for (int k = 0; k < r.length; k++) {
						othelloCopy = othello.copy();
						if (othelloCopy.move(row, r[k])) {
							bestMove = new Move(row, r[k]);
							flag = true;
							break;
						}
					}
				}
			}
			if (flag == false) { //checking for a move which maximizes the player's presence in the middle 4 by 4
				for (int row = 0; row < Othello.DIMENSION; row++) {
					for (int col = 0; col < Othello.DIMENSION; col++) {
						othelloCopy = othello.copy();
						if (othelloCopy.move(row, col) && getMaxCount(othelloCopy, this.player) >= bestMoveCount) {
							bestMoveCount = getMaxCount(othelloCopy, this.player);
							bestMove = new Move(row, col);
						}

					}
				}
			}
		}
		return bestMove;
	}

	/**
	 * A helper method to calculate the number of tokens in the middle 4 by 4 square of the board for the
	 *         BetterPlayer as a consequence of a move
	 * 
	 * @param othello The current Othello Game
	 * @param player  The Better player in the current Othello Game
	 * @return The number of tokens in the middle 4 by 4 square of the board for the
	 *         BetterPlayer
	 */

	private int getMaxCount(Othello othello, char player) {
		int cnt = 0; // a variable to store the number of tokens of player in the middle 4 by 4 square of the board
		for (int row = 2; row < 6; row++) {
			for (int col = 2; col < 6; col++) {
				if (othello.board.board[row][col] == player) {
					cnt++;
				}

			}
		}
		return cnt;
	}

}
