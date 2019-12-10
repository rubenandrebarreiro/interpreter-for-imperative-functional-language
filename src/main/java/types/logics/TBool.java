package main.java.types.logics;

import main.java.types.IType;

public class TBool implements IType{

	private static final TBool INSTANCE = new TBool();
	
	public static TBool getInstance() {
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
	public boolean equals(IType other) {
		return other instanceof TBool;
	}
	
}