package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.othello.viewcontroller.Command;

/**
 * 
 * @author arnold
 *
 */
public class Move implements Command {
	private int row, col;
	OthelloModel model;

	public Move(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public Move(int row, int col, OthelloModel model) {
		this(row, col);
		this.model = model;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public String toString() {
		return "(" + this.row + "," + this.col + ")";
	}

	@Override
	/**
	 * A method to do the move
	 * 
	 */
	public void execute() {
		model.directMove(this.row, this.col);
	}
}
