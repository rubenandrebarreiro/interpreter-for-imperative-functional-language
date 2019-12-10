package main.java.types.mathematics;

import main.java.types.IType;

public class TNegativeInt extends TInt implements IType {

	@Override
	public String getTypeName() {
		return "Negative Integer";
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TNegativeInt;
	}

}
