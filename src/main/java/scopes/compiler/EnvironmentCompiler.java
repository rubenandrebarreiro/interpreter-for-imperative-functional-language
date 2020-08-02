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

/**
 * Class for the Environment Compiler.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class EnvironmentCompiler {
	
	// Global Instance Variables:

	/**
	 * The Static Link pointer to its Environment Compiler (Scope/Frame) Ancestor
	 */
	private EnvironmentCompiler staticLinkAncestorHeapStackFrame;

	/**
	 * The current number used for labels "Lx"
	 */
	private int currentLabelNumber;
	
	/**
	 * The current number used for labels "ref_class"
	 */
	private int currentReferenceNumber;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The constructor of an Environment Compiler,
	 *   for a Scope/Frame without any ancestor;
	 */
	public EnvironmentCompiler() {		
		this.staticLinkAncestorHeapStackFrame = null;
		this.currentLabelNumber = 0;
		this.currentReferenceNumber = 0;
	}

	/**
	 * Constructor #2:
	 * - The constructor of an Environment Compiler,
	 *   for a Scope/Frame with, at least, one ancestor;
	 *   
	 * @param ancestorEnvironment the Environment Compiler of the ancestor
	 */
	public EnvironmentCompiler(EnvironmentCompiler ancestorEnvironment) {		
		this.staticLinkAncestorHeapStackFrame = ancestorEnvironment;
		this.currentLabelNumber = 0;
	}
	
	/**
	 * Returns this Environment Compiler, starting a new Scope/Frame.
	 * 
	 * @return this Environment Compiler, starting a new Scope/Frame
	 */
	public EnvironmentCompiler beginScope() {
		return new EnvironmentCompiler(this);
	}

	/**
	 * Returns true, if this Environment Compiler (Scope/Frame)
	 * it's the root and don't have any Environment Compiler (Scope/Frame) ancestors
	 * and false, otherwise.
	 * 
	 * @return true, if this Environment Compiler (Scope/Frame)
	 * 		   it's the root and don't have any Environment Compiler (Scope/Frame) ancestors
	 * 		   and false, otherwise
	 */
	public boolean isEnvironmentRoot() {
		return this.staticLinkAncestorHeapStackFrame == null ? true : false;
	}
	
	/**
	 * Returns the current number used for labels "Lx".
	 * 
	 * @return the current number used for labels "Lx"
	 */
	public int getCurrentLabelNumber() {
		return currentLabelNumber++;
	}

	/**
	 * Returns the current number used for labels "ref_class".
	 * 
	 * @return the current number used for labels "ref_class"
	 */
	public int getCurrentReferenceNumber() {
		return currentReferenceNumber++;
	}

}