package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.OthelloModel;
import ca.utoronto.utm.util.*;
import javafx.scene.control.Label;

/**
 * A class that updates the label displaying the number of tokens of player 1
 * 
 * @author TheNerds
 *
 */
public class TokenLabelX extends Label implements Observer {

	@Override
	/**
	 * Updating the label's text to the number of tokens currently on the board for
	 * player 1
	 * 
	 * @param A concrete Observable
	 */
	public void update(Observable o) {
		OthelloModel m = (OthelloModel) o;
		this.setText(" " + m.othello.othello.getCount('X'));
	}
}
