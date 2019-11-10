package abstractsyntaxtree.scopes.compiler.instructions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodeBlockInstructions {
	
	private FileOutputStream fileOutputStream;
	
	private List<String> codeBlockInstructionsSet;
	
	public CodeBlockInstructions() {
		this.codeBlockInstructionsSet = new ArrayList<String>();
	}
	
	public void addCodeInstruction(String codeInstruction) {
		this.codeBlockInstructionsSet.add(codeInstruction);
	}
	
	public void dump(String filename) throws IOException {
		
		this.fileOutputStream = new FileOutputStream(filename);
		
		this.dumpHeader();
		// TODO tratar astore e aload
		this.dumpCodeBlockInstructionsSetJavaByteCode();
		this.dumpFooter();
	}
	
	private void dumpHeader() throws IOException {
		
		// Writing of the Header to the Output File of Compiler to be generated
		
		// The Class and Super Type of the Compiler Parser
		this.fileOutputStream.write(".class public CompilerParser\n".getBytes());
		this.fileOutputStream.write(".super java/lang/Object\n".getBytes());
		
		// A blank line
		this.fileOutputStream.write("\n".getBytes());
		
		// The Method of the Constructor of the Compiler Parser
		this.fileOutputStream.write(".method public <init>()V\n".getBytes());
		this.fileOutputStream.write("	aload_0\n".getBytes());
		this.fileOutputStream.write("	invokenonvirtual java/lang/Object/<init>()V\n".getBytes());
		this.fileOutputStream.write("	return\n".getBytes());
		this.fileOutputStream.write(".end method\n".getBytes());

		// A blank line
		this.fileOutputStream.write("\n".getBytes());
		
		// The Method of the Main Class of the Compiler Parser
		this.fileOutputStream.write(".method public static main([Ljava/lang/String;)V\n".getBytes());
		this.fileOutputStream.write("	; set limits used by this method".getBytes());
		this.fileOutputStream.write("	.limit locals 10\n".getBytes());
		this.fileOutputStream.write("	.limit stack 256\n".getBytes());
		
		// A blank line
		this.fileOutputStream.write("\n".getBytes());

		// The Method of TODO
		this.fileOutputStream.write("	;    1 - the PrintStream object held in java.lang.out\n".getBytes());
		this.fileOutputStream.write("	getstatic java/lang/System/out Ljava/io/PrintStream;\n".getBytes());
		
		// A blank line
		this.fileOutputStream.write("\n".getBytes());

		// The Comments indicating where it will begin the Set of Instructions
		this.fileOutputStream.write("	; place your bytecodes here\n".getBytes());
		this.fileOutputStream.write("	; --- START OF THE SET OF INSTRUCTIONS OF THE JAVA BYTE CODE ---\n".getBytes());
		
		// A blank line
		this.fileOutputStream.write("\n".getBytes());
		
	}
	
	private void dumpCodeBlockInstructionsSetJavaByteCode() throws IOException {
		for(String codeBlockInstruction : this.codeBlockInstructionsSet) {
			this.fileOutputStream.write((codeBlockInstruction+"\n").getBytes());
		}
	}
	
	private void dumpFooter() throws IOException {

		// Writing of the Footer to the Output File of Compiler to be generated
		
		// A blank line
		this.fileOutputStream.write("\n".getBytes());
		
		// The Starting of the Epilogue Code
		this.fileOutputStream.write("	; --- START OF EPILOGUE JAVA BYTE CODE ---\n".getBytes());
		
		// A blank line
		this.fileOutputStream.write("\n".getBytes());
		
		// The Epilogue Code
		this.fileOutputStream.write("	; convert to String;\n".getBytes());
		this.fileOutputStream.write("   invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\n".getBytes());
		this.fileOutputStream.write("   ; call println\n".getBytes());
		this.fileOutputStream.write("   invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n".getBytes());
	}
}
