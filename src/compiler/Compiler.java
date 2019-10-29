package compiler;

import java.io.IOException;

import abstractsyntaxtree.node.ASTNode;
import abstractsyntaxtree.scopes.Environment;
import abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructions;

public class Compiler {
	
	private Environment environment;
	
	private CodeBlockInstructions codeBlockIntructions;
	
	public Compiler(ASTNode rootNode) {
		
		this.environment = new Environment();
		this.codeBlockIntructions = new CodeBlockInstructions();
		
		rootNode.compile(environment, codeBlockIntructions);
	}
	
	public void generateClassJavaByteCode(String filename) throws IOException {
		this.codeBlockIntructions.dump(filename);
	}
}