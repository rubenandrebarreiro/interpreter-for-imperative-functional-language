package main.java.abstractsyntaxtree.node.references;

import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.references.TRef;
import main.java.values.atomics.IValue;
import main.java.values.exceptions.NumberArgumentsErrorException;
import main.java.values.exceptions.TypeErrorException;

public class ASTDeReference implements ASTNode {
	
	private static final String TYPE_ERROR_MESSAGE = 
			"Illegal arguments to Dereference operator:\n"
		  + "- The value used in the respectively Reference it's not of Reference Type!!!";
	
	private ASTNode referenceValueToBeDereferenced;
	
	
	public ASTDeReference(ASTNode referenceValueToBeDereferenced) {
		
		this.referenceValueToBeDereferenced = 
				referenceValueToBeDereferenced;
		
	}


	@Override
	public IValue eval(Environment<IValue> environment)
			throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {
		
		return this.referenceValueToBeDereferenced.eval(environment);
	}


	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
			throws ASTInvalidIdentifierException {

		// TODO
		this.referenceValueToBeDereferenced.compile(environmentCompiler, codeBlockInstructionsSet);
		
		codeBlockInstructionsSet.addCodeInstruction("getfield "); //TODO
		
	}


	@Override
	public IType typecheck(Environment<IType> environment)
		   throws TypeErrorException, ASTInvalidIdentifierException, NumberArgumentsErrorException {
		
		IType referenceValueToBeDereferencedType = this.referenceValueToBeDereferenced.typecheck(environment);
	
		if(referenceValueToBeDereferencedType instanceof TRef) {
			
			return ((TRef) referenceValueToBeDereferencedType).getReferenceType(); //TODO
			
		}
		else {
			
			throw new TypeErrorException(TYPE_ERROR_MESSAGE);
		
		}
		
	}
	
}