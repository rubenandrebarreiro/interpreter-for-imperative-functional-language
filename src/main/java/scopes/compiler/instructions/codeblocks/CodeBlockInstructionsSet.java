package main.java.scopes.compiler.instructions.codeblocks;

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

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.java.scopes.structures.heap.HeapStackFrame;

/**
 * Class for the Code Block Instructions Set.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class CodeBlockInstructionsSet {
	
	// Global Instance Variables:
	
	/**
	 * The File Output Stream responsible to
	 * generate/write the Code Block of Instructions Set in Java Byte Code.
	 * 
	 */
	private FileOutputStream fileOutputStream;

	/**
	 * The List/Set of the Heap Stack Frames (Scopes/Frames).
	 */
	private List<HeapStackFrame> heapStackFramesSet;
	
	/**
	 * The List/Set of Code Block Instructions, in Java Byte Code.
	 */
	private List<String> codeBlockInstructionsSet;
	
	/**
	 * The pointer to the current Heap Stack Frame (Scopes/Frames).
	 */
	private HeapStackFrame currentFrame;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The constructor of the Code Block of Instructions Set, in Java Byte Code.
	 */
	public CodeBlockInstructionsSet() {
		
		this.codeBlockInstructionsSet = new ArrayList<String>();
		this.heapStackFramesSet = new ArrayList<HeapStackFrame>();
		
		this.addCodeInstruction(String.format("aconst_null\nastore 0\n"));
		
		this.currentFrame = null;
		
	}
	
	/**
	 * Adds a new Heap Stack Frame (Scope/Frame) to
	 * the List/Set of the Heap Stack Frames (Scopes/Frames).
	 * 
	 * @param heapStackFrame a new Heap Stack Frame (Scope/Frame) to be added
	 *        the List/Set of the Heap Stack Frames (Scopes/Frames)
	 */
	public void addHeapStackFrame(HeapStackFrame heapStackFrame) {
		this.heapStackFramesSet.add(heapStackFrame);
	}
	
	/**
	 * Returns the Heap Stack Frame (Scope/Frame), by a given identification.
	 * 
	 * @param id the identification of the Heap Stack Frame (Scope/Frame)
	 * 
	 * @return the Heap Stack Frame (Scope/Frame), by a given identification
	 */
	public HeapStackFrame getHeapStackFrameByID(int id) {
		   HeapStackFrame result = null;
		
		for (HeapStackFrame stackFrame : this.heapStackFramesSet) {
			
			if(stackFrame.getHeapStackFrameID() == id) {
				result = stackFrame;
				break;
			}
			
		}
		
		return result;
	}
	
	/**
	 * Adds a new Code Instruction on this Code Block of Instructions.
	 * 
	 * @param codeInstruction a new Code Instruction to be added
	 */
	public void addCodeInstruction(String codeInstruction) {
		this.codeBlockInstructionsSet.add(codeInstruction);
	}
	
	/*
	 * Returns the current Heap Stack Frame for
	 * the Scope/Frame of this Code Block of Instructions.
	 */
	public HeapStackFrame getCurrentHeapStackFrame() {
		return this.currentFrame;
	}
	
	/**
	 * Returns the size of the List/Set of Heap Stack Frames.
	 * 
	 * @return the size of the List/Set of Heap Stack Frames
	 */
	public int getHeapStackFrameListSize() {
		return this.heapStackFramesSet.size();
	}

	/**
	 * Sets the current Heap Stack Frame for
	 * the Scope/Frame of this Code Block of Instructions, given a new one.
	 * 
	 * @param newCurrentFrame a new current Heap Stack Frame for
	 * 		  the Scope/Frame of this Code Block of Instructions
	 */
	public void setCurrentFrame(HeapStackFrame newCurrentFrame) {
		this.currentFrame = newCurrentFrame;
	}
	
	/**
	 * Dumps the all the content of Instructions Set, in the Java Byte Code file.
	 * 
	 * @throws IOException an Input/Output raised,
	 * 		   during the Dump process, in the Java Byte Code file
	 */
	public void dump(String filename, boolean isString) throws IOException {
		
		this.fileOutputStream = new FileOutputStream(filename);
		
		this.dumpHeader();
		this.dumpCodeBlockInstructionsSetJavaByteCode();
		this.dumpFooter(isString);
		
		for(HeapStackFrame stackFrame : heapStackFramesSet) {
			stackFrame.generateAndDumpsHeapStackFrameFile();
		}
		
	}
	
	/**
	 * Dumps the Header, in the Java Byte Code file.
	 * 
	 * @throws IOException an Input/Output raised,
	 * 		   during the Dump process of the Header, in the Java Byte Code file
	 */
	private void dumpHeader() throws IOException {
		
		// Writing of the Header to the Output File of Compiler to be generated
		
		// The Class and Super Type of the Compiler Parser
		this.fileOutputStream.write(String.format(".class public CompilerParser\n").getBytes());
		this.fileOutputStream.write(String.format(".super java/lang/Object\n").getBytes());
		
		// A blank line
		this.fileOutputStream.write(String.format("\n").getBytes());
		
		// The Method of the Constructor of the Compiler Parser
		this.fileOutputStream.write(String.format(".method public <init>()V\n").getBytes());
		this.fileOutputStream.write(String.format("	aload_0\n").getBytes());
		this.fileOutputStream
			.write(String.format("	invokenonvirtual java/lang/Object/<init>()V\n").getBytes());
		this.fileOutputStream.write(String.format("	return\n").getBytes());
		this.fileOutputStream.write(String.format(".end method\n").getBytes());

		// A blank line
		this.fileOutputStream.write(String.format("\n").getBytes());
		
		// The Method of the Main Class of the Compiler Parser
		this.fileOutputStream
			.write(String.format(".method public static main([Ljava/lang/String;)V\n").getBytes());
		this.fileOutputStream.write(String.format("	; set limits used by this method\n").getBytes());
		this.fileOutputStream.write(String.format("	.limit locals 10\n").getBytes());
		this.fileOutputStream.write(String.format("	.limit stack 256\n").getBytes());
		
		// A blank line
		this.fileOutputStream.write(String.format("\n").getBytes());

		// The Method responsible to print some contents, native of the Java Language
		this.fileOutputStream
			.write(String.format("	;    1 - the PrintStream object held in java.lang.out\n").getBytes());
		this.fileOutputStream
			.write(String.format("	getstatic java/lang/System/out Ljava/io/PrintStream;\n").getBytes());
		
		// A blank line
		this.fileOutputStream.write(String.format("\n").getBytes());

		// The Comments indicating where it will begin the Set of Instructions
		this.fileOutputStream.write(String.format("	; place your bytecodes here\n").getBytes());
		this.fileOutputStream
			.write(String.format("	; --- START OF THE SET OF INSTRUCTIONS OF THE JAVA BYTE CODE ---\n")
					     .getBytes());
		
		// A blank line
		this.fileOutputStream.write(String.format("\n").getBytes());
		
	}

	/**
	 * Dumps the Code Block, containing an Instructions Set, in the Java Byte Code file.
	 * 
	 * @throws IOException an Input/Output raised,
	 * 		   during the writing of the Dump process of the Code Block,
	 *         containing an Instructions Set, in the Java Byte Code file
	 */
	private void dumpCodeBlockInstructionsSetJavaByteCode() throws IOException {
	
		for(String codeBlockInstruction : this.codeBlockInstructionsSet) {
			this.fileOutputStream.write(String.format("%s\n", codeBlockInstruction).getBytes());
		}
		
	}
	
	/**
	 * Dumps the Footer, in the Java Byte Code file.
	 * 
	 * @throws IOException an Input/Output raised,
	 * 		   during the Dump process of the Footer, in the Java Byte Code file
	 */
	private void dumpFooter(boolean isString) throws IOException {

		// Writing of the Footer to the Output File of Compiler to be generated
		
		// A blank line
		this.fileOutputStream.write(String.format("\n").getBytes());
		
		// The Starting of the Epilogue Code
		this.fileOutputStream.write(String.format("	; --- START OF EPILOGUE JAVA BYTE CODE ---\n").getBytes());
		
		// A blank line
		this.fileOutputStream.write(String.format("\n").getBytes());
		
		// The Epilogue Code
		this.fileOutputStream.write(String.format("	; convert to String;\n").getBytes());
		
		// Verification of instance of ASTNode in the case of being a ASTPrint don't check integer value of a String
		if(!isString) {
			this.fileOutputStream
				.write(String.format("   invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\n").getBytes());
		}
		this.fileOutputStream.write(String.format("   ; call println\n").getBytes());
		this.fileOutputStream
			.write(String.format("   invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n")
						 .getBytes());
		
		// A blank line
		this.fileOutputStream.write(String.format("\n").getBytes());
		
		// The Return statement
		this.fileOutputStream.write(String.format("   return\n").getBytes());
		
		// End of the method
		this.fileOutputStream.write(String.format(".end method").getBytes());
	
	}

}