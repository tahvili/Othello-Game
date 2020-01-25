package ca.utoronto.utm.othello.model;

public class BoardStringVisitor implements Visitor {

	/**
	 * A method that visits the Othello Game and gets the string representation of
	 * the current board
	 * 
	 * @return the string representation of the current OthelloBoard
	 */
	public String visit(Othello othello) {
		return othello.board.toString() + "\n";
	}

	@Override
	/**
	 * A method that visits the OthelloBoard, creates the string representation of
	 * the current board
	 * 
	 * @return the string representation of the current OthelloBoard
	 * 
	 */
	public String visit(OthelloBoard board) {
		String s = "";
		s += "  ";
		for (int col = 0; col < board.dim; col++) {
			s += col + " ";
		}
		s += '\n';

		s += " +";
		for (int col = 0; col < board.dim; col++) {
			s += "-+";
		}
		s += '\n';

		for (int row = 0; row < board.dim; row++) {
			s += row + "|";
			for (int col = 0; col < board.dim; col++) {
				s += board.board[row][col] + "|";
			}
			s += row + "\n";

			s += " +";
			for (int col = 0; col < board.dim; col++) {
				s += "-+";
			}
			s += '\n';
		}
		s += "  ";
		for (int col = 0; col < board.dim; col++) {
			s += col + " ";
		}
		s += '\n';
		return s;
	}
}
