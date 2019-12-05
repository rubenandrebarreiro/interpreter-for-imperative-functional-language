package main.java.types.functions;

import main.java.types.IType;

public class TFun implements IType {

	@Override
	public String getTypeName() {
		return "Function";
	}

	@Override
	public String getStackFrameName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TFun;
	}

}
