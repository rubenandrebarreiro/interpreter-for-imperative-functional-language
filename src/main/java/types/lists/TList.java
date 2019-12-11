package main.java.types.lists;

import main.java.types.IType;

public class TList<T> implements IType {

	private IType listType;	
	
	public TList(IType listType) {
		this.listType = listType;
	}
	
	@Override
	public String getTypeName() {
		return String.format("List of Type: %s", listType);
	}

	@Override
	public String getStackFrameName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TList;
	}

	public IType getListType() {
		return this.listType;
	}
}
