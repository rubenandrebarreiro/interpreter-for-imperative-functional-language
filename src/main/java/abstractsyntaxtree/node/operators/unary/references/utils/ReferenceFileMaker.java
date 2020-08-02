package main.java.abstractsyntaxtree.node.operators.unary.references.utils;

import java.io.FileOutputStream;
import java.io.IOException;

public class ReferenceFileMaker {

	// Global Instance Variables:
	/**
	 * The File Output Stream to write the Java Byte Code,
	 * in order to generate the Jasmim file, corresponding to the .
	 */
	private FileOutputStream fileOutputStream;
	
	private int refID;
	
	private String valueType;
	
	public ReferenceFileMaker(int refID, String valueType) {
		this.refID = refID;
		this.valueType = valueType;
	}
	
	/**
	 * Generate the Reference file and dumps the corresponding code instructions,
	 * wrote in Java Byte Code.
	 * 
	 * @throws IOException an Input/Output Exception raised,
	 * 		   when an Input/Output error occurred.
	 */
	public void generateAndDumpsHeapStackFrameFile() throws IOException {
		
		// The Heap Stack Frame Filename for this Reference
		String heapStackFrameFilename = String.format("ref_class%s.j", this.refID);
		
		// The File Output Stream to write the File, corresponding to this Reference
		this.fileOutputStream = new FileOutputStream(heapStackFrameFilename);
		
		
		// Start of the set of Java Byte Code, writing J.V.M. instructions

		this.fileOutputStream.write(String.format(".class			ref_class%s\n", this.refID).getBytes());

		this.fileOutputStream.write(String.format(".super			java/lang/Object\n").getBytes());

		this.fileOutputStream.write(String.format(".field			public v %s;\n", valueType).getBytes());

		this.fileOutputStream.write(String.format("\n").getBytes());

		this.fileOutputStream.write(String.format(".method			public <init>()V\n" + 
												  "     			aload_0\r\n" + 
												  " 	    		invokenonvirtual java/lang/Object/<init>()V\n" + 
												  " 		    	return\n").getBytes());
		
		
		// Write the code instruction,
		// related to the end of the declaration of the class structure for this Reference, in Java Byte Code 
		this.fileOutputStream.write(String.format(".end method\n").getBytes());
	}
	
}
