package main.java.scopes.compiler;

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

public class EnvironmentCompiler {
	
	// Global Instance Variables:

	/**
	 * The ID of the Frame
	 */
	private int frameID;
	
	/**
	 * The Static Link pointer to its Ancestor
	 */
	private EnvironmentCompiler staticLinkAncestorHeapStackFrame;

	/**
	 * The Current number used for labels Lx
	 */
	private int currentLabelNumber;
	
	// Constructors:
	/**
	 * 
	 */
	public EnvironmentCompiler() {		
		this.staticLinkAncestorHeapStackFrame = null;
		this.currentLabelNumber = 0;
	}

	public EnvironmentCompiler(EnvironmentCompiler ancestorEnvironment) {		
		this.staticLinkAncestorHeapStackFrame = ancestorEnvironment;
		this.currentLabelNumber = 0;
	}
	
	public EnvironmentCompiler beginScope() {
		return new EnvironmentCompiler(this);
	}

	public boolean isEnvironmentRoot() {
		return this.staticLinkAncestorHeapStackFrame == null ? true : false;
	}
	
	public int getCurrentLabelNumber() {
		return currentLabelNumber;
	}
}