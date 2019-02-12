package assignment1;

/**
 * * Pedram Ahadinejad 211438736 Section M 2011
 * 
 * This exception is thrown whenever a method is invoked on a non-simple polygon
 * when a simple polygon is expected.
 * 
 * @author Andy Mirzaian
 */
public class NonSimplePolygonException extends Exception {

	/**
	 * compiler generated serialVersionUID
	 */
	private static final long serialVersionUID = -6499398129928918406L;

	/** no-message constructor */
	public NonSimplePolygonException() {
	}

	/** detailed message constructor */
	public NonSimplePolygonException(String msg) {
		super(msg);
	}
}