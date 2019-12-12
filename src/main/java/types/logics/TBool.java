package main.java.types.logics;

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
	public String getStackFrameName() {
		return "I";
	}

	@Override
	public String getStackRefName() {
		return "ref_int";
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TBool;
	}
	
}