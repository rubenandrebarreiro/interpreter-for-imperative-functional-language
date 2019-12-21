package main.java.abstractsyntaxtree.node.operators.unary.references;

import main.java.abstractsyntaxtree.exceptions.ASTDuplicatedIdentifierException;
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.references.TRef;
import main.java.values.atomics.IValue;
import main.java.values.utils.exceptions.NumberArgumentsErrorException;
import main.java.values.utils.exceptions.TypeErrorException;
import main.java.values.utils.exceptions.WrongArgumentTypeErrorException;

public class ASTDeReference implements ASTNode {
	
	private static final String TYPE_ERROR_MESSAGE = 
			"Illegal arguments to Dereference operator:\n"
		  + "- The value used in the respectively Reference it's not of Reference Type!!!";
	
	private ASTNode referenceValueToBeDeReferenced;
	
	private TRef referenceType; 
	
	
	public ASTDeReference(ASTNode referenceValueToBeDeReferenced) {
		
		this.referenceValueToBeDeReferenced = 
				referenceValueToBeDeReferenced;
		
	}


	@Override
	public IValue eval(Environment<IValue> environment)
			throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {
		
		return this.referenceValueToBeDeReferenced.eval(environment);
	}


	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
		   throws ASTInvalidIdentifierException {
		
		this.referenceValueToBeDeReferenced.compile(environmentCompiler, codeBlockInstructionsSet);
		
		
		String stackRefName = this.referenceType.getHeapStackFrameReferenceName();
		String stackFrameName = this.referenceType.getHeapStackFrameName();
		
		codeBlockInstructionsSet.addCodeInstruction( String.format("checkcast %s", stackRefName) );
		codeBlockInstructionsSet.addCodeInstruction( String.format("getfield %s/v %s", stackRefName, stackFrameName) );
		
	}

	/**
	 * Performs the Typechecking for the Type associated to this A.S.T. Node Identifier,
	 * performing the Typecheking on it and in its descendants A.S.T. Nodes,
	 * verifying the Type of the Values of all the A.S.T. Nodes.
	 * 
	 * @param environment the Environment (Scope/Frame), where the types of
	 *        the current Node of an Abstract Syntax Tree (A.S.T.) will be evaluated,
	 *        in a Static Typechecking, before runtime of the program
	 * 
	 * @throws TypeErrorException a Type Error Exception thrown,
	 * 		   in the case of a Type used for in Typechecking of an A.S.T. Node it's
	 * 		   wrong in the current Environment of Types (Scope/Frame) being evaluated
	 *
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Stack of Environments (Scopes/Frames) 
	 * 
	 * @throws NumberArgumentsErrorException a Number of Arguments Error Exception thrown,
	 *         in the case of the Number of Arguments used in the Typechecking,
	 *         wrong in the current Environment of Types (Scope/Frame) being evaluated
	 *         
	 * @throws ASTDuplicatedIdentifierException a Duplicated Identifier Exception thrown,
	 * 		   in the case of more than one certain Identifier it's found,
	 *         in the current Environment of Types (Scope/Frame) being evaluated
	 * 
	 * @throws WrongArgumentTypeErrorException a Wrong Argument Type Error Exception thrown,
	 * 		   in the case of, at least, one argument of a Closure have a wrong Typechecked Type
	 *
	 * @return the Type for the A.S.T. Node, after the Typechecking be performed
	 * 
	 */
	@Override
	public IType typecheck(Environment<IType> environment)
		   throws TypeErrorException,
	   		      ASTInvalidIdentifierException,
	   		      ASTDuplicatedIdentifierException,
	   		      NumberArgumentsErrorException,
	   		      WrongArgumentTypeErrorException {
	
		this.referenceType = (TRef) this.referenceValueToBeDeReferenced.typecheck(environment);
	
		if(this.referenceType instanceof TRef) {
			
			return this.referenceType.getReferenceType();
			
		}
		else {
			
			throw new TypeErrorException(TYPE_ERROR_MESSAGE);
		
		}
	}
	
}