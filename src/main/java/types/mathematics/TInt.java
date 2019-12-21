package main.java.types.mathematics;

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
 * Class for Type of an Integer Value.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class TInt implements IType {

	// Constants:
	
	/**
	 * The Singleton Instance of Integer Type
	 */
	private static final TInt SINGLETON_INSTANCE = new TInt();
	
	
	// Methods/Functions:
	
	/**
	 * Returns the name of this Type of Value.
	 * 
	 * @return the name of this Type of Value
	 */
	@Override
	public String getTypeName() {
		return "Integer";
	}

	/**
	 * Returns the name of the Heap Stack Frame, for this Type of Value.
	 * 
	 * @return the name of the Heap Stack Frame, for this Type of Value
	 */
	@Override
	public String getHeapStackFrameName() {
		return "I";
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
		return "ref_int";
	}

	// TODO
	public String getClosureInterfaceTypeName() {
		return "int";
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
		return otherType instanceof TInt;
	}

	/**
	 * Returns the Singleton Instance of Integer Type.
	 * 
	 * @return the Singleton Instance of Integer Type
	 */
	public static TInt getSingletonInstance() {
		return SINGLETON_INSTANCE;
	}
}
