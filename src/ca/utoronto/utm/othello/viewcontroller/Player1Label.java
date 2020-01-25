package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.OthelloModel;
import ca.utoronto.utm.util.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * Class representing the type of player 1
 * 
 * @author TheNerds
 *
 */
public class Player1Label extends Label implements Observer {

	@Override
	/**
	 * Method that updates the Label's text to display who player 1 is
	 * 
	 * @param A concrete Observable
	 */
	public void update(Observable o) {
		OthelloModel m = (OthelloModel) o;
		this.setText("             is " + m.getPlayer1());
		this.setTextFill(Color.web("#ffffff"));
		this.setStyle(
				"-fx-background-image: url('res/x.png'); -fx-background-position: 5, center; -fx-background-repeat: no-repeat; -fx-background-size: 30px; "
						+ "-fx-background-color: #038a47; -fx-border-color: #038a47; -fx-font-weight:bold;");
		this.setMaxWidth(Double.MAX_VALUE);
		this.setMaxHeight(Double.MAX_VALUE);
	}
}
