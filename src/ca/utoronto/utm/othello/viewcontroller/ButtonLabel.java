package ca.utoronto.utm.othello.viewcontroller;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import ca.utoronto.utm.othello.model.OthelloModel;
import ca.utoronto.utm.util.*;

/**
 * A class representing the buttons present on the OthelloBoard
 * 
 * @author TheNerds
 *
 */
public class ButtonLabel extends Button implements Observer {
	private Image x = new Image("res/x.png");
	private Image o = new Image("res/o.png");
	private Image available = new Image("res/available.png");
	private Image empty = new Image("res/empty.png");

	/**
	 * Applying a FadeTransition to the token image
	 * 
	 * @param iView Image of the token to which the animation is to be applied
	 * @return A fade effect Animation
	 */
	public FadeTransition fadeout(ImageView iView) {
		FadeTransition ft = new FadeTransition();
		ft.setNode(iView);
		ft.setDuration(new Duration(2000));
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.setCycleCount(1);
		ft.setAutoReverse(true);
		return ft;
	}

	@Override
	/**
	 * A Method that updates the images of the tokens when the user (human or AI)
	 * plays a move.
	 * 
	 * @param A concrete Observable
	 */
	public void update(Observable observe) {

		OthelloModel model = (OthelloModel) observe;

		if (model.flag) {

			int col = GridPane.getColumnIndex(this);
			int row = GridPane.getRowIndex(this);
			
			// Updating buttons based on the token present on it 
			if (model.othello.othello.getToken(row, col) == 'X') {

				ImageView imageView = new ImageView(x);
				imageView.setFitHeight(30);
				imageView.setFitWidth(30);
				this.setGraphic(imageView);
				this.setId("unavailable");

				if (model.getAnimStatus(row, col)) {
					fadeout(imageView).play();
				}

			} else if (model.othello.othello.getToken(row, col) == 'O') {
				ImageView imageView = new ImageView(o);
				imageView.setFitHeight(30);
				imageView.setFitWidth(30);
				this.setGraphic(imageView);
				this.setId("unavailable");

				if (model.getAnimStatus(row, col)) {
					fadeout(imageView).play();
				}

			}
			else if (model.othello.othello.getToken(row, col) == ' ') {

				if (model.AvailableExists(row, col)) { //update a button if its an available move
					ImageView imageView = new ImageView(available);
					imageView.setFitHeight(30);
					imageView.setFitWidth(30);
					this.setGraphic(imageView);
					this.setId("available");

				} else {
					ImageView imageView = new ImageView(empty);
					imageView.setFitHeight(30);
					imageView.setFitWidth(30);
					this.setGraphic(imageView);
					this.setId("unavailable");
				}
			}

		}

	}
}
