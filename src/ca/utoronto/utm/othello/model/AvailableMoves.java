package ca.utoronto.utm.othello.model;

import java.util.ArrayList;

/**
 * A class representing all the currently available moves for a given player.
 * 
 * @author TheNerds
 *
 */
public class AvailableMoves extends Player {
	public AvailableMoves(Othello othello, char player) {
		super(othello, player);
	}

	/**
	 * A method to store all the available moves for a player.
	 * 
	 * @return An ArrayList consisting of all the available moves for a give player.
	 */
	public ArrayList<Move> getAllMove() {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				Othello othelloCopy = othello.copy();
				if (othelloCopy.move(row, col))
					moves.add(new Move(row, col));
			}
		}
		return moves;
	}

	@Override
	public Move getMove() {
		return null;
	}
}
