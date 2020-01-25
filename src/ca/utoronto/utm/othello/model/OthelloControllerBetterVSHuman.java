package ca.utoronto.utm.othello.model;

/**
 * This controller uses the Model classes to allow the Human player P2 to play
 * the computer P1. The computer, P1 uses a Better strategy.
 * 
 * @author TheNerds
 *
 */
public class OthelloControllerBetterVSHuman extends OthelloControllerVerbose {

	/**
	 * Constructs a new OthelloController with a new Othello game and to 
	 * appropriately initialize the players of the game.
	 */
	public OthelloControllerBetterVSHuman() {
		super();
		this.player1 = new PlayerBetter(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerHuman(this.othello, OthelloBoard.P2);

	}

	/**
	 * Run main to play a Computer (P1) against the Human P2. The computer uses a
	 * Better strategy
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		OthelloControllerBetterVSHuman oc = new OthelloControllerBetterVSHuman();
		oc.play();
	}
}
