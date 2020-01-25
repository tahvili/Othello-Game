package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.OthelloModel;
import ca.utoronto.utm.util.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;

/**
 * A class representing the label that display's which player is currently
 * playing
 * 
 * @author TheNerds
 *
 */
public class CurrentPlayerLabel extends Label implements Observer {

	@Override
	/**
	 * A Method that updates who's turn it is currently
	 * 
	 * @param A concrete Observable
	 */
	public void update(Observable observe) {
		OthelloModel m = (OthelloModel) observe;
		Image x = new Image("res/x.png");
		Image o = new Image("res/o.png");
		ImageView OimageView = new ImageView(o);
		ImageView XimageView = new ImageView(x);
		OimageView.setFitHeight(30);
		OimageView.setFitWidth(30);
		XimageView.setFitHeight(30);
		XimageView.setFitWidth(30);
		if (!m.othello.othello.isGameOver() || m.getLostTime()== ' ') {
			if (m.othello.othello.getWhosTurn() == 'X') {
				this.setGraphic(XimageView);
				this.setText(" is now playing! ");
			} else if (m.othello.othello.getWhosTurn() == 'O') {
				this.setGraphic(OimageView);
				this.setText(" is now playing! ");
			}
		}
		if(m.othello.othello.isGameOver() || m.getLostTime()!=' ') { 
			this.setGraphic(null);
			this.setText("   GAME OVER!!");
			this.setTextAlignment(TextAlignment.CENTER);
		}
	}
}
