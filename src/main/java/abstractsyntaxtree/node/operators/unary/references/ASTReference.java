package main.java.abstractsyntaxtree.node.operators.unary.references;

import java.io.IOException;

import main.java.abstractsyntaxtree.exceptions.ASTDuplicatedIdentifierException;
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.node.operators.unary.references.utils.ReferenceFileMaker;
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.codeblocks.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.references.TRef;
import main.java.values.atomics.IValue;
import main.java.values.references.VRef;
import main.java.values.utils.exceptions.NumberArgumentsErrorException;
import main.java.values.utils.exceptions.TypeErrorException;
import main.java.values.utils.exceptions.WrongArgumentTypeErrorException;

public class ASTReference<T> implements ASTNode {

	private ASTNode referenceValue;
	
	private TRef referenceType;
	
	
	public ASTReference(ASTNode referenceValue) {
		
		this.referenceValue = referenceValue;
		
	}
	
	
	@Override
	public IValue eval(Environment<IValue> environment)
		   throws ASTInvalidIdentifierException, TypeErrorException,
		          NumberArgumentsErrorException {
		
		return new VRef(referenceValue.eval(environment));
	
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
		   throws ASTInvalidIdentifierException {
		
		
		String stackRefName = this.referenceType.getHeapStackFrameReferenceName();
		String stackFrameName = this.referenceType.getHeapStackFrameName();
		int stackRefID = environmentCompiler.getCurrentReferenceNumber();
		
		ReferenceFileMaker refMaker = new ReferenceFileMaker(stackRefID, stackFrameName);
		try {
			refMaker.generateAndDumpsHeapStackFrameFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		codeBlockInstructionsSet.addCodeInstruction( String.format("new %s%d", stackRefName, stackRefID) );
		
		codeBlockInstructionsSet.addCodeInstruction("dup");
		codeBlockInstructionsSet.addCodeInstruction( String.format("invokespecial %s%d/<init>()V", stackRefName, stackRefID) );
		codeBlockInstructionsSet.addCodeInstruction("dup");
		
		this.referenceValue.compile(environmentCompiler, codeBlockInstructionsSet);
		
		codeBlockInstructionsSet.addCodeInstruction( String.format("putfield %s%d/v %s;", stackRefName, stackRefID, stackFrameName) );
		
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
	   		      NumberArgumentsErrorException, WrongArgumentTypeErrorException {
		
        this.referenceType = new TRef(this.referenceValue.typecheck(environment));
		
		return this.referenceType;
		
	}
	
}