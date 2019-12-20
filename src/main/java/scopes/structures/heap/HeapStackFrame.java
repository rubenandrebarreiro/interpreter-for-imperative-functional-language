package main.java.scopes.structures.heap;

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

import main.java.scopes.structures.heap.utils.FieldAddress;
import main.java.scopes.structures.heap.utils.HeapStackFrameFieldOffsetLocations;

/**
 * Class for the Heap Stack Frame.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class HeapStackFrame {

	// Global Instance Variables:
	/**
	 * The File Output Stream to write the Java Byte Code,
	 * in order to generate the Jasmim file, corresponding to the .
	 */
	private FileOutputStream fileOutputStream;
	
	/**
	 * The ID of the Heap Stack Frame
	 */
	private int heapStackFrameID;
	
	/**
	 * The Static Link pointer to its Heap Stack Frame Ancestor
	 */
	private HeapStackFrame staticLinkAncestorHeapFrame;
	
	/**
	 * The Number of Fields of the Heap Stack Frame
	 */
	private int numberOfFields;
	
	private HeapStackFrameFieldOffsetLocations stackFrameFieldLocations;
	
	
	// Constructors:
	/**
	 * Constructor #1:
	 * - The Constructor of the Heap Stack Frame,
	 *   given its ID, the Static Link pointer to its Heap Stack Frame Ancestor and its Number of Fields.
	 * 
	 * @param heapStackFrameID the ID of the Heap Stack Frame
	 * 
	 * @param staticLinkAncestorHeapFrame the Static Link pointer to its Heap Stack Frame Ancestor
	 * 
	 * @param numberOfFields the Number of Fields of the Heap Stack Frame
	 */
	public HeapStackFrame(int heapStackFrameID, HeapStackFrame staticLinkAncestorHeapFrame, int numberOfFields) {
		this.heapStackFrameID = heapStackFrameID;
		this.staticLinkAncestorHeapFrame = staticLinkAncestorHeapFrame;
		this.numberOfFields = numberOfFields;
		this.stackFrameFieldLocations = new HeapStackFrameFieldOffsetLocations();
	}
	
	/**
	 * Returns the ID of the Heap Stack Frame.
	 * 
	 * @return the ID of the Heap Stack Frame
	 */
	public int getHeapStackFrameID() {
		return this.heapStackFrameID;
	}
	
	/**
	 * Returns the Static Link pointer to its Heap Stack Frame Ancestor.
	 * 
	 * @return the Static Link pointer to its Heap Stack Frame Ancestor
	 */
	public HeapStackFrame getStaticLinkAncestorHeapFrame() {
		return this.staticLinkAncestorHeapFrame;
	}
	
	/**
	 * Returns the Number of Fields of the Heap Stack Frame
	 * 
	 * @return the Number of Fields of the Heap Stack Frame
	 */
	public int getNumberOfFields() {
		return this.numberOfFields;
	}
	
	/**
	 * Returns a boolean value too keep if the Heap Stack Frame it's the Root, i.e.,
	 * true if the Heap Stack Frame it's the Root (don't have Heap Stack Frame Ancestor) or
	 * false if the Heap Stack Frame it's not the Root (have, at least, one Heap Stack Frame Ancestor).
	 * 
	 * @return true if the Heap Stack Frame it's the Root (don't have Heap Stack Frame Ancestor) or
	 * 	       false if the Heap Stack Frame it's not the Root (have, at least, one Heap Stack Frame Ancestor) 
	 */
	public boolean isHeapFrameRoot() {
		return this.staticLinkAncestorHeapFrame == null ? true : false;
	}
	
	/**
	 * Returns the level of the Heap Stack Frame, counting it from the Root.
	 * 
	 * @return the level of the Heap Stack Frame, counting it from the Root
	 */
	public int getLevelHeapStackFrameFromRoot() {
		int currentLevelHeapStackFrames = 0;
		HeapStackFrame currentHeapStackFrame = this;
		
		while(currentHeapStackFrame.getStaticLinkAncestorHeapFrame() != null) {
			
			currentHeapStackFrame = currentHeapStackFrame.getStaticLinkAncestorHeapFrame();
			
			currentLevelHeapStackFrames++;
		}
		
		return currentLevelHeapStackFrames;
	}
	
	/**
	 * Generate the Heap Stack Frame file and dumps the corresponding code instructions,
	 * wrote in Java Byte Code.
	 * 
	 * @throws IOException an Input/Output Exception raised,
	 * 		   when an Input/Output error occurred.
	 */
	public void generateAndDumpsHeapStackFrameFile() throws IOException {
		
		// The Heap Stack Frame Filename for this Heap Stack Frame
		String heapStackFrameFilename = String.format("f%s.j", this.heapStackFrameID);
		
		// The File Output Stream to write the File, corresponding to this Heap Stack Frame
		this.fileOutputStream = new FileOutputStream(heapStackFrameFilename);
		
		
		// Start of the set of Java Byte Code, writing J.V.M. instructions

		// Write the code instruction, related to the class ID of the Heap Stack Frame,
		// in Java Byte Code, writing J.V.M. instructions
		this.fileOutputStream.write(String.format(".class			f%s\n", this.heapStackFrameID).getBytes());
		
		// Get the code instruction, related to the superclass ID of the Heap Stack Frame,
		// in Java Byte Code, writing J.V.M. instructions
		String superClassReference = this.staticLinkAncestorHeapFrame == null ?
				String.format("java/lang/Object") :
					String.format("f%s", getStaticLinkAncestorHeapFrame().heapStackFrameID) ;
		
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
		
		// Write a new break line in the file of the Heap Stack Frame,
		// in Java Byte Code, writing J.V.M. instructions
		this.fileOutputStream.write(String.format("\n").getBytes());
		
		// Write J.V.M. Byte Code Instructions,
		// such it's loaded the current frame from the position on the memory,
		// initialise the Object of the current frame,
		// to the respectively Frame file generated,
		// in order to be placed in the Evaluation Stack
		this.fileOutputStream.write(String.format(".method			public <init>()V\n" + 
												  "     			aload_0\r\n" + 
												  " 	    		invokenonvirtual java/lang/Object/<init>()V\n" + 
												  " 		    	return\n").getBytes());
		
		
		// Write the code instruction,
		// related to the end of the declaration of the class structure for this Heap Stack Frame, in Java Byte Code 
		this.fileOutputStream.write(String.format(".end method\n").getBytes());
	}
	
	public void addAssoc(String id) {
		this.stackFrameFieldLocations.addAssoc(id, this.heapStackFrameID, this.stackFrameFieldLocations.getCurrentFieldOffsetLocation());
	}
	
	public int getCurrentFieldLocation() {
		return this.stackFrameFieldLocations.getCurrentFieldOffsetLocation();
	}
	
	public FieldAddress findOffsetLocation(String expressionID) {
		return this.stackFrameFieldLocations.findFieldOffsetLocation(expressionID);
	}
}