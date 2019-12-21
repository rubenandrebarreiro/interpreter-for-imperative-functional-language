package main.java.scopes.compiler.instructions.closures;

import java.io.FileOutputStream;
import java.io.IOException;

import main.java.scopes.structures.heap.HeapStackFrame;

public class ClosureClass {

	// Global Instance Variables:
	
	/**
	 * The File Output Stream responsible to
	 * generate/write the Code Block of Instructions Set in Java Byte Code.
	 * 
	 */
	private FileOutputStream fileOutputStream;
	private ClosureInterface closureInterface;
	private int closureID;
	private HeapStackFrame heapStackFrame;
	
	
	/**
	 * Dumps the all the content of Instructions Set, in the Java Byte Code file.
	 * 
	 * @throws IOException an Input/Output raised,
	 * 		   during the Dump process, in the Java Byte Code file
	 */
	public void dump(String filename, boolean isString) throws IOException {
		
		this.fileOutputStream = new FileOutputStream(filename);
		
		this.fileOutputStream.write(String.format(".class public closure_%d", this.closureID).getBytes());
		
		this.fileOutputStream.write(String.format(".super java/lang/Object").getBytes());
		
		this.fileOutputStream
			.write(String.format(".implements %s",
								 this.closureInterface.getClosureInterfaceName()).getBytes());
		
		this.fileOutputStream
			.write(String.format(".field public sl Lframe_%d",
				   this.heapStackFrame.getStaticLinkAncestorHeapStackFrame().getHeapStackFrameID())
				   .getBytes());
			
		
		
		this.fileOutputStream
			.write(String.format("\n").getBytes());
		
				
		this.fileOutputStream
			.write(String.format(".method public <init>()V").getBytes());
	
		this.fileOutputStream
			.write(String.format("	aload_0").getBytes());
	
		this.fileOutputStream
			.write(String.format("	invokespecial java/lang/Object/<init>()V").getBytes());
	
		this.fileOutputStream
			.write(String.format("	return").getBytes());

		this.fileOutputStream
			.write(String.format(".end method").getBytes());

		
		
		this.fileOutputStream
			.write(String.format("\n").getBytes());
	
		
	}
}
