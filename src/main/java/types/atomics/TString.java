package main.java.types.atomics;

import main.java.types.IType;

public class TString implements IType {
	
	private static final TString tStringType = new TString();

	@Override
	public String getTypeName() {
		return "string";
	}

	@Override
	public String getStackFrameName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TString;
	}
	
}
