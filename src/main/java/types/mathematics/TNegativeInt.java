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
 * Class for Type of a Positive Integer Value,
 * extending the Type of an Integer Value and implementing a Type of a Value.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class TNegativeInt extends TInt implements IType {

	private static final TNegativeInt INSTANCE = new TNegativeInt();

	public static TNegativeInt getSingletonInstance() {
		return INSTANCE;
	}
	
	@Override
	public String getTypeName() {
		return "Negative Integer";
	}

	@Override
	public boolean equals(IType otherType) {
		return otherType instanceof TNegativeInt;
	}

}
