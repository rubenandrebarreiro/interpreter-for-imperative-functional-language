package main.java.types.mathematics;

import main.java.types.IType;

public class TPositiveInt extends TInt implements IType {

	private static final TPositiveInt INSTANCE = new TPositiveInt();

	public static TPositiveInt getInstance() {
		return INSTANCE;
	}
	
	@Override
	public String getTypeName() {
		return "Positive Integer";
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TPositiveInt;
	}
	
}
