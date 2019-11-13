package abstractsyntaxtree.scopes.compiler;

import java.io.FileOutputStream;
import java.io.IOException;

import abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import abstractsyntaxtree.scopes.structures.FieldAddress;
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
	
	/**
	 * 
	 */
	private int currentFieldCounter;
	
	public EnvironmentCompiler() {
		this.offsetLocations = new HeapFieldOffsetLocations();
		
		frameID = -1;
		
		staticLinkAncestorHeapFrame = null;
		
		this.numberOfFields = 10;
		currentFieldCounter = 0;
		
		System.out.println("New root environment created!!!");
	}

	public EnvironmentCompiler(int frameID, EnvironmentCompiler ancestor, int numberOfFields) {
		
		this.offsetLocations = new HeapFieldOffsetLocations();
		
		this.frameID = frameID + 1;
		
		this.staticLinkAncestorHeapFrame = (frameID < 0) ? null : ancestor;
		
		this.numberOfFields = numberOfFields;
		this.currentFieldCounter = 0;
		
		System.out.println(String.format("New environment created with frameID = %d!!!", this.frameID));
		
		try {
			generateAndDumpsHeapStackFrameFile();
		}
		catch (IOException inputOutputException) {
			inputOutputException.printStackTrace();
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
	
	public void addAssoc(String id) {
		offsetLocations.addAssoc(id, frameID, currentFieldCounter++);
	}
	
	public int getCurrentField() {
		return currentFieldCounter;
	}
	
	public FieldAddress find(String expressionID) throws ASTInvalidIdentifierException {
		return offsetLocations.findOffsetLocation(expressionID);
	}
	
	/**
	 * Returns the level of the Heap Stack Frame,
	 * from the current compiled Environment.
	 * 
	 * @return the level of the Heap Stack Frame,
	 *         from the current compiled Environment
	 */
	public int getLevelFromRoot() {
		
		// The level of the current Heap Stack Frame
		int currentLevelHeapStackFrames = 0;
		
		// The current Heap Stack Frame, from the current compiled Environment
		EnvironmentCompiler currentHeapStackFrameEnvironment = this;
		
		// While the current Heap Stack Frame has ancestors,
		// doesn't reached the root node yet
		while(currentHeapStackFrameEnvironment.getAncestor() != null) {
			
			// The ancestor of the current Heap Stack Frame, from the current compiled Environment
			currentHeapStackFrameEnvironment = currentHeapStackFrameEnvironment.getAncestor();
			
			// Jumps one more level from the current Heap Stack Frame
			currentLevelHeapStackFrames++;
		}
		
		return currentLevelHeapStackFrames;
	}
	
	/**
	 * Generates and dumps the content from the Heap Stack Frame to a file.
	 * 
	 * @throws IOException an Input/Output Exception
	 */
	public void generateAndDumpsHeapStackFrameFile() throws IOException {
		
		// The Heap Stack Frame Filename for this Heap Stack Frame
		String heapStackFrameFilename = String.format("f%s.j", this.frameID);
		
		// The File Output Stream to write the File, corresponding to this Heap Stack Frame
		this.fileOutputStream = new FileOutputStream(heapStackFrameFilename);
		
		
		// Start of the set of Java Byte Code, writing J.V.M. instructions

		// Write the code instruction, related to the class ID of the Heap Stack Frame,
		// in Java Byte Code, writing J.V.M. instructions
		this.fileOutputStream.write(String.format(".class			f%s\n", this.frameID).getBytes());
		
		// Get the code instruction, related to the superclass ID of the Heap Stack Frame,
		// in Java Byte Code, writing J.V.M. instructions
		String superClassReference = this.isEnvironmentRoot() ?
				String.format("java/lang/Object") :
					String.format("f%s", this.getAncestor().getFrameID()) ;
		
		// Write the code instruction, related to the superclass ID of the Heap Stack Frame,
		// in Java Byte Code, writing J.V.M. instructions
		this.fileOutputStream.write(String.format(".super			java/lang/Object\n").getBytes());

		// Write the code instruction, related to the Static Link to the ID of the Ancestor Heap Stack Frame,
		// in Java Byte Code, writing J.V.M. instructions
		this.fileOutputStream.write(String.format(".field			public sl L%s;\n", superClassReference).getBytes());
		
		// Write the code instructions, related to the all the fields of the Heap Stack Frame,
		// in Java Byte Code, writing J.V.M. instructions
		for(int numberOfField = 0; numberOfField < this.numberOfFields; numberOfField++) {
			this.fileOutputStream.write(String.format(".field			public x%s I\n", numberOfField).getBytes());
		}
		
		// Write a new break line in the file of the Heap Stack frame,
		// in Java Byte Code, writing J.V.M. instructions
		this.fileOutputStream.write(String.format("\n").getBytes());
		
		// Write J.V.M. Byte Code Instructions,
		// such it's loaded the current frame from the position on the memory,
		// initialise the Object of the current frame,
		// to the respectively Frame file generated,
		// in order to be placed in the Evaluation Stack
		this.fileOutputStream.write(String.format(".method			public <init>()V\n" + 
				"			aload_0\r\n" + 
				"			invokenonvirtual java/lang/Object/<init>()V\n" + 
				"			return\n").getBytes());
				
		
		// Write the code instruction,
		// related to the end of the declaration of the class structure for this Heap Stack Frame, in Java Byte Code 
		this.fileOutputStream.write(String.format(".end method\n").getBytes());
	}
}