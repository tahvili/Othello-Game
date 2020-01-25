package ca.utoronto.utm.othello.viewcontroller;

/**
 * An interface declaring the operations that a ConcreteCommand needs to implement 
 * 
 * @author TheNerds 
 *
 */
public interface Command {
	
	/**
	 * Operation to be executed 
	 * 
	 */
	void execute();
}
