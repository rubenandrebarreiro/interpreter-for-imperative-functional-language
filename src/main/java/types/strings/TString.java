package main.java.types.strings;

import main.java.types.IType;

public class TString implements IType {
	
	private static final TString INSTANCE = new TString();

	public static TString getInstance() {
		return INSTANCE;
	}
	
	@Override
	public String getTypeName() {
		return "String";
	}

	@Override
	public String getStackFrameName() {
		return "Ljava/lang/String"; //TODO object confirmar
	}

	@Override
	public String getStackRefName() {
		return "ref_class";
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TString;
	}
	
}
