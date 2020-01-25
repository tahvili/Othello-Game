package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.OthelloModel;
import ca.utoronto.utm.util.*;
import javafx.scene.control.Label;

/**
 * A class representing the outcome of the game
 * 
 * @author TheNerds
 *
 */
public class WinnerLabel extends Label implements Observer {

	@Override
	/**
	 * A Method that updates the Label's text when either the game is over or either
	 * of the player has run out of time
	 * 
	 * @param A concrete Observable
	 *
	 */
	public void update(Observable o) {
		OthelloModel m = (OthelloModel) o;

		if (m.getLostTime() == 'X' && !m.othello.othello.isGameOver()
				|| (m.othello.othello.isGameOver() && m.othello.othello.getWinner() == 'O')) {
			this.setText("Game Over. White token player won!");
		} else if (m.getLostTime() == 'O' && !m.othello.othello.isGameOver()
				|| (m.othello.othello.isGameOver() && m.othello.othello.getWinner() == 'X')) {
			this.setText("Game Over. Black token player won!");
		} else if (m.othello.othello.isGameOver() && m.othello.othello.getWinner() == ' ') {
			this.setText("Game Over. The game ended in a tie");
		} else if(this.getText()!="Game in Progress"){
			this.setText("Game in Progress");
		}
	}
}
