package main.java.scopes.compiler.instructions.closures;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import main.java.scopes.structures.heap.HeapStackFrame;
import main.java.types.IType;
import main.java.types.functions.TFun;

public class ClosureClass {

	// Global Instance Variables:
	
	/**
	 * The File Output Stream responsible to
	 * generate/write the Code Block of Instructions Set in Java Byte Code.
	 * 
	 */
	private FileOutputStream fileOutputStream;

	private TFun functionType;
	
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
				   this.heapStackFrame.getStaticLinkAncestorHeapStackFrame()
				   	   .getHeapStackFrameID())
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
	
		
		
		this.fileOutputStream
			.write(String.format(".method public %s",
				                 this.closureInterface.getClosureCallDeclaration())
								 .getBytes());
		
		this.fileOutputStream
			.write(String.format(".limit locals 10").getBytes());
		
		this.fileOutputStream
			.write(String.format("	.limit stack 256").getBytes());
		
		this.fileOutputStream
			.write(String.format("	new frame_%d",
								 this.heapStackFrame.getHeapStackFrameID())
								 .getBytes());
		
		this.fileOutputStream.write(String.format("dup").getBytes());
		
		this.fileOutputStream
			.write(String.format("	invokespecial frame_%d/<init>()V",
								 this.heapStackFrame.getHeapStackFrameID())
								 .getBytes());
		
		this.fileOutputStream.write(String.format("dup").getBytes());
		
		this.fileOutputStream.write(String.format("aload 0").getBytes());
		
		this.fileOutputStream.write(String.format("dup").getBytes());
		
		this.fileOutputStream
			.write(String.format("getfield clousure_%d/sl Lframe_%d",
							     this.closureID,
							     this.heapStackFrame.getStaticLinkAncestorHeapStackFrame()
							     	 .getHeapStackFrameID())
								 .getBytes());
		
		this.fileOutputStream
			.write(String.format("putfield frame_%d/sl Lframe_%d",
								 this.heapStackFrame.getHeapStackFrameID(),
						     	 this.heapStackFrame.getStaticLinkAncestorHeapStackFrame()
						     	 	.getHeapStackFrameID())
								 .getBytes());
		
		
		
		// TODO evaluation of instructions
		int currentFunctionArgumentTypeIndex = 0;
		
		List<IType> functionArgumentsTypes = this.functionType.getFunctionArgumentsTypes();
		
		for(IType functionArgumentType : functionArgumentsTypes) {
			
			this.fileOutputStream.write(String.format("dup").getBytes());
			
			// The Pointer for itself
			this.fileOutputStream
				.write(String.format("%s %d",
									 functionArgumentType.getLoadJVMInstruction(),
									 currentFunctionArgumentTypeIndex)
					   .getBytes());
			
			this.fileOutputStream
			.write(String.format("putfield frame_%d/arg%d %s",
								 this.heapStackFrame.getHeapStackFrameID(),
								 ( currentFunctionArgumentTypeIndex + 1 ),
						     	 functionArgumentType.getHeapStackFrameName())
				   .getBytes());
			
			currentFunctionArgumentTypeIndex++;
		}
		
		
		
		this.fileOutputStream.write(String.format(".end method").getBytes());

		
		
	}
}
