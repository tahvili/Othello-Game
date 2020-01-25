package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.OthelloModel;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * Class representing the time label for player 1
 * 
 * @author TheNerds
 *
 */
public class TimeLabelX extends Label implements Observer {
	private Timer t;

	@Override
	/**
	 * A Method that updates the Label's text to display the time left for player 1
	 * 
	 * @param A concrete Observable
	 */
	public void update(Observable o) {
		OthelloModel m = (OthelloModel) o;
		t = m.timer;
		this.setText("   Time Left: " + t.getTime());
		this.setTextFill(Color.web("#000"));
		this.setStyle(
				"-fx-background-color: #038a47; -fx-border-color: #000000 #038a47 #038a47 #038a47; -fx-font-weight:bold;");
		this.setMaxWidth(Double.MAX_VALUE);
		this.setMaxHeight(Double.MAX_VALUE);
	}

}
