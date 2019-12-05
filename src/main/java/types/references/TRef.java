package main.java.types.references;

import main.java.types.IType;

public class TRef implements IType {

	@Override
	public String getTypeName() {
		return "reference";
	}

	@Override
	public String getStackFrameName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TRef;
	}

}
