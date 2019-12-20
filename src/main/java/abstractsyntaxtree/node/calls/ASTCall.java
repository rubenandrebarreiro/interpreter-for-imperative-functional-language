package main.java.abstractsyntaxtree.node.calls;

/**
 * Interpreter for Imperative/Functional Language
 * 
 * Interpretation and Compilation of Programming Languages
 * 
 * Faculty of Science and Technology of New University of Lisbon
 * (FCT NOVA | FCT/UNL)
 * 
 * Integrated Master of Computer Science and Engineering
 * (BSc. + MSc. Bologna Degree)
 * 
 * Academic Year 2019/2020
 * 
 */

import java.util.List;

import main.java.abstractsyntaxtree.exceptions.ASTDuplicatedIdentifierException;
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.node.operators.nary.functionals.ASTFun;
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.functions.TFun;
import main.java.values.atomics.IValue;
import main.java.values.utils.exceptions.NumberArgumentsErrorException;
import main.java.values.utils.exceptions.TypeErrorException;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing Call of a Function/Procedure.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTCall implements ASTNode {

	// Constants:
	
	/**
	 * The Type Error Message to be showed,
	 * in the case of the type of this A.S.T. Call not be a Function/Procedure (TFun).
	 */
	private static final String TYPE_ERROR_MESSAGE = "The Type for this Call it's not a Function/Procedure!";

	
	// Global Instance Variables:
	
	/**
	 * The A.S.T. Node for the Function/Procedure associated to this A.S.T. Call.
	 */
	private ASTFun astFunction;
	
	/**
	 * The list of A.S.T. Nodes representing the arguments used in
	 * the Function/Procedure associated to this A.S.T. Call.
	 */
	private List<ASTNode> functionArguments;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree (A.S.T.).
	 * 
	 * @param astFunction the A.S.T. Node for the Function/Procedure
	 *        associated to this A.S.T. Call
	 * 
	 * @param functionArguments the list of A.S.T. Nodes representing
	 * 		  the arguments used in the Function/Procedure associated to
	 *        this A.S.T. Call
	 */
	public ASTCall(ASTFun astFunction, List<ASTNode> functionArguments) {
		this.astFunction = astFunction;
		this.functionArguments = functionArguments;
	}
	
	
	
	@Override
	public IValue eval(Environment<IValue> environment)
		   throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {
		
		this.astFunction.eval(environment);
		
		
		Environment<IValue> newEnvironment = new Environment<>();
		
		
		newEnvironment.beginScope();
		
		
		int sizeOfFunctionArgumentsIDs = this.astFunction.getFunctionArgumentsIDs().size();
		
		int sizeOfFunctionArgumentsValues = this.functionArguments.size();
		
		if(sizeOfFunctionArgumentsIDs != sizeOfFunctionArgumentsValues) {
			
			throw new NumberArgumentsErrorException("Illegal Number of Arguments to the Functions: "
												  + "The number of IDs and Values must be the same!!!");
		
		}
		
		int numArguments = this.functionArguments.size(); 
		
		for(int currentArgument = 0; currentArgument < numArguments; currentArgument++) {
			
			String argumentID = this.astFunction.getFunctionArgumentsIDs().get(currentArgument);
			ASTNode argumentValue = this.functionArguments.get(currentArgument);
			
			newEnvironment.addAssoc(argumentID, argumentValue.eval(newEnvironment));
			
		}
		
		IValue functionBodyEvaluationValue = this.astFunction.getFunctionBody().eval(newEnvironment);
		
		newEnvironment.endScope();
		
		
		return functionBodyEvaluationValue;
				
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
			throws ASTInvalidIdentifierException {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * Compiles the List of Code Instructions of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside and
	 * the List of the Code Instructions of the current Node of an
	 * Abstract Syntax Tree (A.S.T.) will be kept, writing J.V.M. instructions,
	 * in order to, perform Identification.
	 * 
	 * @param environment the Environment (Scope/Frame), where the types of
	 *        the current Node of an Abstract Syntax Tree (A.S.T.) will be evaluated,
	 *        in a Static Typechecking, before runtime of the program
	 * 
	 * @throws TypeErrorException a Type Error Exception thrown,
	 * 		   in the case of a Type used for in Typechecking of an A.S.T. Node it's
	 * 		   wrong in the current Environment (Scope/Frame) being evaluated
	 *
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Stack of Environments (Scopes/Frames) 
	 * 
	 */
	@Override
	public IType typecheck(Environment<IType> environment)
			throws TypeErrorException,
	   		       ASTInvalidIdentifierException,
	   		       ASTDuplicatedIdentifierException,
	   		       NumberArgumentsErrorException {
		
		IType astFunctionType = this.astFunction.typecheck(environment);
		
		if(astFunctionType instanceof TFun) {
			
			return null;
			
		}
		else {
		
			throw new TypeErrorException(TYPE_ERROR_MESSAGE);
			
		}
		
	}
	
}