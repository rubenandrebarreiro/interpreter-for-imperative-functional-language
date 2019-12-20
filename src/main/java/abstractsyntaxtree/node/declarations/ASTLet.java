package main.java.abstractsyntaxtree.node.declarations;

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

import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.scopes.structures.heap.HeapStackFrame;
import main.java.types.IType;
import main.java.values.atomics.IValue;
import main.java.values.utils.exceptions.NumberArgumentsErrorException;
import main.java.values.utils.exceptions.TypeErrorException;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing the association of its descendants {ID -> Value}.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTLet implements ASTNode {
	
	// Global Variables:
	
	/**
	 * The Identifier of A.S.T. Let Node descendant
	 */
	private List<ASTNode> associations;
	
	/**
	 * The Body Expression of A.S.T. Let Node descendant
	 */
	private ASTNode bodyASTLetNodeDescendant;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree (A.S.T.).
	 * 
	 * @param leftASTNodeDescedant the left side Descendant of the A.S.T. Node
	 * 
	 * @param rightASTNodeDescedant the left side Descendant of the A.S.T. Node
	 *
	 */
	public ASTLet(List<ASTNode> associations, ASTNode bodyASTLetNodeDescendant) {
		
		this.associations = associations;
		
		this.bodyASTLetNodeDescendant = bodyASTLetNodeDescendant;
	}
	
	
	// Methods:
	
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
	public IValue eval(Environment<IValue> environment)
		   throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {
		
		// Stars the Scope (Environment) of the declared expression
		Environment<IValue> newEnv = environment.beginScope();
		
		try {
			// The addition of the association between the left side Descendant
			// and the expression evaluated of the right side
			for (ASTNode astNode : associations) {
				astNode.eval(newEnv);
			}
		}
		catch (ASTInvalidIdentifierException astInvalidIdentifierException) {
			astInvalidIdentifierException.printStackTrace();
		}
		catch (TypeErrorException typeErrorException) {
			typeErrorException.printStackTrace();
		}

		// The evaluation of the right side Descendant
		IValue expressionEvaluated = this.bodyASTLetNodeDescendant.eval(newEnv);
		
		// Ends the Scope (Environment) of the previously declared expression
		newEnv.endScope();
		
		// Returns the expression evaluated
		return expressionEvaluated;
	}


	/**
	 * Compiles the List of Code Instructions of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside and
	 * the List of the Code Instructions of the current Node of an
	 * Abstract Syntax Tree (A.S.T.) will be kept, writing J.V.M. instructions,
	 * in order to, perform the Let/Declaration.
	 * 
	 * @param environment the Environment (Scope/Frame), where the current Code Instructions of
	 *        the current Node of an Abstract Syntax Tree (A.S.T.) will be kept
	 * 
	 * @param codeInstructions the List of the Code Instructions to be compiled
	 * 
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Stack of Environments (Scopes/Frames) 
	 */
	@Override
	public void compile(EnvironmentCompiler environmentCompiler,
			            CodeBlockInstructionsSet codeBlockInstructions) throws ASTInvalidIdentifierException {
		
		EnvironmentCompiler newEnvironment = environmentCompiler.beginScope();
		
		HeapStackFrame currentFrame = null;
		
		if(codeBlockInstructions.getCurrentFrame() != null) {
			currentFrame = codeBlockInstructions.getCurrentFrame();
		}
		
		currentFrame = new HeapStackFrame(
				codeBlockInstructions.getHeapListSize(),
				currentFrame,
				associations.size());

		codeBlockInstructions.addHeapStackFrame(currentFrame);
		codeBlockInstructions.setCurrentFrame(currentFrame);
		
		this.createFrame(codeBlockInstructions, currentFrame);
		
		for(ASTNode associationASTNode : this.associations) {
			associationASTNode.compile(newEnvironment, codeBlockInstructions);
		}
		
		this.bodyASTLetNodeDescendant.compile(newEnvironment, codeBlockInstructions);
		
		this.removeFrame(codeBlockInstructions, currentFrame);
		
		currentFrame = currentFrame.getStaticLinkAncestorHeapFrame();
		codeBlockInstructions.setCurrentFrame(currentFrame);
	}
	
	private void createFrame(CodeBlockInstructionsSet codeInstructions, HeapStackFrame currentFrame) {
		
		HeapStackFrame ancestorFrame = currentFrame.getStaticLinkAncestorHeapFrame();
		int currentFrameID = currentFrame.getHeapStackFrameID();
		
		codeInstructions.addCodeInstruction(String.format(";------------------Start new frame------------------"));
		codeInstructions.addCodeInstruction(String.format("new f%d", currentFrameID));
		codeInstructions.addCodeInstruction(String.format("dup"));
		codeInstructions.addCodeInstruction(String.format("invokespecial f%d/<init>()V", currentFrameID));
		codeInstructions.addCodeInstruction(String.format("dup"));
		codeInstructions.addCodeInstruction(String.format("aload 0"));
		
		if(ancestorFrame == null) {
			codeInstructions.addCodeInstruction(String.format("putfield f%d/sl Ljava/lang/Object;", currentFrameID));
		}
		else {
			codeInstructions.addCodeInstruction(String.format("putfield f%d/sl Lf%d;",
															  currentFrameID, ancestorFrame.getHeapStackFrameID()));
		}
		
		codeInstructions.addCodeInstruction(String.format("astore 0"));
		codeInstructions.addCodeInstruction(String.format(";------------------End new frame------------------"));
		codeInstructions.addCodeInstruction(String.format("\n"));
		
	}
	
	private void removeFrame(CodeBlockInstructionsSet codeInstructions, HeapStackFrame currentFrame) {
		
		HeapStackFrame ancestorFrame = currentFrame.getStaticLinkAncestorHeapFrame();
		int currentFrameID = currentFrame.getHeapStackFrameID();
		
		codeInstructions.addCodeInstruction(String.format("\n"));
		codeInstructions.addCodeInstruction(String.format(";------------------Start remove frame------------------"));
		codeInstructions.addCodeInstruction(String.format("aload 0"));
		
		if(ancestorFrame == null) {
			codeInstructions.addCodeInstruction(String.format("getfield f%d/sl Ljava/lang/Object;", currentFrameID));
		}
		else {
			codeInstructions.addCodeInstruction(String.format("getfield f%d/sl Lf%d;",
															  currentFrameID, ancestorFrame.getHeapStackFrameID()));
		}
		
		codeInstructions.addCodeInstruction(String.format("astore 0\n"));
		codeInstructions.addCodeInstruction(String.format(";------------------End remove frame------------------"));
		codeInstructions.addCodeInstruction(String.format("\n"));	
	}


	@Override
	public IType typecheck(Environment<IType> environment) throws TypeErrorException {
		// TODO Auto-generated method stub
		return null;
	}
}