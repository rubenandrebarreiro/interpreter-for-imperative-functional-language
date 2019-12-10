package main.java.abstractsyntaxtree.node.operators.binary.arithmetics;

import main.java.abstractsyntaxtree.exceptions.ASTDuplicatedIdentifierException;
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.mathematics.TNegativeInt;
import main.java.types.mathematics.TPositiveInt;
import main.java.values.atomics.IValue;
import main.java.values.exceptions.NumberArgumentsErrorException;
import main.java.values.exceptions.TypeErrorException;
import main.java.values.mathematics.VPositiveInt;
import main.java.values.mathematics.VNegativeInt;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing the Division of its descendants.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTDiv implements ASTNode {

	// Global Variables:
	
	/**
	 * The left A.S.T. Node descendant
	 */
	private ASTNode leftASTNodeDescendant;
	
	/**
	 * The right A.S.T. Node descendant
	 */
	private ASTNode rightASTNodeDescendant;
	
	/**
	 * The TypeCheck Error Message for the A.S.T. Node for Division
	 */
	private static final String TYPE_ERROR_MESSAGE = "Illegal arguments to / (division) operator!!!";
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree (A.S.T.).
	 * 
	 * @param leftASTNodeDescedant the left side Descendant of the A.S.T. Node
	 * 
	 * @param rightASTNodeDescedant the left side Descendant of the A.S.T. Node
	 */
	public ASTDiv(ASTNode leftASTNodeDescedant, ASTNode rightASTNodeDescedant) {
		this.leftASTNodeDescendant = leftASTNodeDescedant;
		this.rightASTNodeDescendant = rightASTNodeDescedant;
	}
	
	
	// Methods:
	
	/**
	 * Evaluates the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside,
	 * performing its division.
	 * 
	 * @param environment the Environment (Scope/Frame), where the current A.S.T. Node it's inside
	 * 
	 * @return the evaluation of the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 *  	   given the Environment (Scope/Frame), where the current A.S.T. Node it's inside,
	 *  	   performing its division
	 *  
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Heap Stack of Environments (Scopes/Frames)
	  * 
	 * @throws TypeErrorException a Type Error Exception thrown,
	 * 		   in the case of the Type of a Value it's completely unknown to
	 * 		   the recognised and acceptable Types for Values
	 * 
	 * @throws NumberArgumentsErrorException 
	 */
	@Override
	public IValue eval(Environment<IValue> environment)
		   throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {
		
		IValue leftASTNodeDescendantValue = leftASTNodeDescendant.eval(environment);
		IValue rightASTNodeDescedantValue = rightASTNodeDescendant.eval(environment);
		
		if(leftASTNodeDescendantValue instanceof VPositiveInt && rightASTNodeDescedantValue instanceof VPositiveInt) {

			// Returns the Division of the A.S.T. Nodes Positive Descendants
			int divResult = ((VPositiveInt) leftASTNodeDescendantValue).getValue() 
				          / ((VPositiveInt) rightASTNodeDescedantValue).getValue();
			
			return new VPositiveInt(divResult);
		}
		
		if(leftASTNodeDescendantValue instanceof VNegativeInt && rightASTNodeDescedantValue instanceof VPositiveInt) {

			// Returns the Division of the A.S.T. Nodes Positive/Negative Descendants
			int divResult = ((VNegativeInt) leftASTNodeDescendantValue).getValue() 
					      / ((VPositiveInt) rightASTNodeDescedantValue).getValue();
			
			return new VNegativeInt(Math.abs(divResult));
		}
		
		if(leftASTNodeDescendantValue instanceof VPositiveInt && rightASTNodeDescedantValue instanceof VNegativeInt) {

			// Returns the Division of the A.S.T. Nodes Positive/Negative Descendants
			int divResult = ((VPositiveInt) leftASTNodeDescendantValue).getValue() 
					      / ((VNegativeInt) rightASTNodeDescedantValue).getValue();
			
			return new VNegativeInt(Math.abs(divResult));
		}
		
		if(leftASTNodeDescendantValue instanceof VNegativeInt && rightASTNodeDescedantValue instanceof VNegativeInt) {

			// Returns the Division of the A.S.T. Nodes Negative Descendants
			int divResult = ((VNegativeInt) leftASTNodeDescendantValue).getValue() 
					      / ((VNegativeInt) rightASTNodeDescedantValue).getValue();
			
			return new VPositiveInt(divResult);
		}
		
		throw new TypeErrorException(TYPE_ERROR_MESSAGE);
	}
	
	/**
	 * Compiles the List of Code Instructions of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside and
	 * the List of the Code Instructions of the current Node of an
	 * Abstract Syntax Tree (A.S.T.) will be kept, writing J.V.M. instructions,
	 * in order to, perform Division.
	 * 
	 * @param environment the Environment (Scope/Frame), where the current Code Instructions of
	 *        the current Node of an Abstract Syntax Tree (A.S.T.) will be kept
	 * 
	 * @param codeInstructions the List of the Code Instructions to be compiled
	 * 
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Heap Stack of Environments (Scopes/Frames) 
	 */
	@Override
	public void compile(EnvironmentCompiler environmentCompiler,
					    CodeBlockInstructionsSet codeBlockInstructionsSet)
					    				throws ASTInvalidIdentifierException {
		
		// To Perform the Division of the 2 A.S.T. Nodes,
		// it's necessary to evaluate the both left and right descendants
		// and push their evaluation to the Execution Stack
		this.leftASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
		this.rightASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
		
		// Push the Code Instruction of Division (idiv) to the Execution Stack,
		// in order to perform the Division of the 2 A.S.T. Nodes
		String instructionDivision = String.format("idiv");
		codeBlockInstructionsSet.addCodeInstruction(instructionDivision);
	}
	
	@Override
	public IType typecheck(Environment<IType> environment)
			throws TypeErrorException,
		   		   ASTInvalidIdentifierException,
		   		   ASTDuplicatedIdentifierException,
		   		   NumberArgumentsErrorException {
		
		IType leftASTNodeDescedantType = this.leftASTNodeDescendant.typecheck(environment);
		IType rightASTNodeDescedantType = this.rightASTNodeDescendant.typecheck(environment);
		
		if(leftASTNodeDescedantType instanceof TPositiveInt && rightASTNodeDescedantType instanceof TPositiveInt) {
			return new TPositiveInt();
		}
		else if(leftASTNodeDescedantType instanceof TNegativeInt && rightASTNodeDescedantType instanceof TNegativeInt) {
			return new TNegativeInt();
		}
		else if(leftASTNodeDescedantType instanceof TPositiveInt && rightASTNodeDescedantType instanceof TNegativeInt) {
			return new TPositiveInt(); //TODO
		}
		else if(leftASTNodeDescedantType instanceof TNegativeInt && rightASTNodeDescedantType instanceof TPositiveInt) {
			return new TPositiveInt(); //TODO
		}
		else {
			throw new TypeErrorException(TYPE_ERROR_MESSAGE);
		}
	}
}