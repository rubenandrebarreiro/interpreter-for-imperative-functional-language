package main.java.types.functions;

import main.java.types.IType;

public class TFun implements IType {

	@Override
	public String getTypeName() {
		return "Function";
	}

	@Override
	public String getStackFrameName() {
		return "Ljava/lang/Object";
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TFun;
	}

}
