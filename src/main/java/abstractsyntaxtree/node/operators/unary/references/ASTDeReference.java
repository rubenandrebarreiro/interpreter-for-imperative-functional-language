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
		
		
		String stackRefName = this.referenceType.getStackRefName();
		String stackFrameName = this.referenceType.getStackFrameName();
		
		codeBlockInstructionsSet.addCodeInstruction( String.format("checkcast %s", stackRefName) );
		codeBlockInstructionsSet.addCodeInstruction( String.format("getfield %s/v %s", stackRefName, stackFrameName) );
		
	}


	@Override
	public IType typecheck(Environment<IType> environment)
		   throws TypeErrorException,
	   		      ASTInvalidIdentifierException,
	   		      ASTDuplicatedIdentifierException,
	   		      NumberArgumentsErrorException {
	
		this.referenceType = (TRef) this.referenceValueToBeDeReferenced.typecheck(environment);
	
		if(this.referenceType instanceof TRef) {
			
			return this.referenceType.getReferenceType();
			
		}
		else {
			
			throw new TypeErrorException(TYPE_ERROR_MESSAGE);
		
		}
	}
	
}