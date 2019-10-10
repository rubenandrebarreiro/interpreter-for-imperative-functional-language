package abstractsyntaxtree.node.associations;

import abstractsyntaxtree.Environment;
import abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import abstractsyntaxtree.node.ASTNode;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing the association of its descendants {ID -> Value}.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTLet implements ASTNode {

	/**
	 * The left A.S.T. Node descendant
	 */
	private String leftASTNodeDescedant;
	
	/**
	 * The right A.S.T. Node descendant
	 */
	private ASTNode rightASTNodeDescedant;
	
	@Override
	public int eval(Environment environment) {
		environment.beginScope();
		
		try {
			environment.addAssoc(leftASTNodeDescedant, rightASTNodeDescedant.eval(environment));
		}
		catch (ASTInvalidIdentifierException astInvalidIdentifierException) {
			astInvalidIdentifierException.printStackTrace();
		}
		
		environment.endScope();
		
		return 0;
	}
}