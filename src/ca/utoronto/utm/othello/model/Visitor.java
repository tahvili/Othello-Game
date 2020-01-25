package ca.utoronto.utm.othello.model;

/**
 * An interface to be implemented by all concrete Visitors
 *
 * @author TheNerds
 *
 */
public interface Visitor {

	/**
	 * 
	 * @param othello A concrete Visitable class Othello
	 * @return An Object
	 */
	public Object visit(Othello othello); 
	/**
	 * 
	 * @param othello A concrete Visitable class OthelloBoard
	 * @return An Object
	 */
	public Object visit(OthelloBoard board); 
	
 }

