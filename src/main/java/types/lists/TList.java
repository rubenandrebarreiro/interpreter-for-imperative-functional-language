package main.java.types.lists;

import main.java.types.IType;

public class TList implements IType {

	private IType listType;	
	
	public TList(IType listType) {
		this.listType = listType;
	}
	
	@Override
	public String getTypeName() {
		return String.format("List of Type: %s", this.listType.getTypeName());
	}

	@Override
	public String getStackFrameName() {
		return "Ljava/lang/Object";
	}

	@Override
	public String getStackRefName() {
		return "ref_class";
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TList;
	}

	public IType getListType() {
		return this.listType;
	}
}
