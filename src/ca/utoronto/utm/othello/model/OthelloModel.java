package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.othello.viewcontroller.ButtonLabel;
import ca.utoronto.utm.othello.viewcontroller.Timer;
import ca.utoronto.utm.othello.viewcontroller.UndoOperator;
import ca.utoronto.utm.util.Observable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class OthelloModel extends Observable {

	public OthelloController othello;
	public GridPane grid;
	private String p1 = "Human"; //player 1 (human by default)
	private String p2 = "Human"; //player 2 (human by default)
	public String hint = ""; // stores currently activated hint option
	public Timer timer;
	public Timer timer2;
	private boolean hasStarted = false; // variable to keep track if the game has started
	private int[][] mod = new int[Othello.DIMENSION][Othello.DIMENSION]; // to keep track of changes on the board to help in displaying animation
	private UndoOperator operator; // helps in performing the undo operation
	private Othello boardcopy;
	// Variables used to check if respective hints are activated or not
	public boolean RandomAlreadyMade = true;
	public boolean GreedyAlreadyMade = true;
	public boolean SingleAlreadyMade = true;
	public boolean BetterAlreadyMade = true;
	public boolean RestartButtonPressed = false;
	
	public boolean flag = false;
	private String opponent; // storing the opponent selected by the user

	// CONSTRUCTOR
	public OthelloModel() {
		this.timer = new Timer(this);
		this.timer2 = new Timer(this);
		operator = new UndoOperator();
		this.othello = setGame(this.othello);
		this.boardcopy = this.othello.othello.copy(); // storing the initial state of the board
	}

	// ANIMATION STARTS HERE

	public void updateAnimation() {

		this.RandomAlreadyMade = true;  //reactivate RandomHint Button
		this.GreedyAlreadyMade = true; //reactivate GreedyHint Button

		if (this.hint == "Single" || this.hint == "Better") {
			this.hint = "";
		}
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				for (Node source : grid.getChildren()) {
					if (source instanceof ButtonLabel && GridPane.getColumnIndex(source) == col
							&& GridPane.getRowIndex(source) == row) {
						if (this.boardcopy.getToken(row, col) != this.othello.othello.getToken(row, col))
							mod[row][col] = 3; // setting it to some arbitrary number (say 3) just so that we can
												// activate animation for the button at (row,col)
					}
				}
			}
		}

		if (this.othello.othello.getWhosTurn() == 'X') {
			this.timer2.getTimer().stop();
			this.timer.getTimer().play();
		} else if (this.othello.othello.getWhosTurn() == 'O') {
			this.timer.getTimer().stop();
			this.timer2.getTimer().play();
		}
		flag = true;
		this.notifyObservers();
		flag = false;

	}

	/**
	 * Checks to see if we need to perform animation for the button
	 * 
	 * @param row The row coordinate
	 * @param col The col coordinate
	 * @return whether we need to activate animation for the button at (row, col)
	 */
	public boolean getAnimStatus(int row, int col) {
		if (mod[row][col] == 3) {
			return true;
		}
		return false;
	}

	// TIME SECTION BEGINS
	/**
	 * A method to set the time according to user's input
	 * 
	 * @param min The minutes to be set
	 * @param sec The seconds to be set
	 */

	public void setTime(int min, int sec) {
		timer.setTime(min, sec);
		timer2.setTime(min, sec);
		timer.startTime(timer.getTimer());
		this.notifyObservers();
	}

	/**
	 * A method to return the player who has run out of time
	 * 
	 * @return the player who's time is up
	 */
	public char getLostTime() {
		if (timer.timeUp()) {
			setHasStarted(false);
			return 'X';
		} else if (timer2.timeUp()) {
			setHasStarted(false);
			return 'O';
		} else {
			return ' ';
		}
	}

	/**
	 * A method to decrement the timer of the player who's turn is currently going
	 * on
	 * 
	 */
	public void decrement() {
		if (this.othello.othello.getWhosTurn() == 'X' && !this.timer.timeUp()) {
			this.timer.decrement();
			this.notifyObservers();
		} else if (this.othello.othello.getWhosTurn() == 'O' && !this.timer2.timeUp()) {
			this.timer2.decrement();
			this.notifyObservers();
		}else {
			this.notifyObservers();
		}
	}

	// PLAYER SETTER/GETTER BEGINS
	
	/**
	 * A method to set the players of game according to the user's preference
	 * 
	 * @param opponent      The opponent chosen by the user
	 * @param wantToGoFirst The turn the user wants to play
	 */
	public void setPlayers(String opponent, String wantToGoFirst) {
		if (wantToGoFirst == "Yes") {
			this.p1 = "Human";
			this.p2 = opponent;
			this.opponent = "p2";
		} else {
			this.p2 = "Human";
			this.p1 = opponent;
			this.opponent = "p1";
		}
		this.othello = setGame(this.othello);
		doMove(3, 3); //dummy move to trigger a change
	}

	/**
	 * A method to initialize the players of the game based on the user's choice
	 * 
	 * @param btn The button selected by the user
	 */
	public void initPlayers(Button btn) {

		if (opponent == "p1") {
			this.p1 = btn.getText();
			if (p1 == "Human") {
				othello.player1 = new PlayerHuman(this.othello.othello, OthelloBoard.P1);
			} else if (p1 == "Greedy") {
				othello.player1 = new PlayerGreedy(this.othello.othello, OthelloBoard.P1);
				doMove(3, 3);
			} else if (p1 == "Random") {
				othello.player1 = new PlayerRandom(this.othello.othello, OthelloBoard.P1);
				doMove(3, 3);
			} else if (p1 == "Better") {
				othello.player1 = new PlayerBetter(this.othello.othello, OthelloBoard.P1);
				doMove(3, 3);
			}
		}
		if (opponent == "p2") {
			this.p2 = btn.getText();

			if (p2 == "Human") {
				othello.player2 = new PlayerHuman(this.othello.othello, OthelloBoard.P2);
			} else if (p2 == "Greedy") {
				othello.player2 = new PlayerGreedy(this.othello.othello, OthelloBoard.P2);
				doMove(3, 3);
			} else if (p2 == "Random") {
				othello.player2 = new PlayerRandom(this.othello.othello, OthelloBoard.P2);
				doMove(3, 3);
			} else if (p2 == "Better") {
				othello.player2 = new PlayerBetter(this.othello.othello, OthelloBoard.P2);
				doMove(3, 3);
			}

		}

		this.notifyObservers();
	}

	/**
	 * 
	 * @return Player 1 of the current Othello Game
	 */
	public String getPlayer1() {
		return this.p1;
	}

	/**
	 * 
	 * @return Player 2 of the current Othello Game
	 */
	public String getPlayer2() {
		return this.p2;
	}

	//GAME SECTION BEGINGS
	
	/**
	 * A method to run the appropriate controller for an Othello Game
	 * 
	 * @param othello Instance of OthelloController
	 * @return A Controller for the Othello Game
	 */
	public OthelloController setGame(OthelloController othello) {

		if (p1.equals("Human") && p2.equals("Human")) {
			othello = new OthelloControllerHumanVSHuman();
		} else if (p1.equals("Human") && p2.equals("Greedy")) {
			othello = new OthelloControllerHumanVSGreedy();
		} else if (p1.equals("Human") && p2.equals("Random")) {
			othello = new OthelloControllerHumanVSRandom();
		} else if (p1.equals("Human") && p2.equals("Better")) {
			othello = new OthelloControllerHumanVSBetter();
		} else if (p1.equals("Greedy") && p2.equals("Human")) {
			othello = new OthelloControllerGreedyVSHuman();
		} else if (p1.equals("Random") && p2.equals("Human")) {
			othello = new OthelloControllerRandomVSHuman();
		} else if (p1.equals("Better") && p2.equals("Human")) {
			othello = new OthelloControllerBetterVSHuman();
		}
		return othello;
	}
	
	/**
	 * A method to check if the game has started
	 * 
	 * @return if the game has started
	 */
	public boolean getHasStarted() {
		this.RestartButtonPressed = false;
		return this.hasStarted;
	}

	/**
	 * To set the status(if the game has begun or no)
	 * 
	 * @param check the value to be set as the game's status
	 */
	public void setHasStarted(boolean check) {
		this.hasStarted = check;
	}

	/**
	 * A method to set the model's grid
	 * 
	 * @param grid the grid to be set
	 */
	public void setGrid(GridPane grid) {
		this.grid = grid;
	}

	// MOVEMENT SECTION BEGINS
	/**
	 * Makes a move for current player of Othello game
	 * 
	 * @param row
	 * @param col
	 */
	public void doMove(int row, int col) {
		if(getLostTime()==' ') {
			this.boardcopy = this.othello.othello.copy();
			if (this.othello.othello.getWhosTurn() == 'X' && this.p1 == "Human"
					|| this.othello.othello.getWhosTurn() == 'O' && this.p2 == "Human") {
				if (this.othello.othello.move(row, col)) {
					operator.acceptMove(new Move(row, col, this));
					if (this.hint == "Manual" || this.hint == "BILL GATE'S HINT") {
						this.hint = "";
					}
					updateAnimation();
				}
			}
			if (this.othello.othello.getWhosTurn() == 'X'
					&& (this.p1 == "Greedy" || this.p1 == "Random" || this.p1 == "Better")
					|| this.othello.othello.getWhosTurn() == 'O'
							&& (this.p2 == "Greedy" || this.p2 == "Random" || this.p2 == "Better")) {
				robot();
			}
			mod = new int[Othello.DIMENSION][Othello.DIMENSION];		
		}
	}

	/**
	 * Makes the move for AI player
	 * 
	 */
	public void robot() {
		char player = this.othello.othello.getWhosTurn();
		if (player == 'X') {
			Move move = this.othello.player1.getMove();
			if (this.othello.othello.move(move.getRow(), move.getCol())) {
				operator.acceptMove(new Move(move.getRow(), move.getCol(), this));
				updateAnimation();
			}
		} else if (player == 'O') {
			Move move = this.othello.player2.getMove();
			if (this.othello.othello.move(move.getRow(), move.getCol())) {
				operator.acceptMove(new Move(move.getRow(), move.getCol(), this));
				updateAnimation();
			}
		}

	}

	// THE HINT SECTION BEGINS

	/**
	 * A method to store all the available moves for a player.
	 * 
	 * @return An ArrayList consisting of all the available moves for a give player.
	 */
	private ArrayList<Move> AvailableMoves() {
		AvailableMoves moves = new AvailableMoves(this.othello.othello, this.othello.othello.getWhosTurn());
		return (moves.getAllMove());
	}

	/**
	 * Checking if a player has a move at (row, col)
	 * 
	 * @param row
	 * @param col
	 * @return If the move exists or no
	 */
	public boolean AvailableExists(int row, int col) {

		for (Move available : AvailableMoves()) {
			if (available.getRow() == row && available.getCol() == col) {
				return true;
			}
		}
		return false;
	}

	/**
	 * A method to return a move which maximizes the number of tokens owned by this
	 * player.
	 * 
	 * @return return a move which maximizes the number of tokens owned by this
	 *         player
	 */
	public Move bestMove() {
		PlayerGreedy greedy = new PlayerGreedy(this.othello.othello, this.othello.othello.getWhosTurn());
		if (!this.othello.othello.isGameOver()) {
			return greedy.getMove();
		}
		return null;
	}

	/**
	 * A method to return a move based on better strategy
	 *
	 * @return A move according to better strategy
	 */
	public Move betterMove() {
		PlayerBetter better = new PlayerBetter(this.othello.othello, this.othello.othello.getWhosTurn());
		if (!this.othello.othello.isGameOver()) {
			return better.getMove();
		}
		return null;
	}

	/**
	 * A method to return a randomly selected move from the list of available moves
	 * 
	 * @return A move according to the above mentioned description
	 */
	public Move someMove() {
		PlayerRandom random = new PlayerRandom(this.othello.othello, this.othello.othello.getWhosTurn());
		if (!this.othello.othello.isGameOver()) {
			return random.getMove();
		}
		return null;
	}

	// THE HINT SETTER/GETTER SECTION BEGINS

	/**
	 * 
	 * @return Which hint button has been chosen by the suer
	 */
	public String getHint() {
		return this.hint;
	}

	/**
	 * A method to set the activation status of SingleHint
	 * 
	 */
	public void setSingleHint() {
		this.SingleAlreadyMade = true;

		if (this.hint == "Single") { //if the user clicks on SingleHint button while it's activated, it deactivates it.
			this.hint = "";
		} else { //activate it
			this.hint = "Single";
		}
		this.notifyObservers();
	}

	/**
	 * A method to set the activation status of BetterHint (Steve Job's Hint)
	 * 
	 */
	public void setBetterHint() {
		this.BetterAlreadyMade = true;

		if (this.hint == "Better") {
			this.hint = "";
		} else {
			this.hint = "Better";
		}
		this.notifyObservers();
	}

	/**
	 * A method to set the activation status of GreedyHint
	 * 
	 */
	public void setAutoGreedyHint() {
		if (this.hint == "Greedy") {
			this.hint = "";
		} else {
			this.hint = "Greedy";
		}
		this.notifyObservers();
	}

	/**
	 * A method to set the activation status of RandomHint
	 * 
	 */
	public void setAutoRandomHint() {
		if (this.hint == "Random") {
			this.hint = "";
		} else {
			this.hint = "Random";
		}
		this.notifyObservers();
	}

	// RESTART SECTION BEGINS
	/**
	 * A method to restart the Othello Game
	 * 
	 */
	public void restart() {
		this.p1 = "Human";
		this.p2 = "Human";
		this.othello = setGame(this.othello); //resetting the Othello Game
		updateAnimation();
		this.timer.getTimer().stop();
		this.timer2.getTimer().stop();
		this.operator.getStack().clear(); // need to clear the undo operator's stack when we restart.
		setHasStarted(true);
		this.RestartButtonPressed = true;
		this.notifyObservers();
	}

	// UNDO SECTION BEGINS

	/**
	 * A method to undo previously made move
	 * 
	 * @return whether the undo was successful or no
	 */
	public boolean undoMove() {
		if (this.operator.getStack().size() > 1 && !this.othello.othello.isGameOver()  && getLostTime()==' ') {
			this.othello = setGame(this.othello);
			updateAnimation();
			this.operator.doUndo();
			this.notifyObservers();
			return true;
		}
		return false;
	}

	/**
	 * A method to do a move at (row,col) if its possible
	 * 
	 * @param row
	 * @param col
	 */
	public void directMove(int row, int col) {
		this.boardcopy = this.othello.othello.copy();
		if (this.othello.othello.move(row, col)) {
			updateAnimation();
		}
		mod = new int[Othello.DIMENSION][Othello.DIMENSION];
	}
}
