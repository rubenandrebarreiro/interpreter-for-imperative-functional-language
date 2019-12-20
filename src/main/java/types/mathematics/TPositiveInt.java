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

public class TPositiveInt extends TInt implements IType {

	private static final TPositiveInt INSTANCE = new TPositiveInt();

	public static TPositiveInt getInstance() {
		return INSTANCE;
	}
	
	@Override
	public String getTypeName() {
		return "Positive Integer";
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TPositiveInt;
	}
	
}
