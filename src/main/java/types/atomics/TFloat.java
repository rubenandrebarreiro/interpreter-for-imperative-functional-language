package main.java.types.atomics;

import main.java.types.IType;

public class TFloat implements IType {

	@Override
	public String getTypeName() {
		return "float";
	}

	@Override
	public String getStackFrameName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TFloat;
	}

}
