package main.java.abstractsyntaxtree.node.operators.nary.functionals;

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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.java.abstractsyntaxtree.exceptions.ASTDuplicatedIdentifierException;
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.codeblocks.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.values.atomics.IValue;
import main.java.values.closures.VClosure;
import main.java.values.utils.exceptions.NumberArgumentsErrorException;
import main.java.values.utils.exceptions.TypeErrorException;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing the behaviour of a Function/Procedure.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTFun implements ASTNode {
	
	private List<String> functionArgumentsIDs;
	
	private ASTNode functionBody;
	
	
	public ASTFun(List<String> functionArgumentsIDs, ASTNode functionBody) {
		
		this.functionArgumentsIDs = functionArgumentsIDs;
		this.functionBody = functionBody;
	
	}
	
	
	// Methods/Functions:
	
	/**
	 * Returns the list of Identifiers of the Arguments used in the Function/Procedure.
	 * 
	 * @return the list of Identifiers of the Arguments used in the Function/Procedure
	 */
	public List<String> getFunctionArgumentsIDs() {
		return this.functionArgumentsIDs;
	}
	
	/**
	 * Returns the A.S.T. Node representing the Body (Set of Instructions) of a Function/Procedure.
	 * 
	 * @return the A.S.T. Node representing the Body (Set of Instructions) of a Function/Procedure
	 */
	public ASTNode getFunctionBody() {
		return this.functionBody;
	}
	
	/**
	 * Evaluates the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope), where the current A.S.T. Node it's inside.
	 * 
	 * @param environment the Environment (Scope), where the current A.S.T. Node it's inside
	 * 
	 * @return the evaluation of the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 *  	   given the Environment (Scope), where the current A.S.T. Node it's inside        
	 *
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Stack of Environments (Scopes/Frames)
	 * 
	 * @throws TypeErrorException a Type Error Exception thrown,
	 * 		   in the case of the Type of a Value it's completely unknown to
	 * 		   the recognised and acceptable Types for Values
	 * 
	 * @throws NumberArgumentsErrorException a raised Number of Arguments Error Exception,
	 * 		   in the case of the Number of Arguments used in the Evaluation,
	 *         wrong in the current Environment of Values (Scope/Frame) being evaluated
	 *         
	 */
	@Override
	public IValue eval(Environment<IValue> environment) throws ASTInvalidIdentifierException, TypeErrorException {
	
		return new VClosure(environment, this.functionArgumentsIDs, this.functionBody);
	
	}

	/**
	 * Compiles the List of Code Instructions of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside and
	 * the List of the Code Instructions of the current Node of an
	 * Abstract Syntax Tree (A.S.T.) will be kept, writing J.V.M. instructions.
	 * 
	 * @param environment the Environment (Scope/Frame), where the current Code Instructions of
	 *        the current Node of an Abstract Syntax Tree (A.S.T.) will be kept
	 * 
	 * @param codeInstructions the List of the Code Instructions to be compiled
	 * 
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Stack of Environments (Scopes/Frames) 
	 * 
	 */
	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
		   throws ASTInvalidIdentifierException {
		
		codeBlockInstructionsSet.addCodeInstruction(String.format("new f000%d_frame", 1 /* TODO */));
		codeBlockInstructionsSet.addCodeInstruction(String.format("dup"));		
		codeBlockInstructionsSet.addCodeInstruction(String.format("aload 0"));
		
		int staticLinkAncestorHeapStackFrameID = 
				codeBlockInstructionsSet.getCurrentHeapStackFrame()
				.getStaticLinkAncestorHeapStackFrame().getHeapStackFrameID();
		
		codeBlockInstructionsSet.addCodeInstruction
				(String.format("getfield closure_f000%d/%d %s",
							   staticLinkAncestorHeapStackFrameID
							   )); //TODO type
		
		codeBlockInstructionsSet.addCodeInstruction
				(String.format("putfield f000%d_frame/%d %s",
							   staticLinkAncestorHeapStackFrameID)); //TODO type
		
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
	 * @return the Type for the A.S.T. Node, after the Typechecking be performed
	 * 
	 */
	@Override
	public IType typecheck(Environment<IType> environment) throws TypeErrorException, ASTDuplicatedIdentifierException {
		// TODO Auto-generated method stub
		
		Set<String> argumentsTypecheckingVerified = new HashSet<>();
		
		for(String argumentID : this.functionArgumentsIDs) {
			
			if(!argumentsTypecheckingVerified.contains(argumentID)) {
				
				argumentsTypecheckingVerified.add(argumentID);
				
			}
			else {
				
				throw new ASTDuplicatedIdentifierException("Duplicated Identifier found in Function's declaration!!!");
				
			}
		}
		
		
		
		return null;
	}
}