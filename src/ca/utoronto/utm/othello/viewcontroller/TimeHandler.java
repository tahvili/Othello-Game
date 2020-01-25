package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.OthelloModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 * A class that handles the action needed to be performed by the timers in the game 
 * 
 * @author TheNerds
 *
 */
public class TimeHandler implements EventHandler<ActionEvent>{
	
	OthelloModel m; 
	public TimeHandler(OthelloModel model) {
		this.m = model; 
	}
	@Override
	/**
	 * A method which executes time decrement
	 * 
	 */
	public void handle(ActionEvent action) {
		m.decrement();
	}

}
