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
	public boolean equals(IType other) {
		return other instanceof TNegativeInt;
	}

}
