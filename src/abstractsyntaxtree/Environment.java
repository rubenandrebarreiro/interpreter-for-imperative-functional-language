package abstractsyntaxtree;
import java.util.HashMap;
import java.util.Map;

import abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;

/**
 * Class for the Environment.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */

public class Environment {

	private Environment ancestor;
	private Map<String, Integer> map;
	
	// Constructors:
	/**
	 * Constructor #1:
	 * - The Constructor of and environment with no ancestor.
	 */
	public Environment() {
		map = new HashMap<String, Integer>();
		ancestor = null;
	}
	
	/**
	 * Constructor #2:
	 * - The Constructor of and environment with an ancestor.
	 * 
	 * @param ancestor the previous environment on the stack.
	 */
	public Environment(Environment ancestor) {
		map = new HashMap<String, Integer>();
		this.ancestor = ancestor;
	}
	
	// Methods:
	/**
	 * Finds a value with a given ID. If not found on this Environment, check other
	 * ancestors on stack.
	 * @param id the identifier to search for.
	 * @return the value associated with id.
	 * @throws ASTInvalidIdentifierException if id does not match any known value.
	 */
	public int find(String id) throws ASTInvalidIdentifierException {
		Integer value = map.get(id);
		if(value == null && ancestor == null) {
			throw new ASTInvalidIdentifierException("No value with identifier " + id);
		} else if(value == null && ancestor != null) {
			return ancestor.find(id);
		}
		else
			return value;
	}
	
	/**
	 * Associates a given value with a given identifier.
	 * @param id the identifier of the new value.
	 * @param value the value of the new identifier.
	 * @throws ASTInvalidIdentifierException  if id already exists in this Environment.
	 */
	public void assoc(String id, int value) throws ASTInvalidIdentifierException
	{
		if(map.containsKey(id)) {
			//TODO Check if value cannot be overwritten.
			throw new ASTInvalidIdentifierException("ID " + id + " already exists,");
		} else {
			map.put(id, value);
		}
	}
	
	/**
	 * Creates a new Environment with this one as its ancestor.
	 * @return new Environment
	 */
	public Environment beginScope() {
		return new Environment(this);
	};
	
	/**
	 * TODO Check what this actually is supposed to do.
	 * Destroys Environment
	 * @return ?
	 */
	public Environment endScope() {
		if(ancestor != null)
			return ancestor;
		//TODO What happens here??
		else return ancestor;
	};
	
}
