package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.OthelloModel;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * A class representing the "Steve Job's Hint" Button (BetterHint Button)
 * 
 * @author TheNerds
 *
 */

public class BetterHint extends Button implements Observer {

	private Node source; // source of the Steve Job's Hint Button
	private Node single; // source of the SingleHint Button
	private Node random; // source of the RandomHint Button
	private Node greedy; // source of the GreedyHint Button

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
	 * A method that updates the View based on what the BetterHint is
	 * 
	 */
	public void update(Observable obs) {

		OthelloModel model = (OthelloModel) obs;
		
		if(model.othello.othello.isGameOver() || model.getLostTime()!=' ') {
			model.hint="";
			this.setDisable(true);
		} else if (model.RestartButtonPressed){
			this.setDisable(false);
		}

		if (model.getHint() == "Better" && model.BetterAlreadyMade) {

			ImageView available = new ImageView(new Image("res/available.png"));
			available.setFitHeight(30);
			available.setFitWidth(30);

			ImageView hint = new ImageView(new Image("res/hint.png"));
			hint.setFitHeight(30);
			hint.setFitWidth(30);

			Move hintMove = model.betterMove();

			for (Node source : model.grid.getChildren()) {

				if (source instanceof ButtonLabel) {
					ButtonLabel btn = (ButtonLabel) source;

					if (!model.othello.othello.isGameOver()) {
						if (GridPane.getColumnIndex(source) == hintMove.getCol()
								&& GridPane.getRowIndex(source) == hintMove.getRow()) { // displaying the hint on the
																						// GUI to the user
							btn.setGraphic(hint);
							btn.setId("hint");
							this.source = source;
							model.SingleAlreadyMade = false;
						}
					}
				}

				if (source instanceof Button) { // disabling other hint buttons when Steve Job's Hint button has been
												// activated
					Button button = (Button) source;
					if (button.getText() == "RANDOM HINTS") {
						button.setDisable(true);
						this.random = source;
					}
					if (button.getText() == "WINNING HINTS") {
						button.setDisable(true);
						this.greedy = source;
					}
					if (button.getText() == "GET HINT") {
						button.setDisable(true);
						this.single = source;
					}
				}

			}
			// changing the color of the Steve Job's Hint button when selected
			this.setStyle(
					"-fx-background-color: #9000b9; -fx-border-color: #9000b9; -fx-border-width: 0px; -fx-font-weight:bold; -fx-font-size:11px;");

		}

		if ((model.getHint() == "")) {
			// changing the color of the Steve Job's Hint button back to original when it's unselected
			this.setStyle(
					"-fx-background-color: #000000; -fx-border-color: #000000; -fx-border-width: 0px; -fx-font-weight:bold; -fx-font-size:11px;");

			if (this.source != null) { // changing the highlighted hint color back to normal available move color
				model.BetterAlreadyMade = true;
				ButtonLabel btn = (ButtonLabel) this.source;
				if (model.AvailableExists(GridPane.getRowIndex(btn), GridPane.getColumnIndex(btn))) {
					ImageView available = new ImageView(new Image("res/available.png"));
					available.setFitHeight(30);
					available.setFitWidth(30);
					btn.setGraphic(available);
					btn.setId("available");
				} else {

					char current = model.othello.othello.getToken(GridPane.getRowIndex(btn),
							GridPane.getColumnIndex(btn));
					if (current != ' ') { // if the user uses the hint, making appropriate changes
						ImageView imageView = new ImageView(new Image("res/" + current + ".png"));
						imageView.setFitHeight(30);
						imageView.setFitWidth(30);
						btn.setGraphic(imageView);
						btn.setId("unavailable");
						if (model.getAnimStatus(GridPane.getRowIndex(btn), GridPane.getColumnIndex(btn))) {
							fadeout(imageView).play();
						}
					} else { // if the user does not use the hint, and there's no longer an available move at
								// that spot
						ImageView imageView = new ImageView(new Image("res/empty.png"));
						imageView.setFitHeight(30);
						imageView.setFitWidth(30);
						btn.setGraphic(imageView);
						btn.setId("unavailable");
					}

				}
			}
			// re-enabling other hint buttons when Steve Job's Hint button is no longer activated
			if (this.random != null) {
				Button btn = (Button) this.random;
				btn.setDisable(false);
				this.random = null;
			}

			if (this.greedy != null) {
				Button btn = (Button) this.greedy;
				btn.setDisable(false);
				this.greedy = null;
			}

			if (this.single != null) {
				Button btn = (Button) this.single;
				btn.setDisable(false);
				this.single = null;
			}
		}

	}

}