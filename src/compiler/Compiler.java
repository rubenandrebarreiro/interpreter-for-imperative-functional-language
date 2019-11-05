package compiler;

import java.io.IOException;

import abstractsyntaxtree.node.ASTNode;
import abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructions;

public class Compiler {
	
	private EnvironmentCompiler environment;
	
	private CodeBlockInstructions codeBlockIntructions;
	
	public Compiler(ASTNode rootNode) {
		
		this.environment = new EnvironmentCompiler();
		this.codeBlockIntructions = new CodeBlockInstructions();
		
		rootNode.compile(environment, codeBlockIntructions);
	}
	
	public void generateClassJavaByteCode(String filename) throws IOException {
		this.codeBlockIntructions.dump(filename);
	}
}