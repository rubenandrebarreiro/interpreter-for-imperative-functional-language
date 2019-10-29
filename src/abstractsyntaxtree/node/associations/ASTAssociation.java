package abstractsyntaxtree.node.associations;

import java.util.List;

import abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import abstractsyntaxtree.node.ASTNode;
import abstractsyntaxtree.scopes.Environment;

public class ASTAssociation implements ASTNode {

	private String id;
	private ASTNode value;
	
	public ASTAssociation(String id, ASTNode value) {
		this.id = id;
		this.value = value;
	}
	
	@Override
	public int eval(Environment environment) throws ASTInvalidIdentifierException {
		environment.addAssoc(id, value.eval(environment));
		return value.eval(environment);
	}

	@Override
	public void compile(Environment environment, List<String> codeInstructions) {
		// TODO Auto-generated method stub

	}

}
