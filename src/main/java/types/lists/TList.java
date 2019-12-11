package main.java.types.lists;

import main.java.types.IType;

public class TList<T> implements IType {

	@Override
	public String getTypeName() {
		return "List of "; //TODO
	}

	@Override
	public String getStackFrameName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TList<?>;
	}

}
