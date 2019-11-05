package abstractsyntaxtree.scopes.compiler;

import java.util.HashMap;
import java.util.Map;

import abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;

public class EnvironmentCompiler {
	
	private Map<String, Integer> offsetLocalizations;
	private EnvironmentCompiler ancestorEnvironment;
	
	public EnvironmentCompiler() {
		this.offsetLocalizations = new HashMap<String, Integer>();
		ancestorEnvironment = null;
	}

	public EnvironmentCompiler(EnvironmentCompiler ancestorEnvironment) {
		this.offsetLocalizations = new HashMap<String, Integer>();
		this.ancestorEnvironment = ancestorEnvironment;
	}
	
	public EnvironmentCompiler beginScope() {
		return new EnvironmentCompiler(this);
	}
	
	public EnvironmentCompiler endScope() {

		if(this.ancestorEnvironment == null) {
			return null;
		}
		
		else {
			return this.ancestorEnvironment;
		}
	}
	
	//TODO Search for variable in frame. If unavailable, search parent frame.
	//Needs to know what number of frame jumps were made.
	public int find(String expressionID) throws ASTInvalidIdentifierException {
		Integer expressionValue = offsetLocalizations.get(expressionID);
				
		if(expressionValue == null && this.ancestorEnvironment == null) {
			throw new ASTInvalidIdentifierException("No Value with the following ID: " + expressionID + " !!!");
		}
		
		else if(expressionValue == null && this.ancestorEnvironment != null) {
			return this.ancestorEnvironment.find(expressionID);
		}

		else {
			return expressionValue;
		}
	}
	
}
