package ca.utoronto.utm.othello.model;

/**
 * This controller uses the Model classes to allow the Human player P1 to play
 * the computer P2. The computer, P2 uses a Better strategy.
 * 
 * @author TheNerds
 *
 */
public class OthelloControllerHumanVSBetter extends OthelloControllerVerbose {

	/**
	 * Constructs a new OthelloController with a new Othello game and to 
	 * appropriately initialize the players of the game.
	 */
	public OthelloControllerHumanVSBetter() {
		super();
		this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerBetter(this.othello, OthelloBoard.P2);
	}

	/**
	 * Run main to play a Human (P1) against the computer P2. The computer uses a
	 * Better strategy
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		OthelloControllerHumanVSBetter oc = new OthelloControllerHumanVSBetter();
		oc.play();
	}
}
