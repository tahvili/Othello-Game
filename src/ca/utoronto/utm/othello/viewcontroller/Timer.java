package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.OthelloModel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * A class representing a Timer in Othello Game
 * 
 * @author TheNerds
 * 
 */
public class Timer {
	private Timeline timer;
	private OthelloModel model;
	private int min;
	private int sec;

	/**
	 * A constructor to create a new Timer
	 * 
	 * 
	 * @param model Instance of OthelloModel
	 */
	public Timer(OthelloModel model) {
		this.model = model;
		this.timer = new Timeline(new KeyFrame(Duration.millis(1000), new TimeHandler(model)));
		timer.setCycleCount(Animation.INDEFINITE);
		this.min = 5;
		this.sec = 0;
	}

	/**
	 * Decrementing the time
	 * 
	 */
	public void decrement() {
		if (min > -1 && sec != 0) {
			this.sec--;
		}
		if (sec == 0 && min != 0) {
			min--;
			sec = 59;
		}
	}

	/**
	 * To check if the time is up
	 * 
	 * @return if the time is up or no
	 */
	public boolean timeUp() {
		if (min == 0 && sec == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Method to get access to the Timer
	 * 
	 * @return The Timer
	 */

	public Timeline getTimer() {
		return this.timer;
	}

	/**
	 * A method to set the time according to user's input
	 * 
	 * @param min The minutes to be set
	 * @param sec The seconds to be set
	 */
	public void setTime(int min, int sec) {
		this.min = min;
		this.sec = sec;
	}

	/**
	 * A method to start the timer
	 * 
	 * @param timer The timer to be started
	 */
	public void startTime(Timeline timer) {
		timer.play();
	}

	/**
	 * Method to get the String representation of the time
	 * 
	 * @return String representation to the current time
	 */
	public String getTime() {

		String s = this.min + ":" + this.sec;
		if (sec < 10) {
			s = min + ":" + "0" + this.sec;
		} else if (sec == 60) {
			s = min + 1 + ":" + "00";
		}
		return s;
	}
}
