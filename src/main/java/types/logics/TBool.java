package main.java.types.logics;

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

public class TBool implements IType{

	private static final TBool INSTANCE = new TBool();
	
	public static TBool getSingletonInstance() {
		return INSTANCE;
	}
	
	@Override
	public String getTypeName() {
		return "Boolean";
	}

	@Override
	public String getHeapStackFrameName() {
		return "I";
	}

	@Override
	public String getHeapStackFrameReferenceName() {
		return "ref_int";
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TBool;
	}
	
}