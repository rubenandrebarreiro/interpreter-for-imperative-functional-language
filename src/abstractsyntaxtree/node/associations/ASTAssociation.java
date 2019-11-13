package abstractsyntaxtree.node.associations;

import abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import abstractsyntaxtree.node.ASTNode;
import abstractsyntaxtree.scopes.Environment;
import abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructions;

public class ASTAssociation implements ASTNode {

	private String id;
	private ASTNode value;
	
	public ASTAssociation(String id, ASTNode value) {
		this.id = id;
		this.value = value;
	}
	
	@Override
	public int eval(Environment environment) throws ASTInvalidIdentifierException {
		environment.addAssoc(id, value.eval(environment));
		return value.eval(environment);
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructions codeBlockInstructions) {
		// TODO Auto-generated method stub
		int tempCurrentField = environmentCompiler.getCurrentField();
		environmentCompiler.addAssoc(id);

		codeBlockInstructions.addCodeInstruction(";------------------Start new assignment------------------");
		codeBlockInstructions.addCodeInstruction("aload 0");
		value.compile(environmentCompiler, codeBlockInstructions);
		codeBlockInstructions.addCodeInstruction("putfield f" + environmentCompiler.getFrameID() + "/x" + tempCurrentField + " I");
		codeBlockInstructions.addCodeInstruction(";------------------End new assignment------------------");
		codeBlockInstructions.addCodeInstruction("\n");
	}
}
