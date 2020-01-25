package ca.utoronto.utm.othello.model;

/**
 * An interface to be implemented by all concrete Visitable
 * 
 * @author TheNerds
 *
 */
public interface Visitable {

	/**
	 * To accept a concrete Visitor
	 * 
	 * @param visit a visitor
	 * @return an Object
	 */
	public Object accept(Visitor visit);

}
