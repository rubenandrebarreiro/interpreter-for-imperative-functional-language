package abstractsyntaxtree.scopes.compiler;

import java.io.FileOutputStream;
import java.io.IOException;

import abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import abstractsyntaxtree.scopes.structures.HeapFieldOffsetLocations;

public class EnvironmentCompiler {
	
	// Global Instance Variables:
	
	private HeapFieldOffsetLocations offsetLocations;
	
	/**
	 * The File Output Stream to write the Java Byte Code,
	 * in order to generate the Jasmim file, corresponding to the 
	 * code for the new frame.
	 */
	private FileOutputStream fileOutputStream;

	/**
	 * The ID of the Frame
	 */
	private int frameID;
	
	/**
	 * The Static Link pointer to its Ancestor
	 */
	private EnvironmentCompiler staticLinkAncestorHeapFrame;
	
	/**
	 * The Number of Fields of the Heap Stack Frame
	 */
	private int numberOfFields;
	
	public EnvironmentCompiler() {
		//TODO Change these default values
		offsetLocations = new HeapFieldOffsetLocations();
		frameID = -1;
		staticLinkAncestorHeapFrame = null;
		numberOfFields = 10;
		System.out.println("New root environment created;");
	}

	public EnvironmentCompiler(int frameID, EnvironmentCompiler ancestor, int nFields) {
		offsetLocations = new HeapFieldOffsetLocations();
		this.frameID = frameID + 1;
		if(frameID < 0) staticLinkAncestorHeapFrame = null;
		else staticLinkAncestorHeapFrame = ancestor;
		numberOfFields = nFields;
		System.out.println("New environment created with frameID = " + this.frameID);
		try {
			generateAndDumpsHeapStackFrameFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public EnvironmentCompiler beginScope() {
		return new EnvironmentCompiler(frameID, this, numberOfFields);
	}
	
	public int getFrameID() {
		return frameID;
	}
	
	public EnvironmentCompiler getAncestor() {
		return staticLinkAncestorHeapFrame;
	}
	
	public int getNumberOfFields() {
		return numberOfFields;
	}
	
	public boolean isEnvironmentRoot() {
		return staticLinkAncestorHeapFrame == null ? true : false;
	}
	
	
	public int find(String expressionID) throws ASTInvalidIdentifierException {
		
		Integer expressionValue = offsetLocations.findOffsetLocation(expressionID);
		
		if(expressionValue == null && this.staticLinkAncestorHeapFrame == null) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + "DeadCode1!?");
			throw new ASTInvalidIdentifierException("No Value with the following ID: " + expressionID + " !!!");
		}

		else if(expressionValue == null && this.staticLinkAncestorHeapFrame != null) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + "DeadCode2!?");
			return this.staticLinkAncestorHeapFrame.find(expressionID);
		}
				
		else {
			return expressionValue;
		}
	}
	
	public int getLevelFromRoot() {
		int currentLevelHeapStackFrames = 0;
		EnvironmentCompiler currentHeapStackFrame = this;
		
		while(currentHeapStackFrame.getAncestor() != null) {
			
			currentHeapStackFrame = currentHeapStackFrame.getAncestor();
			
			currentLevelHeapStackFrames++;
		}
		
		return currentLevelHeapStackFrames;
	}
	
	public void generateAndDumpsHeapStackFrameFile() throws IOException {
		
		// The Heap Stack Frame Filename for this Heap Stack Frame
		String heapStackFrameFilename = String.format("f%s.j", this.frameID);
		
		// The File Output Stream to write the File, corresponding to this Heap Stack Frame
		this.fileOutputStream = new FileOutputStream(heapStackFrameFilename);
		
		
		// Start to write the code instructions, in Java Byte Code
		
		// Write the code instruction, related to the class ID of the Heap Stack Frame, in Java Byte Code
		this.fileOutputStream.write(String.format(".class			f%s\n", this.frameID).getBytes());
		
		// Get the code instruction, related to the superclass ID of the Heap Stack Frame, in Java Byte Code
		String superClassReference = this.isEnvironmentRoot() ?
				String.format("java/lang/Object\n") :
					String.format("f%s\n", this.getAncestor().getFrameID()) ;
		
		// Write the code instruction, related to the superclass ID of the Heap Stack Frame, in Java Byte Code
		this.fileOutputStream.write(String.format(".super			%s", superClassReference).getBytes());

		// Write the code instruction, related to the Static Link to the ID of the Ancestor Heap Stack Frame, in Java Byte Code
		this.fileOutputStream.write(String.format(".field			public sl L%s\n", superClassReference).getBytes());
		
		// Write the code instructions, related to the all the fields of the Heap Stack Frame, in Java Byte Code
		for(int numberOfField = 0; numberOfField < this.numberOfFields; numberOfField++) {
			this.fileOutputStream.write(String.format(".field			public x_%s type\n", numberOfField).getBytes());
		}
		
		// Write the code instruction,
		// related to the end of the declaration of the class structure for this Heap Stack Frame, in Java Byte Code 
		this.fileOutputStream.write(String.format(".end method\n").getBytes());
	}
}
