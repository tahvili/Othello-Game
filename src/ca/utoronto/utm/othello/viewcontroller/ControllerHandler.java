package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
/**
 * Handles the  events trigged by user actions and calls appropriate methods in the model
 * 
 * @author TheNerds
 *
 */
public class ControllerHandler implements EventHandler<ActionEvent> {
	private OthelloModel model;

	public ControllerHandler(OthelloModel model) {
		this.model = model;
	}

	public ControllerHandler(OthelloModel model, Spinner<Integer> spinner1, Spinner<Integer> spinner2, String opponent,
			String wantToGoFirst) {
		int min = spinner1.getValue(); //reading the time that the user has entered
		int sec = spinner2.getValue();
		model.setPlayers(opponent, wantToGoFirst);
		model.setTime(min, sec);
		model.setHasStarted(true);
		model.doMove(3, 3); //trigger a dummy move
	}
	/**
	 * A method to direct the user's action to an appropriate method in the model to
	 * complete the appropriate task
	 * 
	 * 
	 */
	public void handle(ActionEvent event) {

		if (event.getSource() instanceof Button) {

			Button btn = (Button) event.getSource();
			if (btn.getText() == "U") {
				if (!model.undoMove()) {  
					Alert alert = new Alert(AlertType.WARNING); //displaying an alert box if no further undo moves are possible
					alert.setTitle("Warning Dialog");
					alert.setHeaderText("Cannot Undo Move!");
					alert.setContentText("Click on OK to get back to the game");
					alert.showAndWait();
				}
			}
			if (model.getHasStarted()) {
				if (event.getSource() instanceof GreedyHint) {
					model.setAutoGreedyHint();
				} else if (event.getSource() instanceof RandomHint) {
					model.setAutoRandomHint();
				} else if (event.getSource() instanceof SingleHint) {
					model.setSingleHint();
				} else if (event.getSource() instanceof BetterHint) {
					model.setBetterHint();
				} else if (btn.getText() == "Human" || btn.getText() == "Greedy" || btn.getText() == "Random"
						|| btn.getText() == "Better") {
					model.initPlayers(btn);
				}
			}
		}

		if (model.getHasStarted() && event.getSource() instanceof ButtonLabel) { //doing the move based on user's selection
			ButtonLabel source = (ButtonLabel) event.getSource();
			Integer colIndex = GridPane.getColumnIndex(source);
			Integer rowIndex = GridPane.getRowIndex(source);
			model.doMove(rowIndex, colIndex);
		}

	}
}