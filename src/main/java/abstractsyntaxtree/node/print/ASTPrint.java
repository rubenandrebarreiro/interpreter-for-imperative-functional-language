package main.java.abstractsyntaxtree.node.print;

import main.java.abstractsyntaxtree.exceptions.ASTDuplicatedIdentifierException;
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.values.atomics.IValue;
import main.java.values.exceptions.NumberArgumentsErrorException;
import main.java.values.exceptions.TypeErrorException;

public class ASTPrint implements ASTNode {

	private ASTNode stringToPrint;
	
	
	public ASTPrint(ASTNode stringToPrint) {
		this.stringToPrint = stringToPrint;
	}
	
	@Override
	public IValue eval(Environment<IValue> environment)
			throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {
		
		System.out.println(this.stringToPrint.eval(environment)); // TODO
		
		return null;
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
			throws ASTInvalidIdentifierException {

		codeBlockInstructionsSet.addCodeInstruction("getstatic java/lang/System/out Ljava/io/PrintStream;");
		
		this.stringToPrint.compile(environmentCompiler, codeBlockInstructionsSet);
		
		codeBlockInstructionsSet.addCodeInstruction("invokevirtual java/io/PrintStream/println(Ljava/lang/String)V");
		
	}

	@Override
	public IType typecheck(Environment<IType> environment)
	       throws TypeErrorException,
	   		      ASTInvalidIdentifierException,
	   		      ASTDuplicatedIdentifierException,
	   		      NumberArgumentsErrorException {
	

		return this.stringToPrint.typecheck(environment);
		
	}

}
