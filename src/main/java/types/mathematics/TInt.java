package main.java.types.mathematics;

import main.java.types.IType;

public class TInt implements IType {

	private static final TInt INSTANCE = new TInt();
	
	public static TInt getInstance() {
		return INSTANCE;
	}
	
	@Override
	public String getTypeName() {
		return "Integer";
	}

	@Override
	public String getStackFrameName() {
		return "I";
	}

	@Override
	public String getStackRefName() {
		return "ref_int";
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TInt;
	}

}
