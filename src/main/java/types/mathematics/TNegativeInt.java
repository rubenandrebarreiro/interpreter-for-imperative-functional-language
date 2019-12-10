package main.java.types.mathematics;

import main.java.types.IType;

public class TNegativeInt extends TInt implements IType {

	private static final TNegativeInt INSTANCE = new TNegativeInt();

	public static TNegativeInt getInstance() {
		return INSTANCE;
	}
	
	@Override
	public String getTypeName() {
		return "Negative Integer";
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TNegativeInt;
	}

}
