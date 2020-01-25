package ca.utoronto.utm.othello.viewcontroller;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.OthelloModel;
import ca.utoronto.utm.util.*;

/**
 * A class representing the GreedyHint Button
 * 
 * @author TheNerds
 *
 */
public class GreedyHint extends Button implements Observer {

	private Node greedy; // source of the GreedyHint Button
	private Node single; // source of the SingleHint Button
	private Node steve; // source of the Steve Job's Hint Button

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
	 * A method that updates the View based on what the GreedyHint is
	 * 
	 */
	public void update(Observable o) {

		OthelloModel model = (OthelloModel) o;

		if (model.getHint() == "Greedy" && model.GreedyAlreadyMade) {

			ImageView available = new ImageView(new Image("res/available.png"));
			available.setFitHeight(30);
			available.setFitWidth(30);

			ImageView hint = new ImageView(new Image("res/hint.png"));
			hint.setFitHeight(30);
			hint.setFitWidth(30);

			Move hintMove = model.bestMove();

			for (Node source : model.grid.getChildren()) {

				if (source instanceof ButtonLabel) {
					ButtonLabel btn = (ButtonLabel) source;

					if (btn.getId() == "hint") {
						btn.setGraphic(available);
						btn.setId("available");
					}

					if (!model.othello.othello.isGameOver() || model.getLostTime()==' ') {
						if (GridPane.getColumnIndex(source) == hintMove.getCol()
								&& GridPane.getRowIndex(source) == hintMove.getRow()) { // displaying the hint on the
																						// GUI to the user
							btn.setGraphic(hint);
							btn.setId("hint");
							this.greedy = source;
							model.GreedyAlreadyMade = false;
							model.RandomAlreadyMade = true;
						}
					}
				}

				if (source instanceof Button) { // disabling other hint buttons when GreedyHint button has been activated
					Button btn = (Button) source;
					if (btn.getText() == "GET HINT") {
						btn.setDisable(true);
						this.single = source;
					}
					if (btn.getText() == "STEVE JOB'S HINT  ") {
						btn.setDisable(true);
						this.steve = source;
					}
				}

			}
			// changing the color of the GreedyHint button when selected
			this.setStyle(
					"-fx-background-color: #9000b9; -fx-border-color: #9000b9; -fx-border-width: 0px; -fx-font-weight:bold; -fx-font-size:11px;");

		}

		if (model.othello.othello.isGameOver() || model.getHint() == "" || model.getHint() == "Random") {
			// changing the color of the GreedyHint button back to original
			this.setStyle(
					"-fx-background-color: #0039e6; -fx-border-color: #0039e6; -fx-border-width: 0px; -fx-font-weight:bold; -fx-font-size:11px;");
		}

		if (model.othello.othello.isGameOver() || model.getLostTime()!=' '|| model.getHint() == "") {
			if (this.greedy != null) { // changing the highlighted hint color back to normal available move color
				model.GreedyAlreadyMade = true;
				ButtonLabel btn = (ButtonLabel) this.greedy;
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
			// re-enabling other hint buttons when GreedyHint button is no longer activated
			if (this.single != null) {
				Button btn = (Button) this.single;
				btn.setDisable(false);
			}
			if (this.steve != null) {
				Button btn = (Button) this.steve;
				btn.setDisable(false);
			}
			if(model.othello.othello.isGameOver() || model.getLostTime()!=' ') {
				model.hint="";
				this.setStyle(
						"-fx-background-color: #0039e6; -fx-border-color: #0039e6; -fx-border-width: 0px; -fx-font-weight:bold; -fx-font-size:11px;");
				this.setDisable(true);
				if (this.single != null) {
					Button btn = (Button) this.single;
					btn.setDisable(true);
				}
				if (this.steve != null) {
					Button btn = (Button) this.steve;
					btn.setDisable(true);
				}
				
			}
			else if (model.RestartButtonPressed){
				this.setDisable(false);
				if (this.single != null) {
					Button btn = (Button) this.single;
					btn.setDisable(false);
				}
				if (this.steve != null) {
					Button btn = (Button) this.steve;
					btn.setDisable(false);
				}
			}
			this.steve = null;
			this.single = null;
		}
	}

}
