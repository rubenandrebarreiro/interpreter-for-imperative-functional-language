package abstractsyntaxtree.scopes;
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


// TODO -  Fazer Generico com contariner <T>
public class Environment {
	
	// Global Instance Variables:
	/**
	 * The Environment's ancestor on the Stack of Environments (Scopes)
	 */
	private Environment ancestorEnvironment;
	
	/**
	 * The dictionary of Expressions known by the current Environment (Scope),
	 * mapping each Expression's ID to its Value 
	 */
	private Map<String, Integer> dictionaryOfExpressions;
	
	
	// Constructors:
	/**
	 * Constructor #1:
	 * - The Constructor of an Environment (Scope) with no ancestor on the Stack of Environments (Scopes).
	 */
	public Environment() {
		this.dictionaryOfExpressions = new HashMap<String, Integer>();
		this.ancestorEnvironment = null;
	}
	
	/**
	 * Constructor #2:
	 * - The Constructor of an Environment (Scope) with its ancestor on the Stack of Environments (Scopes).
	 * 
	 * @param ancestorEnvironment the previous Environment (Scope), i.e.,
	 *                            the Environment's ancestor on the Stack of Environments (Scopes)
	 */
	public Environment(Environment ancestorEnvironment) {
		this.dictionaryOfExpressions = new HashMap<String, Integer>();
		this.ancestorEnvironment = ancestorEnvironment;
	}
	
	
	// Methods:
	/**
	 * Creates a new Environment (Scope) with this one as its ancestor.
	 * 
	 * @return new Environment (Scope) with this one as its ancestor
	 */
	public Environment beginScope() {
		return new Environment(this);
	}
	
	/**
	 * Ends the current Environment (Scope), returning its ancestor on the Stack of Environments (Scopes).
	 * 
	 * @return the ancestor of the currently ended Environment (Scope)
	 */
	public Environment endScope() {
		
		// This only happens in the initial Environment (Scope):
		// - It will never be called the Environment's ancestor of
		//   the initial Environment (Scope), on the Stack of Environments
		// - Thus, it will never enter in this case
		// - But if it would be the case, should return null
		if(this.ancestorEnvironment == null) {
			return null;
		}
		
		// The normal case:
		// - Here it should be called the Environment's ancestor on the Stack of Environments (Scopes)
		else {
			return this.ancestorEnvironment;
		}
	}
	
	/**
	 * Associates a given Value of an Expression with a given ID.
	 * 
	 * @param expressionID the ID of the new Expression's Value
	 * @param expressionValue the Value of the new Expression, which be identified by the given Expression's ID
	 * 
	 * @throws ASTInvalidIdentifierException if Expression's ID already exists in the current Environment
	 */
	public void addAssoc(String expressionID, int expressionValue) throws ASTInvalidIdentifierException {
		
		// This will never happen, but just in case:
		// - If the case that, in the current Environment (Scope),
		//   already exist the Expression identified by the given ID,
		//   it should be replaced by the more recent Expression's Value
		//   associated to the given Expression's ID
		if(this.dictionaryOfExpressions.containsKey(expressionID)) {
			// Obsolete instruction
			this.dictionaryOfExpressions.put(expressionID, expressionValue);
		}
		
		// In the case that, in the current Environment (Scope), 
		// don't exist any Expression identified by the given ID
		// (even if, it could exist in other Environments on the Stack of Environments)
		else {
			this.dictionaryOfExpressions.put(expressionID, expressionValue);
		}
	}
	
	/**
	 * Finds a value with a given ID, in the current Environment.
	 * If the value associated to the given ID it's not found on this Environment,
	 * check in the other Ancestor's Environments on the Stack of Environments.
	 * 
	 * @param expressionID the ID to search for an associated Value
	 * 
	 * @return the value associated with the given ID
	 * 
	 * @throws ASTInvalidIdentifierException if ID doesn't match any known Value
	 */
	public int find(String expressionID) throws ASTInvalidIdentifierException {
		Integer expressionValue = dictionaryOfExpressions.get(expressionID);
		
		//int[] offeset
		
		// If the Value associated to the given ID, don't exist in the current Environment,
		// And the current Environment don't have any parent, i.e., it's the initial Environment,
		// So, the Value associated to the given ID, don't exist in any Environment on the Stack of Environments
		if(expressionValue == null && this.ancestorEnvironment == null) {
			throw new ASTInvalidIdentifierException("No Value with the following ID: " + expressionID + " !!!");
		}
		
		// If the Value associated to the given ID, don't exist in the current Environment,
		// And the current Environment still have parents, i.e., it's not the initial Environment,
		// So, the Value associated to the given ID, can exist in other Environments on the Stack of Environments
		else if(expressionValue == null && this.ancestorEnvironment != null) {
			return this.ancestorEnvironment.find(expressionID);
		}
				
		// If the Value associated to the given ID,
		// exist in the current Environment, it should be returned
		else {
			return expressionValue;
		}
	}
}