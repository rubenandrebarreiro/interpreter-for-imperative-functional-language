package main.java.scopes.compiler.instructions.closures;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import main.java.types.IType;
import main.java.types.functions.TFun;

public class ClosureInterface {
	
	// Global Instance Variables:
	
	/**
	 * The File Output Stream responsible to
	 * generate/write the Code Block of Instructions Set in Java Byte Code.
	 * 
	 */
	private FileOutputStream fileOutputStream;

	private TFun functionType;
	
	private String closureInterfaceName;
	
	private String closureCallDeclaration;
	
	
	public ClosureInterface(TFun functionType) {
	
		this.functionType = functionType;
		this.buildClosureInterfaceNameAndCallDeclaration();
		
	}
	
	
	public void buildClosureInterfaceNameAndCallDeclaration() {
		
		List<IType> functionArgumentsTypes = this.functionType.getFunctionArgumentsTypes();
		
		StringBuilder closureInterfaceNameStringBuilder = new StringBuilder();
		StringBuilder closureCallDeclarationStringBuilder = new StringBuilder();
		
		closureCallDeclarationStringBuilder.append("call(");
		
		
		for(IType functionArgumentType : functionArgumentsTypes) {
			
			closureInterfaceNameStringBuilder.append(functionArgumentType.getClosureInterfaceTypeName());
			closureInterfaceNameStringBuilder.append(String.format("_"));
			
			closureCallDeclarationStringBuilder.append(functionArgumentType.getHeapStackFrameName());
			
		}
		
		
		IType functionReturnType = this.functionType.getFunctionReturnType();
		
		closureInterfaceNameStringBuilder.append(String.format("To"));
		closureInterfaceNameStringBuilder.append(functionReturnType.getClosureInterfaceTypeName());
		
		closureCallDeclarationStringBuilder.append(String.format(")"));
		closureCallDeclarationStringBuilder.append(String.format(functionReturnType.getHeapStackFrameName()));
		
		this.closureInterfaceName = closureInterfaceNameStringBuilder.toString();
		
	}
	
	/**
	 * Dumps the all the content of Instructions Set, in the Java Byte Code file.
	 * 
	 * @throws IOException an Input/Output raised,
	 * 		   during the Dump process, in the Java Byte Code file
	 */
	public void dump(String filename, boolean isString) throws IOException {
		
		this.fileOutputStream = new FileOutputStream(filename);
		
		this.fileOutputStream
			.write(String.format(".interface public closure_interface_%s",
			 					 this.closureInterfaceName).getBytes());
		
		this.fileOutputStream
			.write(String.format(".super java/lang/Object").getBytes());

		this.fileOutputStream
			.write(String.format(".method public abstract %s",
					             this.closureCallDeclaration).getBytes());
		
		this.fileOutputStream
			.write(String.format(".end method").getBytes());
		
	}
	
}
