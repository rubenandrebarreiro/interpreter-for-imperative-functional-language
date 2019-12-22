package main.java.types.references;

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

import main.java.types.IType;

/**
 * Class for Type of a Reference Value, implementing a Type of a Value.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class TRef implements IType {
	
	// Global Instance Variables:
	
	/**
	 * The Type of Value for this Type of Reference Value
	 */
	private IType referenceType;
	
	/**
	 * Constructor #1:
	 * - Constructor of Type of Reference Value;
	 * 
	 * @param referenceType the Type of Value for this Type of Reference Value
	 */
	public TRef(IType referenceType) {
		this.referenceType = referenceType;
	}
	
	
	// Methods/Functions:
	
	/**
	 * Returns the name of this Type of Value.
	 * 
	 * @return the name of this Type of Value
	 */
	@Override
	public String getTypeName() {
		return String.format("Reference for Type: %s", this.referenceType.getTypeName());
	}

	/**
	 * Returns the name of the Heap Stack Frame, for this Type of Value.
	 * 
	 * @return the name of the Heap Stack Frame, for this Type of Value
	 */
	@Override
	public String getHeapStackFrameName() {
		return "Ljava/lang/Object";
	}

	/**
	 * Returns the name of the Reference of the Heap Stack Frame,
	 * for this Type of Value.
	 * 
	 * @return the name of the Reference of the Heap Stack Frame,
	 * 		   for this Type of Value
	 */
	@Override
	public String getHeapStackFrameReferenceName() {
		return "ref_class";
	}

	// TODO
	public String getClosureInterfaceTypeName() {
		return "ref";
	}
	
	public String getStoreJVMInstruction() {
		return "astore";
	}
	
	public String getLoadJVMInstruction() {
		return "aload";
	}
	
	/**
	 * Returns true if this Type of Value it's equal to another one given and false, otherwise.
	 * 
	 * @param otherType another Type of Value, to be compared with this one
	 * 
	 * @return true if this Type of Value it's equal to another one given and false, otherwise
	 */
	@Override
	public boolean equals(IType otherType) {
		return otherType instanceof TRef;
	}

	/**
	 * Returns the Type of Value for this Type of Reference Value.
	 * 
	 * @return the Type of Value for this Type of Reference Value
	 */
	public IType getReferenceType() {
		return this.referenceType;
	}	
	
}