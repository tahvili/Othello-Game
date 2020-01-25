package ca.utoronto.utm.othello.viewcontroller;

import java.util.ArrayList;

/**
 * A class the performs the Undo Operation
 * 
 * @author TheNerds
 *
 */
public class UndoOperator {
	private ArrayList<Command> moveStack;

	public UndoOperator() {
		moveStack = new ArrayList<Command>();
	}

	/**
	 * Add the currently made mode to the stack
	 * 
	 * @param command the move currently made in the game
	 */
	public void acceptMove(Command command) {
		this.moveStack.add(command);
	}

	/**
	 * Performing the undo operation
	 * 
	 */
	public void doUndo() {
		if (this.moveStack.size() > 1) {
			// removing the two latest moves from the stack
			this.moveStack.remove(moveStack.size() - 1);
			this.moveStack.remove(moveStack.size() - 1);
		}
		for (Command command : this.moveStack) { // performing each move in the stack from the beginning
			command.execute();
		}

	}

	/**
	 * 
	 * @return Return the stack of moves made in the game
	 */
	public ArrayList<Command> getStack() {
		return this.moveStack;
	}
}
