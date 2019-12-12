package main.java.types.references;

import main.java.types.IType;

public class TRef<T> implements IType {
	
	private IType referenceType;
	
	
	public TRef(IType referenceType) {
		this.referenceType = referenceType;
	}
	
	@Override
	public String getTypeName() {
		return String.format("Reference for Type: %s", this.referenceType.getTypeName());
	}

	@Override
	public String getStackFrameName() {
		return "Ljava/lang/Object";
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TRef;
	}

	public IType getReferenceType() {
		return this.referenceType;
	}
	
}
