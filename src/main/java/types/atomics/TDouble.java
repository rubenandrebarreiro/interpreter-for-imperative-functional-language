package main.java.types.atomics;

import main.java.types.IType;

public class TDouble implements IType {

	@Override
	public String getTypeName() {
		return "double";
	}

	@Override
	public String getStackFrameName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TDouble;
	}

}
